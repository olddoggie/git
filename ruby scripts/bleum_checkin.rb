require "cm_module.rb"

# main program
projectFile = Console.selectProject
# $project is initialized in projectFile
Console.loadProject(projectFile)
Console.confirmVPNdisconnected
Console.loginBleumCVSroot
$project.copyITOtoBleum
$project.checkinBleumModules
if $project.mode == "sync"
  tag = Console.inputTag
  $project.createBleumTag(tag)
end
