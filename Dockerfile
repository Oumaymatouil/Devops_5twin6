FROM openjdk:8
EXPOSE 8089
ADD target/5Twin6-G2-Kaddem.jar 5Twin6-G2-Kaddem.jar
ENTRYPOINT ["java" , "-jar" , "5Twin6-G2-Kaddem.jar"]