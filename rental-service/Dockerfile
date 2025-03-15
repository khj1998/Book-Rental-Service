FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

COPY ../gradlew .
COPY ../gradle gradle
COPY ../build.gradle ../settings.gradle ./
COPY ../rental-service rental-service
RUN  ./gradlew :rental-service:bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/rental-service/build/libs/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]