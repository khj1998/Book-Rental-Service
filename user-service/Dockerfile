FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

COPY ../gradlew .
COPY ../gradle gradle
COPY ../build.gradle ../settings.gradle ./
COPY ../user-service user-service
RUN  ./gradlew :user-service:bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/user-service/build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]