require "cm_module.rb"

# main program
projectFile = Console.selectProject
# $project is initialized in projectFile
Console.loadProject(projectFile)
Console.confirmVPNconnected
$project.copyBleumToITO
$project.checkinITOmodules
if $project.mode == "release"
  tag = Console.inputTag
  $project.createITOtag(tag)
end
$project.renameFileList