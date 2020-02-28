FROM jboss/wildfly:latest
ENV JVM_ARGS="-Xmx2000m -Xms800m"

ADD /opt/wildfly/standalone/configuration/standalone.xml opt/wildfly/standalone/configuration/standalone.xml
ADD target/SFB-Farbig.war opt/jboss/wildfly/standalone/deployments/