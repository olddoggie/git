@echo off
java -version | findstr "1.6" && mvn clean test -Djdk=6
java -version | findstr "1.7" && mvn clean test
