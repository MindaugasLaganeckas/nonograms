FROM gradle:6.4.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-slim

MAINTAINER Jonas Hecht

RUN mkdir /app

COPY --from=build /home/gradle/src/com.nonograms.web/build/libs/com.nonograms.web-1.0.jar /app/spring-boot-application.jar

CMD [ "sh", "-c", "java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -Djava.security.egd=file:/dev/./urandom -jar /app/spring-boot-application.jar"]
