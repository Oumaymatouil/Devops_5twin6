FROM openjdk:11
EXPOSE 8089
COPY target/khaddem-4.0.jar khaddem-4.0.jar
ENTRYPOINT ["java","-jar","/khaddem-4.0.jar"]