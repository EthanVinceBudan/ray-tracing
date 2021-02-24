@ECHO OFF
javac -classpath src ./src/main/Scene.java -d bin
cd bin
jar cfe ../renderer.jar main.Scene *
pause