# import library
require "fileutils.rb"
require "ftools.rb"

# CVS root and operations
class CVSroot
  include FileUtils
  
  def initialize(protocol, host, path)
    @protocol = protocol
    @host = host
    @path = path
    @root = ""
  end

  def user=(user)
    @root = ":"+@protocol+":"+user+"@"+@host+":"+@path
  end

  #singleton pattern to define bleumRoot and itoRoot
  @@bleumRoot = CVSroot.new("sspi","192.168.2.200","/1fb")
  @@itoRoot = CVSroot.new("ext","ito-as1.1fb.net","/vb/cvs")
  @@itoRoot.user = "bbu"
  
  def CVSroot.bleumRoot
    return @@bleumRoot
  end
  
  def CVSroot.itoRoot
    return @@itoRoot
  end
  
  def chdir(folder)
    Dir.chdir(folder)
  end
 
  # execute basic CVS command
  def cvs(command)
    return system("cvs -d "+@root+" "+command)
  end

  def login
    #loggedOn = cvs("login")
    #if not loggedOn
      #puts "Fail to login CVS"
      #exit
    #end
  end
  
  #assumption: current folder is out of CVS working folder
  def checkout(path,branch,folder)
    remove_dir(folder,true)
    if branch == "head"
      puts "Check out module "+path+" from HEAD..."
      succeeded = cvs("-Q checkout -d "+folder+" "+path)
    else
      puts "Check out module "+path+" from branch "+branch+"..."
      succeeded = cvs("-Q checkout -d "+folder+" -r "+branch+" "+path)
    end
    if not ( succeeded && File.exists?(folder) )
      puts "Failed to checkout module "+path+" from branch "+branch
      exit
    end
  end

  #assumption: current folder is out of CVS working folder
  def checkoutTag(path,tag,folder)
    remove_dir(folder,true)
    puts "Check out module "+path+" with tag "+tag+"..."
    succeeded = cvs("-Q checkout -d "+folder+" -r "+tag+" "+path)
    if not ( succeeded && File.exists?(folder) )
      puts "Failed to checkout module "+path+" with tag "+tag
      puts ""
      throw :incorrect_tag
    end
  end
  
  #assumption: current folder is out of CVS working folder
  def log(branch,folder)
    chdir(folder)
    if branch == "head"
      cvs("-Q log -N -r > cvs.log")
    else 
      cvs("-Q log -N -r"+branch+". > cvs.log")
    end
    chdir("..")
  end
  
  #assumption: current folder is CVS working folder
  #assumption: comment is already wrapped with double quote
  def checkin(file,comment)
    if File.exist?(file)
      if not cvs("-Q commit -m "+comment+" \""+file+"\"")
        puts "Error: cannot checkin modified file "+file+" to CVS. There's some conflict!"
      end
    else
      puts "Error: cannot checkin modified file "+file+" to CVS. It doesn't exist!"
    end
  end

  #assumption: current folder is CVS working folder
  #assumption: comment is already wrapped with double quote
  #assumption: file is NOT starting with slash "/"
  def addBinary(file,comment)
    if File.exist?(file)
      confirmDirAdded(file)
      cvs("-Q add -kb \""+file+"\"")
      cvs("-Q commit -m "+comment+" \""+file+"\"")
    else
      puts "Error: cannot add binary file "+file+" to CVS. It doesn't exist!"
    end  
  end
  
  #assumption: current folder is CVS working folder
  #assumption: comment is already wrapped with double quote
  #assumption: file is NOT starting with slash "/"
  def addTXT(file,comment)
    if File.exist?(file)
      confirmDirAdded(file)
      cvs("-Q add \""+file+"\"")
      cvs("-Q commit -m "+comment+" \""+file+"\"")
    else
      puts "Error: cannot add file "+file+" to CVS. It doesn't exist!"
    end  
  end
  
  #assumption: current folder is CVS working folder
  #assumption: comment is already wrapped with double quote
  def delete(file,comment)
    cvs("-Q remove -f \""+file+"\"")
    cvs("-Q commit -m "+comment+" \""+file+"\"")
  end
  
  #assumption: current folder is out of CVS working folder
  def createTag(tag,folder)
    chdir(folder)
    cvs("-Q tag "+tag)
    chdir("..")
  end

  private
  
  #assumption: current folder is CVS working folder
  #assumption: file is NOT starting with slash "/"
  def confirmDirAdded(file)
    currentIndex = file.index("/")
    until currentIndex == nil
      folder = file[0..currentIndex - 1]
      cvsMetaFolder = File.join( folder, "CVS")
      if ! ( File.exist?(cvsMetaFolder) && File.directory?(cvsMetaFolder) )
        cvs("-Q add \""+folder+"\"")
      end
      currentIndex = file.index("/",currentIndex+1)
    end
  end
 
