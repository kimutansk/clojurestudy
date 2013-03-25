@echo off

set CLOJURE_HOME=%~dp0
set LIB_PATH=%CLOJURE_HOME%\lib
set SRC_PATH=%CLOJURE_HOME%\src

java ^
-cp ^
%SRC_PATH%;^
%LIB_PATH%\clojure-1.5.1.jar;^
%LIB_PATH%\clojure-contrib-1.2.0.jar ^
clojure.main

pause

