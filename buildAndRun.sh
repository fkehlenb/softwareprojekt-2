#!/bin/bash
mvn clean package && docker build -t de.unibremen.sfb/SFB-Farbig .
docker rm -f SFB-Farbig || true && docker run -d -p 8080:8080 -p 4848:4848 --name SFB-Farbig de.unibremen.sfb/SFB-Farbig
