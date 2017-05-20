@echo off
dir /s /B *.java > sources.txt 
javac @sources.txt
java Main
PAUSE