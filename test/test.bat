@echo off

set JAVA_HOME=%JDK8_HOME%
set JAVA="%JAVA_HOME%"\bin\java
set GRADLE="D:\Program Files\Java\gradle-2.2.1"\bin\gradle
set BASE_DIR=..
set COMPILE_OPTS=-cp %BASE_DIR%\build\libs\*
set COMPILE_MAIN_CLASS=mincamlj.Main
set RUNTIME_OPTS=-cp classes;%BASE_DIR%\build\libs\*
set TEST_ML_DIR=ml
set TEST_PKG=mincamlj.test

pushd %0\..
cls

:BUILD
call %GRADLE% -p %BASE_DIR% build

:COMPILE
set target=Test
%JAVA% %COMPILE_OPTS% %COMPILE_MAIN_CLASS% %TEST_ML_DIR%\%target%.ml %TEST_PKG%.%target% .\classes

:TEST
%JAVA% %RUNTIME_OPTS% %TEST_PKG%.%target% > %TEST_ML_DIR%\%target%.out
fc %TEST_ML_DIR%\%target%.out %TEST_ML_DIR%\%target%.txt > NUL
if errorlevel 1 goto ERROR
echo check done.
goto END

:ERROR
echo error occured.
echo %errorlevel%

:END
pause
exit
