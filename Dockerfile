ARG BUILD_NUMBER
ENV APP_VERSION=${BUILD_NUMBER}
FROM openjdk:8
EXPOSE 8090
ADD  target/khaddem-$APP_VERSION.jar khaddem-$APP_VERSION.jar
ENTRYPOINT ["java" , "-jar" , "khaddem-$APP_VERSION.jar" ]