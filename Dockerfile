FROM anapsix/alpine-java:jdk8 AS build
ARG PROJECT

COPY . /home/gradle/src
RUN chown -R gradle:gradle /home/gradle/
WORKDIR /home/gradle/src
RUN ./gradlew ${PROJECT}:build --no-daemon

FROM openjdk:8-jre-slim
ARG PROJECT

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/${PROJECT}/build/libs/*fat*.jar /app/${PROJECT}-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/${PROJECT}-application.jar"]
