FROM gradle:5.2.1-jdk8-alpine AS build
ARG PROJECT

COPY . /home/gradle/src
CMD chmod -R 777 /home/gradle/src
CMD cmdod -R 777 .
WORKDIR /home/gradle/src
RUN ./gradlew ${PROJECT}:build --no-daemon

FROM openjdk:8-jre-slim
ARG PROJECT

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/${PROJECT}/build/libs/*fat*.jar /app/${PROJECT}-application.jar

ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/${PROJECT}-application.jar"]
