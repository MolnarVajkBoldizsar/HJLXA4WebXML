@echo off
setlocal
chcp 65001 >nul

if "%~1"=="student-query" (
    set "SOURCE=%~dp0student-query\src\xpathhjlxa4\xPathQueryHJLXA4.java"
    set "CLASS=xpathhjlxa4.xPathQueryHJLXA4"
) else if "%~1"=="student-modify" (
    set "SOURCE=%~dp0student-modify\src\xpathhjlxa4\xPathModifyHJLXA4.java"
    set "CLASS=xpathhjlxa4.xPathModifyHJLXA4"
) else if "%~1"=="schedule-query" (
    set "SOURCE=%~dp0schedule-query\src\xpathhjlxa4\xPathQueryHJLXA4.java"
    set "CLASS=xpathhjlxa4.xPathQueryHJLXA4"
) else if "%~1"=="schedule-modify" (
    set "SOURCE=%~dp0schedule-modify\src\xpathhjlxa4\xPathModifyHJLXA4.java"
    set "CLASS=xpathhjlxa4.xPathModifyHJLXA4"
) else (
    echo Hasznalat: run.cmd student-query^|student-modify^|schedule-query^|schedule-modify
    exit /b 2
)

set "OUTPUT=%~dp0out\%~1"
if not exist "%OUTPUT%" mkdir "%OUTPUT%"

javac -encoding UTF-8 -d "%OUTPUT%" "%~dp0common\src\xpathhjlxa4\XmlSupport.java" "%~dp0common\src\xpathhjlxa4\XPathSupport.java" "%SOURCE%"
if errorlevel 1 exit /b 1

pushd "%~dp0"
java -cp "%OUTPUT%" %CLASS%
set "RESULT=%ERRORLEVEL%"
popd
exit /b %RESULT%
