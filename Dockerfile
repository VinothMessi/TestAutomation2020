FROM openjdk:8u191-jre-alpine3.9

#For health check script
#RUN apk add curl jq

#Work Space
WORKDIR /usr/share/automation2020

#Adding JARS files from target folder of host
#To the docker image
ADD target/automation-testing-docker.jar        automation-testing-docker.jar
ADD target/automation-testing-docker-tests.jar  automation-testing-docker-tests.jar
ADD target/libs                                 libs

#Adding Suite files (testng.xml files)
ADD flight-booking.xml flight-booking.xml

#Adding health check up script
ADD healthcheck.sh healthcheck.sh

ENTRYPOINT java -cp automation-testing-docker.jar:automation-testing-docker-tests.jar:libs/* org.testng.TestNG $MODULE

#ENTRYPOINT sh healthcheck.sh