@echo off
dir /s /B *.java > sources.txt 
javac @sources.txt -Xlint:unchecked	
java Main -Xmx2048m
PAUSE