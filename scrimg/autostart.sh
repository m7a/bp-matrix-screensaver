#!/bin/bash

if [ "$1" == "start" ] 
then
clear
echo
echo MESSAGE : Changing Directory...
cd /data/Daten/programs/linux_main/settings/
# echo MESSAGE : Compiling...
# javac MatrixScreensaverImage.java
echo MESSAGE : Executing Program...
java MatrixScreensaverImage
# echo
# echo Press Enter...
# read none
exit 0
else
xterm -T "Autostart - Matrix Hintergrund" -bg black -cr green -fg green -title "Autostart - Matrix Hintergrund" -e "$0 start"
fi