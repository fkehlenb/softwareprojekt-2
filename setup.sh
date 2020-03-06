#!/bin/bash
echo "You have choosen following Folder to replace the Standalone.xml File" "$1"
cp standalone.xml $1/standalone/configuration
echo "Succesfully Setup Widlfly, please exexute with: " "$1" "/standalone/bin/standalone.sh"