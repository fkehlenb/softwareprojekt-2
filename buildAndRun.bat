@echo off
call mvn clean package
call docker build -t de.unibremen.sfb/SFB-Farbig .
call docker rm -f SFB-Farbig
call docker run -d -p 8080:8080 -p 4848:4848 --name SFB-Farbig de.unibremen.sfb/SFB-Farbig