FROM openjdk:8
ARG BUILD_NUMBER
ENV APP_VERSION=${BUILD_NUMBER}
EXPOSE 8090
ADD  target/khaddem-$APP_VERSION.jar khaddem-$APP_VERSION.jar
ENTRYPOINT ["java" , "-jar" , "khaddem-$APP_VERSION.jar" ]