@echo off

set CLOJURE_HOME=%~dp0
set LIB_PATH=%CLOJURE_HOME%\lib
set SRC_PATH=%CLOJURE_HOME%\src
set CLASS_PATH=%CLOJURE_HOME%\classes

mkdir %CLASS_PATH%

echo %LIB_PATH%

java -classpath %LIB_PATH%\* clojure.main
pause

