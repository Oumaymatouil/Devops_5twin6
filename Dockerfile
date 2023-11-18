FROM openjdk:8
EXPOSE 8089
ADD target/Khaddem-4.0.jar Khaddem-4.0.jar
ENTRYPOINT ["java" , "-jar" , "Khaddem-4.0.jar"]