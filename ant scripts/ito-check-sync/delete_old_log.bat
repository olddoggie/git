@echo off
set p=D:\sync\sync logs
pushd %p%
set /a b=33
for /f "skip=%b% tokens=*" %%i in ('dir *.txt /a:-d/b/o-d') do del /q/f "%%i"