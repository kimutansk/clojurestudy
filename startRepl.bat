@echo off

set CLOJURE_HOME=%~dp0
set LIB_PATH=%CLOJURE_HOME%\lib
set SRC_PATH=%CLOJURE_HOME%\src
set CLASS_PATH=%CLOJURE_HOME%\classes

mkdir %CLASS_PATH%

java ^
-cp ^
%LIB_PATH%\clojure-1.5.1.jar;^
%LIB_PATH%\clojure-contrib-1.2.0.jar;^
%LIB_PATH%\criterium-0.4.0.jar;^
%SRC_PATH%;%CLASS_PATH% ^
clojure.main

pause

