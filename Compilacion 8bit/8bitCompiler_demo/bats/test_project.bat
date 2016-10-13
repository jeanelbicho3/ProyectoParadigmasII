@echo off
echo Prueba una caso de prueba: %1
java -cp .;lib;%CLASSPATH% eightBit.compile.EightBitc cases_sprint_01\%1 > output\%1.out.js
