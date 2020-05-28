FROM gradle:6.4.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

FROM openjdk:8-jre-slim

ENV PORT=8080

EXPOSE $PORT

RUN mkdir /app

COPY --from=build /home/gradle/src/com.nonograms.web/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-Dserver.port=$PORT", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
