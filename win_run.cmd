cd src
path %PATH%;C:\Program Files\Java\jdk1.8.0_261\bin
javac -classpath .;..\jars\mysql-connector-java-8.0.27.jar Main.java
java -classpath .;..\jars\mysql-connector-java-8.0.27.jar Main
del /s *.class