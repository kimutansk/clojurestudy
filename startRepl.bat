@echo off

set CLOJURE_HOME=%~dp0
set LIB_PATH=%CLOJURE_HOME%\lib
set SRC_PATH=%CLOJURE_HOME%\src
set CLASS_PATH=%CLOJURE_HOME%\classes

mkdir %CLASS_PATH%

java -jar %LIB_PATH%\clojure-1.5.1.jar
pause

