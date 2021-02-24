@ECHO OFF
javac -classpath src ./src/main/Scene.java -d bin
cd src
javadoc -d ../javadoc -sourcepath . -subpackages *
cd ../bin
jar cfe ../renderer.jar main.Scene *
pause