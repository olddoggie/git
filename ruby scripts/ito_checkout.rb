require "cm_module.rb"

# main program
projectFile = Console.selectProject
# $project is initialized in projectFile
Console.loadProject(projectFile)
Console.confirmVPNconnected
Console.loginITOcvsRoot
$project.checkoutITOmodules
previousTagModulesSucceeded = false
until previousTagModulesSucceeded
  catch (:incorrect_tag) do
    previousTag = Console.inputPreviousTag
    $project.checkoutITOpreviousTagModules(previousTag)
    previousTagModulesSucceeded = true
  end
end
if $project.mode == "sync"
  tag = Console.inputTag
  $project.createITOtag(tag)
end
