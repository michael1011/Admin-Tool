@echo off

mode con:cols=200 lines=50

:loop

java -Xmx1G -jar spigot-1.8.8.jar

PAUSE
goto loop