@echo off
set p=G:\cvsbackup
pushd %p%
set /a b=7
for /f "skip=%b% tokens=*" %%i in ('dir *.bkf /a:-d/b/o-d') do del /q/f "%%i" 