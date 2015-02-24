@echo off
setlocal enabledelayedexpansion

set JAVA_HOME=%JDK8_HOME%
set JAVA="%JAVA_HOME%"\bin\java
set GRADLE="D:\Program Files\Java\gradle-2.2.1"\bin\gradle
set BASE_DIR=..
set COMPILE_OPTS=-cp %BASE_DIR%\build\libs\*
set COMPILE_MAIN_CLASS=mincamlj.Main
set RUNTIME_OPTS=-cp classes;%BASE_DIR%\build\libs\*
set TEST_ML_DIR=ml
set TEST_OUTPUT_DIR=output
set TEST_PKG=mincamlj.test

pushd %0\..
cls

REM BUILD
REM  call %GRADLE% -p %BASE_DIR% build

if not exist %TEST_OUTPUT_DIR% mkdir %TEST_OUTPUT_DIR%

for /f "delims=;" %%i in ('dir /b .\ml\*.ml') do call :TEST %%~ni
echo check done.
goto END

:TEST
echo --- %1
REM  COMPILE
%JAVA% %COMPILE_OPTS% %COMPILE_MAIN_CLASS% %TEST_ML_DIR%\%1.ml %TEST_PKG%.%1 .\classes
REM  RUN
%JAVA% %RUNTIME_OPTS% %TEST_PKG%.%1 > %TEST_OUTPUT_DIR%\%1.out
REM  CHECK
fc %TEST_OUTPUT_DIR%\%1.out %TEST_ML_DIR%\%1.txt > NUL
REM JUMP IF ERROR OCCURED
if errorlevel 1 echo test failed. (%1.ml) && goto ERROR
exit /b

:ERROR

:END
pause
exit