end

class ProjectFile
  TXT_FILE_TYPES = [".java",".jsp", ".txt", ".jhtml", ".html", ".xsd",".xml", ".properties", ".htm",".dtd",".tld",".css",".js"]
  SRC_FILE_TYPES = [".java",".properties",".jsp"]
  WAR_FILE_TYPES = [".jsp",".txt",".jhtml",".html",".xsd",".xml",".properties",".htm",".dtd",".tld",".css",".js",".jpe",".jpg",".pdf",".jpeg",".png",".bmp",".gif",".ttf","jar"]
  MAPPING_FILE_TYPES = [".xml"]
  DEFAULT_FILE_TYPES = SRC_FILE_TYPES + WAR_FILE_TYPES
  
  attr_reader :fileName, :comment
  attr_accessor :status
  
  def initialize(fileName)
    @fileName = fileName
    @status = ""
    @comment = ""
  end

  def ProjectFile.isTXTfile?(file)
    return TXT_FILE_TYPES.include?(File.extname(file))
  end
  
  def ProjectFile.isModified?(fileName,folder1,folder2)
    inFolder1 = File.exist?( File.join(folder1,fileName) )
    inFolder2 = File.exist?( File.join(folder2,fileName) )
    if ! ( inFolder1 == inFolder2 )
      return true
    end
    if ! ( inFolder1 && inFolder2 )
      return false
    end
    if ProjectFile.isTXTfile?(fileName)
      return ProjectFile.isTXTfileModified?(fileName,folder1,folder2)
    else
      return ProjectFile.isBinaryFileModified?(fileName,folder1,folder2)
    end
  end 
  
  def resolveComment(logs)  
    index = logs.index("Working file: "+fileName+"\n")
    if index == nil
      @comment = "not found in CVS log."
    else 
      index = index + 5
      line = logs[index].strip
      until line[0..4] == "date:"
        index = index + 1
        line = logs[index].strip
      end
      @comment = ""
      line = logs[index+=1].chop.strip
      while line =~ /[^=]/
        @comment += (line + " ")
        line = logs[index+=1].chop.strip
      end
      @comment.gsub!(/\n/," ")
      @comment.gsub!(/\"/,"'")
    end
    if @status == "1FB added"
      @comment = "added by 1FB: "+@comment
    elsif @status == "1FB modified"
      @comment = "modified by 1FB: "+@comment
    elsif @status == "1FB deleted"
      @comment = "deleted by 1FB: "+@comment
    elsif @status == "new"
      @comment = "added by Bleum: "+@comment
    elsif @status == "modified"
      @comment = "modified by Bleum: "+@comment
    elsif @status == "deleted"
      @comment = "deleted by Bleum: "+@comment
    elsif $project.mode == "sync" 
      @comment = "ITO log: "+@comment
    else 
      @comment = "Bleum log: "+@comment
    end
  end
  

  private
  
  def ProjectFile.isTXTfileModified?(fileName,folder1,folder2)
    lines1 = ProjectFile.readTXTlines(fileName,folder1)
    lines2 = ProjectFile.readTXTlines(fileName,folder2)
    until lines1.empty? && lines2.empty?
      if lines1.empty? || lines2.empty?
        return true
      end
      currentLine1 = ProjectFile.getLastLine(lines1)
      currentLine2 = ProjectFile.getLastLine(lines2)
      if not (currentLine1 == currentLine2)
        return true
      end
    end
    return false
  end
  
  def ProjectFile.isBinaryFileModified?(fileName,folder1,folder2)
    return (not File.compare( File.join(folder1,fileName), File.join(folder2,fileName) ) )
  end

  def ProjectFile.readTXTlines(fileName,folder)
    file = File.new( File.join(folder,fileName) )
    lines = file.readlines
    file.close
    fileUpdated = false
    lines.each do |line| 
      lineUpdated1 = ! ( line.gsub!(/\r\n/,"\n") == nil )
      lineUpdated2 = ! ( line.gsub!(/\r/,"\n") == nil )
      if lineUpdated1 or lineUpdated2
        fileUpdated = true
      end
    end
    if fileUpdated
      file = File.new( File.join(folder,fileName), "w" )
      file.puts(lines)
      file.close
      file = File.new( File.join(folder,fileName) )
      lines = file.readlines
      file.close
    end
    return lines
  end
  
  def ProjectFile.getLastLine(lines)
    line = ""
    while line == ""
      line = lines.pop
      if( line == nil )
        return nil
      end
      line.delete!("\r\n")
      line.gsub!(/\$Author:(.*)\$/,"")
      line.gsub!(/\$Date:(.*)\$/,"")
      line.gsub!(/\$Header:(.*)\$/,"")
      line.gsub!(/\$Id:(.*)\$/,"")
      line.gsub!(/\$Name:(.*)\$/,"")
      line.gsub!(/\$Locker:(.*)\$/,"")
      line.gsub!(/\$Log:(.*)\$/,"")
      line.gsub!(/\$rcsfile:(.*)\$/,"")
      line.gsub!(/\$Revision:(.*)\$/,"")
      line.gsub!(/\$Source:(.*)\$/,"")
      line.gsub!(/\$State:(.*)\$/,"")
      line.strip!
    end
    return line
  end
  
end

# a CVS module
class CVSmodule

  attr_writer :branch
  attr_accessor :folder, :previousTagFolder
  
  def initialize(path,cvsRoot)
    @cvsRoot = cvsRoot
    @path = "\""+path+"\""
    @branch = "head"
    @folder = ""
    @previousTagFolder = ""
  end
  
  def checkout(enableLog)
    @cvsRoot.checkout(@path,@branch,@folder)
    if enableLog
      @cvsRoot.log(@branch,@folder)
    end
  end
  
  def checkoutPreviousTag(previousTag)
    @cvsRoot.checkoutTag(@path,previousTag,@previousTagFolder)
  end
  
  def createTag(tag)
    @cvsRoot.createTag(tag,@folder)
  end

  def checkin(fileName,comment)
    @cvsRoot.checkin(fileName,comment)
  end
  
  def addBinary(fileName,comment)
    @cvsRoot.addBinary(fileName,comment)
  end
  
  def addTXT(fileName,comment)
    @cvsRoot.addTXT(fileName,comment)
  end
  
  def delete(fileName,comment)
    @cvsRoot.delete(fileName,comment)
  end

  def getLogs
    logFile = File.new( File.join(@folder,"cvs.log") )
    logs = logFile.readlines
    logFile.close
    return logs
  end
  
  def fileListName
      fileListName = @path.gsub(/\//,"-")
      fileListName.gsub!("\"","")
      return fileListName +" module filelist.txt"
  end
  
end

# a module in project
class Module
  include FileUtils

  def initialize(bleumPath, itoPath)
    @bleumModule = CVSmodule.new(bleumPath,CVSroot.bleumRoot)
    @itoModule = CVSmodule.new(itoPath,CVSroot.itoRoot)
    @fileTypes = []
    if itoPath[-3..-1] == "src"
      @fileTypes = ProjectFile::SRC_FILE_TYPES
    elsif itoPath[-3..-1] == "war"
      @fileTypes = ProjectFile::WAR_FILE_TYPES
    elsif itoPath[-7..-1] == "mapping"
      @fileTypes = ProjectFile::MAPPING_FILE_TYPES
    else
      @fileTypes = ProjectFile::DEFAULT_FILE_TYPES
    end
  end

  def bleumBranch=(bleumBranch)
    @bleumModule.branch = bleumBranch
  end
  
  def itoBranch=(itoBranch)
    @itoModule.branch = itoBranch
  end
  
  def setFolder(index)
    index = index+1
    @bleumModule.folder = "bleumModule"+index.to_s
    @bleumModule.previousTagFolder = "bleumPreviousTagModule"+index.to_s
    @itoModule.folder = "itoModule"+index.to_s
    @itoModule.previousTagFolder = "itoPreviousTagModule"+index.to_s
  end
  
  def checkoutBleumModule
    enableLog = $project.mode == "release" || $project.mode == "debug"
    @bleumModule.checkout(enableLog)
  end
  
  def checkoutITOmodule
    enableLog = $project.mode == "sync" || $project.mode == "debug"
    @itoModule.checkout(enableLog)
  end
  
  def checkoutBleumPreviousTagModule(previousTag)
    @bleumModule.checkoutPreviousTag(previousTag)
  end

  def checkoutITOpreviousTagModule(previousTag)
    @itoModule.checkoutPreviousTag(previousTag)
  end
  
  def createBleumTag(tag)
    @bleumModule.createTag(tag)
  end

  def createITOtag(tag)
    @itoModule.createTag(tag)
  end
  
  def compareFiles
    pattern = "**/*{"+@fileTypes.join(",")+"}"
    if $project.mode == "sync"
      updatedFiles = getSYNCupdatedFiles(pattern)
    else 
      updatedFiles = getReleaseUpdatedFiles(pattern)
    end      
    generateUpdatedFileList(updatedFiles)
  end
  
  def copyITOtoBleum
    actions = getActions
    eachAction(actions) do |action,fileName,comment|
      if action[0..3] == "SYM " || action[0..3] == "SYAB" || action[0..3] == "SYA "
        copyFile(fileName,@itoModule.folder,@bleumModule.folder)
      end
    end
  end
  
  def copyBleumToITO
    actions = getActions
    eachAction(actions) do |action,fileName,comment|
      if action[0..1] == "M " || action[0..2] == "AB " || action[0..1] == "A "
        copyFile(fileName,@bleumModule.folder,@itoModule.folder)
      end
    end
  end
  
  def checkinBleumModule
    actions = getActions
    Dir.chdir(@bleumModule.folder)
    eachAction(actions) do |action,fileName,comment|
      if action[0..3] == "SYM "
        @bleumModule.checkin(fileName,comment)
      elsif action[0..3] == "SYAB"
        @bleumModule.addBinary(fileName,comment)
      elsif action[0..3] == "SYA "
        @bleumModule.addTXT(fileName,comment)
      elsif action[0..3] == "SYD "
        @bleumModule.delete(fileName,comment)
      else
        # do nothing in other cases
      end
    end
    Dir.chdir("..")
  end    
  
  def checkinITOmodule
    actions = getActions
    Dir.chdir(@itoModule.folder)
    eachAction(actions) do |action,fileName,comment|
      if action[0..1] == "M "
        @itoModule.checkin(fileName,comment)
      elsif action[0..1] == "AB"
        @itoModule.addBinary(fileName,comment)
      elsif action[0..1] == "A "
        @itoModule.addTXT(fileName,comment)
      else
        # do nothing in other cases
      end
    end
    Dir.chdir("..")
  end    
  
  def renameFileList
    File.copy(fileListName, @itoModule.fileListName)
  end
  
  def fixBleumModule
    pattern = "**/*{"+@fileTypes.join(",")+"}"
    bleumFileNames = listFileNames(@bleumModule.folder,pattern)
    modifiedFileNames = []
    bleumFileNames.each do |fileName| 
      if fixFile(fileName,@bleumModule.folder) 
        modifiedFileNames.push(fileName)
      end
    end
    generateFixedFileList(modifiedFileNames,"SYM")
  end
  
  def fixITOmodule
    pattern = "**/*{"+@fileTypes.join(",")+"}"
    itoFileNames = listFileNames(@itoModule.folder,pattern)
    modifiedFileNames = []
    itoFileNames.each do |fileName| 
      if fixFile(fileName,@itoModule.folder) 
        modifiedFileNames.push(fileName)
      end
    end
    generateFixedFileList(modifiedFileNames,"M")
  end
  
  private
  
  def getSYNCupdatedFiles(pattern)
    updatedFileNames = getUpdatedFileNames(@itoModule.previousTagFolder,@itoModule.folder,pattern)
    updatedFiles = []
    itoLogs = @itoModule.getLogs
    updatedFileNames.each do |fileName|
      file = ProjectFile.new(fileName)
      if ProjectFile.isModified?(fileName,@itoModule.previousTagFolder,@bleumModule.folder)
        file.status = "conflicted"
      else
        bleumExist = File.exist?( File.join( @bleumModule.folder, fileName) )
        itoExist = File.exist?( File.join( @itoModule.folder, fileName ) )
        if bleumExist && itoExist
          file.status = "1FB modified"
        elsif bleumExist
          file.status = "1FB deleted"
        elsif itoExist
          file.status = "1FB added"
        else
          puts "CM script error: getUpdatedFileNames() listed non-exist file (from getSYNCupdatedFiles)!"
        end
      end
      file.resolveComment(itoLogs)
      updatedFiles.push(file)
    end
    return updatedFiles
  end
  
  def getReleaseUpdatedFiles(pattern)
    updatedFileNames = getUpdatedFileNames(@bleumModule.folder,@itoModule.folder,pattern)
    updatedFiles = []
    bleumLogs = @bleumModule.getLogs
    updatedFileNames.each do |fileName|
      file = ProjectFile.new(fileName)
      if ProjectFile.isModified?(fileName,@itoModule.previousTagFolder,@itoModule.folder)
        file.status = "conflicted"
      else
        bleumExist = File.exist?( File.join( @bleumModule.folder, fileName) )
        itoExist = File.exist?( File.join( @itoModule.folder, fileName ) )
        if bleumExist && itoExist
          file.status = "modified"
        elsif bleumExist
          file.status = "new"
        elsif itoExist
          file.status = "deleted"
        else
          puts "CM script error: getUpdatedFileNames() listed non-exist file (from getReleaseUpdatedFiles)!"
        end
      end
      file.resolveComment(bleumLogs)
      updatedFiles.push(file)
    end
    return updatedFiles
  end
  
  def getUpdatedFileNames(folder1,folder2,pattern)
    fileNames1 = listFileNames(folder1,pattern)
    fileNames2 = listFileNames(folder2,pattern)
    allFileNames = fileNames1 + ( fileNames2 - fileNames1 )
    updatedFileNames = []
    allFileNames.each do |fileName|
      if ProjectFile.isModified?(fileName,folder1,folder2)
        updatedFileNames.push( fileName )
      end
    end
    return updatedFileNames
  end

  def listFileNames(folder,pattern)
    Dir.chdir(folder)
    fileNames = Dir.glob(pattern, File::FNM_PATHNAME | File::FNM_CASEFOLD).sort!
    Dir.chdir("..")
    return fileNames
  end
  
  def generateUpdatedFileListSection(filelist, updatedFiles, title, status )
    filelist.puts(title)
    updatedFiles.each do |file|
      if file.status == status
        action = yield(file.fileName)
        filelist.print(action.ljust(5))
        filelist.print(file.fileName)
        filelist.print(" \"")
        filelist.print(file.comment)
        filelist.puts("\"")
      end
    end
    filelist.puts("")
  end
  
  def generateUpdatedFileList(updatedFiles)
    filelist = File.new(fileListName(),"w")
    generateUpdatedFileListSection(filelist,updatedFiles,"[Modified files]","modified") { |fileName| "M" }
    generateUpdatedFileListSection(filelist,updatedFiles,"[New files]","new") do |fileName| 
      if ProjectFile.isTXTfile?(fileName)
        "A"
      else
        "AB"
      end
    end
    generateUpdatedFileListSection(filelist,updatedFiles,"[Deleted files]","deleted") { |fileName| "D" }
    generateUpdatedFileListSection(filelist,updatedFiles,"[1FB modified files]","1FB modified") { |fileName| "SYM" }
    generateUpdatedFileListSection(filelist,updatedFiles,"[1FB added files]","1FB added") do |fileName| 
      if ProjectFile.isTXTfile?(fileName)
        "SYA"
      else
        "SYAB"
      end
    end
    generateUpdatedFileListSection(filelist,updatedFiles,"[1FB deleted files]","1FB deleted") { |fileName| "SYD" }
    generateUpdatedFileListSection(filelist,updatedFiles,"[Conflicted files]","conflicted") { |fileName| "C" }
    filelist.close
  end
  
  def fileListName
    return @bleumModule.folder+".txt"
  end

  def getActions
    filelist = File.new( fileListName() )
    actions = filelist.readlines
    filelist.close
    return actions
  end

  def eachAction(actions)
    actions.each do |action|
      action.strip!
      if action == "" || action[0..0] == "["
        next
      end
      commentStart = action.index("\"")
      if commentStart == nil
        puts "Warning: unknown line "+action
        next
      end
      fileNameStart = action.index(" ")
      fileNameEnd = commentStart - 1
      fileName = action[fileNameStart..fileNameEnd].strip
      commentEnd = action.length - 1
      comment = action[commentStart,commentEnd].strip
      yield(action,fileName,comment)
    end
  end
  
  def copyFile(fileName,fromFolder,toFolder)
    confirmDirExist(fileName,toFolder)
    from = File.join(fromFolder,fileName)
    to = File.join(toFolder,fileName)
    if File.exist?(from) 
      File.copy(from,to)
    else
      puts "Error: File "+fileName+" is not found in folder "+fromFolder+". Please check the filelist."
    end
  end
  
  def confirmDirExist(fileName,toFolder)
    index = fileName.rindex("/")
    if index == nil
      return
    end
    dir = fileName[0..index-1]
    mkdir_p( File.join(toFolder,dir) )
  end  
  
  def fixFile(fileName,folder)
    if ! ProjectFile.isTXTfile?(fileName)
      return false
    end
    file = File.new( File.join(folder,fileName) )
    lines = file.readlines
    file.close
    fileUpdated = false
    lines.each do |line| 
      lineUpdated1 = ! ( line.gsub!(/\r\n/,"\n") == nil )
      lineUpdated2 = ! ( line.gsub!(/\r/,"\n") == nil )
      if lineUpdated1 or lineUpdated2
        fileUpdated = true
      end
    end
    if fileUpdated
      file = File.new( File.join(folder,fileName), "w" )
      file.puts(lines)
      file.close
    end
    return fileUpdated
  end
  
  def generateFixedFileList(modifiedFileNames,action)
    filelist = File.new( fileListName(),"w")
    filelist.puts("[Modified files]")
    modifiedFileNames.each do |fileName|
      filelist.print(action.ljust(5))
      filelist.print(" ")
      filelist.print(fileName)
      filelist.puts(" \"fix line seperator\"")
    end
    filelist.puts("")
    filelist.close
  end
  
end  

# project
class Project
  attr_accessor :mode
  
  def initialize
    @modules = []
    @mode = "debug"
  end
  
  def modules=(modules)
    @modules = modules
    @modules.each_index { |index| @modules[index].setFolder(index) }
  end

  def checkoutBleumModules
    puts "Check out Bleum modules..."
    seconds = eachModule { |myModule| myModule.checkoutBleumModule }
    puts "Bleum modules are checked out in "+seconds+" seconds."
    puts ""
  end  

  def checkoutBleumPreviousTagModules(previousTag)
    puts "Check out Bleum modules with tag "+previousTag+"..."
    seconds = eachModule { |myModule| myModule.checkoutBleumPreviousTagModule(previousTag) }
    puts "Bleum modules with tag "+previousTag+" are checked out in "+seconds+" seconds."
    puts ""
  end  
  
  def checkoutITOmodules
    puts "Check out ITO modules..."
    seconds = eachModule { |myModule| myModule.checkoutITOmodule }
    puts "ITO modules are checked out in "+seconds+" seconds."
    puts ""
  end

  def checkoutITOpreviousTagModules(previousTag)
    puts "Check out ITO modules with tag "+previousTag+"..."
    seconds = eachModule { |myModule| myModule.checkoutITOpreviousTagModule(previousTag) }
    puts "ITO modules with tag "+previousTag+" are checked out in "+seconds+" seconds."
    puts ""
  end
  
  def createBleumTag(tag)
    puts  "Create tag on Bleum modules..."
    seconds = eachModule { |myModule| myModule.createBleumTag(tag) }
    puts "Tag "+tag+" is created on Bleum modules in "+seconds+" seconds."
    puts ""
  end

  def createITOtag(tag)
    puts  "Create tag on ITO modules..."
    seconds = eachModule { |myModule| myModule.createITOtag(tag) }
    puts "Tag "+tag+" is created on ITO modules in "+seconds+" seconds."
    puts ""
  end
  
  def compareModules
    puts "Compare Bleum files and ITO files. Please wait..."
    seconds = eachModule { |myModule| myModule.compareFiles }
    puts "Files are compared in "+seconds+" seconds."
    puts ""
  end
  
  def copyITOtoBleum
    puts "Copy files from ITO folder to Bleum folder. Please wait..."
    seconds = eachModule { |myModule| myModule.copyITOtoBleum }
    puts "Files are copied from ITO folder to Bleum folder in "+seconds+" seconds."
    puts ""
  end

  def copyBleumToITO
    puts "Copy files from Bleum folder to ITO folder. Please wait..."
    seconds = eachModule { |myModule| myModule.copyBleumToITO }
    puts "Files are copied from Bleum folder to ITO folder in "+seconds+" seconds."
    puts ""
  end
  
  def checkinBleumModules
    puts "Check in Bleum modules..."
    seconds = eachModule{ |myModule| myModule.checkinBleumModule }
    puts "Bleum modules are checked in in "+seconds+" seconds."
    puts ""
  end

  def checkinITOmodules
    puts "Check in ITO modules..."
    seconds = eachModule{ |myModule| myModule.checkinITOmodule }
    puts "ITO modules are checked in in "+seconds+" seconds."
    puts ""
  end

  def renameFileList
    @modules.each { |myModule|  myModule.renameFileList }
  end

  def fixBleumModules
    puts "Fixing line seperators on Bleum modules..."
    seconds = eachModule{ |myModule| myModule.fixBleumModule }
    puts "Line seperators are fixed on Bleum modules in "+seconds+" seconds."
    puts ""
  end
  
  def fixITOmodules
    puts "Fixing line seperators on ITO modules..."
    seconds = eachModule{ |myModule| myModule.fixITOmodule }
    puts "Line seperators are fixed on ITO modules in "+seconds+" seconds."
    puts ""
  end

  private 
  
  def eachModule
    startTime = Time.now
    @modules.each { |myModule| yield(myModule) }
    endTime = Time.now
    seconds = endTime - startTime
    return seconds.to_s
  end

end

# define console
class Console
  
  @@vpnStatus = "unknown"
  
  def Console.selectProject
    projectFileFound = false
    until projectFileFound
      print "Please enter the project name:"
      projectName = gets
      projectName.chop!
      projectFile = projectName+".rb"
      projectFileFound = File.exist?(projectFile)
      if projectFileFound
        puts projectFile+" is found."
      else
        puts projectFile+" is not found."
      end
    end
    return projectFile
  end
  
  def Console.selectEnv
    while true
      print "Please enter the environment name (bleum,ito):"
      env = gets
      env.chop!
      if env == "bleum" || env == "ito"
        return env
      end
    end
  end
  
  def Console.loadProject(projectFile)
    require projectFile
    puts projectFile+" is loaded."
  end
  
  def Console.confirmVPNdisconnected
    if @@vpnStatus == "disconnected"
      return
    end
    puts "Please disconnect VPN. Press any key after VPN disconnected."
    gets
    @@vpnStatus = "disconnected"
  end

  def Console.confirmVPNconnected
    if @@vpnStatus == "connected"
      return
    end
    puts "Please connect VPN. Press any key after VPN connected."
    gets
    @@vpnStatus = "connected"
  end
  
  def Console.loginBleumCVSroot
    print "Please enter Bleum CVS user name:"
    cvsUser = gets
    cvsUser.chop!
    CVSroot.bleumRoot.user = cvsUser
    CVSroot.bleumRoot.login
    puts "Bleum CVS logged in."
    puts ""
  end
  
  def Console.loginITOcvsRoot
    CVSroot.itoRoot.login
    puts "ITO CVS logged in."
    puts ""
  end
  
  def Console.inputTag
    tagInputed = false
    until tagInputed
      if $project.mode == "release"
        puts "A sample tag BLEUM_FS_CAT1_05282007_1"
        print "Please enter the tag of this release:"
      elsif $project.mode == "sync"
        puts "A sample tag BLEUM_FS_SYNC_05282007_1"
        print "Please enter the tag of this sync:"
      else
        puts "A sample tag BLEUM_FS_CAT1_05282007_1"
        print "Please enter the tag of this debug:"
      end
      inputStr = gets
      inputStr.chop!
      if inputStr.length < 20
        puts "The inputted tag shall be at least 20 characters. Please input again."
        next
      end
      tagInputed = true
      #if $project.mode == "release"
        #pattern = /BLEUM.*(CAT|CHKIN).*/
      #elsif $project.mode == "sync"
        #pattern = /BLEUM.*(SYNC).*/
      #else
        #pattern = /BLEUM.*(SYNC|CAT|CHKIN).*/
      #end  
      #tagInputed = pattern.match(inputStr) != nil
      #if ! tagInputed
        #puts "Tag format incorrect. Please input again."
      #end
    end
    puts ""
    return inputStr
  end
  
  def Console.inputPreviousTag
    tagInputed = false
    until tagInputed
      puts "A most recent tag on SAME branch/HEAD shall be provided so that CM script can track updated files since last SYNC or CAT."
      puts "If you are releasing branch, please input a tag on same branch. If you are releasing HEAD, please input a tag on HEAD."
      print "Please enter the most recent tag on SAME branch/HEAD:"
      tag = gets
      tag.chop!
      if tag.length < 20
        puts "The inputted tag shall be at least 20 characters. Please input again."
        next
      end
      tagInputed = true
      #pattern = /BLEUM.*(SYNC|CAT|CHKIN).*/
      #tagInputed = pattern.match(tag) != nil
      #if ! tagInputed
        #puts "Tag format incorrect. Please input again."
      #end
    end
    puts ""
    return tag
  end
  
end


# preserve archived code

  #~ def populateCVSlog(logs,fileName,previousCVStag,moduleName)  
    #~ index = logs.index("Working file: "+fileName+"\n")
    #~ if index == nil
      #~ puts "Warning: file "+fileName+" is not found in cvs.log when looking up CVS logs in "+moduleName
      #~ return
    #~ end
    #~ @headRevision = getHeadRevision(logs,index)
    #~ @previousRevision = getTagRevision(logs,index,previousCVStag)
    #~ @currentRevision = getCurrentRevision(logs,index)
    #~ @comment = getComment(logs,index)
  #~ end
  
  #~ def isNotInCVS?
    #~ return @currentRevision == ""
  #~ end
  
  #~ def isUpdated?
    #~ return @currentRevision != @previousRevision
  #~ end
  
  #~ def isNew?
    #~ return @previousRevision == ""
  #~ end
  
  #~ private
  
  #~ def getHeadRevision(logs,index)
    #~ line = logs[index+1].strip
    #~ headRevisionEnd = line.length - 1
    #~ return line[6..headRevisionEnd]
  #~ end
  
  #~ def getTagRevision(logs,index,tag)
    #~ index = index + 5
    #~ tagLen = tag.length - 1
    #~ while true
      #~ index = index + 1
      #~ line = logs[index].strip
      #~ if line[0..6] == "keyword"
        #~ #tag is not found
        #~ return ""
      #~ end
      #~ if line[0..tagLen] == tag
        #~ tagRevisionStart = tagLen + 3
        #~ tagRevisionEnd = line.length - 1
        #~ return line[tagRevisionStart..tagRevisionEnd]
      #~ end
    #~ end
  #~ end
  
  #~ def getCurrentRevision(logs,index)
    #~ index = index + 5
    #~ while true
      #~ index = index + 1
      #~ line = logs[index].strip
      #~ if line[0..5] == "======"
        #~ # no branch revision is found. use headRevision instead
        #~ return @headRevision
      #~ end
      #~ if line[0..7] == "revision"
        #~ currentRevisionEnd = line.length-1
        #~ return line[9..currentRevisionEnd]
      #~ end
    #~ end
  #~ end
  
  #~ def getComment(logs,index)  
    #~ index = index + 5
    #~ line = logs[index].strip
    #~ until line[0..4] == "date:"
      #~ index = index + 1
      #~ line = logs[index].strip
    #~ end
    #~ comment = logs[index+1].chop!
    #~ comment.gsub!(/\n/," ")
    #~ comment.gsub!(/\"/,"'")
    #~ return comment
  #~ end
  
#~ end