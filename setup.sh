#!/bin/bash
echo "You have choosen following Folder to replace the Standalone.xml File" "$1"
if [ -z " $1 " ]
  then 
    echo "Usage: setup.sh /path/to/wildfly/folder"
    sleep 10
fi 
pwdx $(ps -ef | grep wildfly | awk {'print$2'}) | kill
cp standalone.xml $1/standalone/configuration
echo "Succesfully Setup Widlfly, please exexute with: " "$1" "/standalone/bin/standalone.sh"
sleep 10
