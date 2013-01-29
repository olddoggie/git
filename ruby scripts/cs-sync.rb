# set up project
cssrcModule = Module.new("csmaintenance/3.50/SourceCodeUBS/src","cs/src")
#cssrcModule.bleumBranch ="CS_CLEANUP"
#cssrcModule.itoBranch ="CS_CLEANUP"
cswarModule = Module.new("csmaintenance/3.50/SourceCodeUBS/war","cs/war")
#cswarModule.bleumBranch ="CS_CLEANUP"
#cswarModule.itoBranch ="CS_CLEANUP"

$project = Project.new
$project.modules = [cssrcModule, cswarModule]
$project.mode = "sync"