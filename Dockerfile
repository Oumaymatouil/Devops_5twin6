
FROM maven:3.8.3-openjdk-17 AS build

WORKDIR /Devops_5twin6
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
EXPOSE 8080
EXPOSE 8089
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]