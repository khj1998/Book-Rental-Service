FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

COPY ../gradlew .
COPY ../gradle gradle
COPY ../build.gradle ../settings.gradle ./
COPY ../best-book-service best-book-service
RUN  ./gradlew :best-book-service:bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=builder /app/best-book-service/build/libs/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]