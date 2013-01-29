require "cm_module.rb"

# main program
projectFile = Console.selectProject
# $project is initialized in projectFile
Console.loadProject(projectFile)
$project.compareModules