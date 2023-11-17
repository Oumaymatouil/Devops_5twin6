FROM openjdk:8
EXPOSE 8082
ADD target/khaddem-4.0.jar 5Twin6-G2-Kaddem.jar
ENTRYPOINT ["java" , "-jar" , "5Twin6-G2-Kaddem.jar"]