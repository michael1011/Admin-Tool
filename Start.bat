@echo off

java -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Xms512M -Xmx1G -XX:MaxPermSize=128M -jar spigot.jar
pause