@echo off
call ant -buildfile preSettings.xml copy_mvn_settings
kill /F /im IEDriverServer.exe
echo %JAVA_HOME% | findstr "1.6" && mvn clean test -Djdk=6
echo %JAVA_HOME% | findstr "1.7" && mvn clean test