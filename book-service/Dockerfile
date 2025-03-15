FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

COPY ../gradlew .
COPY ../gradle gradle
COPY ../build.gradle ../settings.gradle ./
COPY ../book-service book-service
RUN  ./gradlew :book-service:bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/book-service/build/libs/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]