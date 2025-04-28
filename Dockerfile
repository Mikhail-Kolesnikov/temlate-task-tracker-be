# syntax = docker/dockerfile:1.4

# === Build stage ===
FROM eclipse-temurin:17-jdk AS builder
LABEL stage=builder

WORKDIR /workspace/app

COPY gradlew settings.gradle build.gradle ./
COPY gradle gradle

RUN chmod +x gradlew

RUN --mount=type=cache,target=/root/.gradle \
    --mount=type=cache,target=/workspace/app/.gradle \
    ./gradlew --no-daemon dependencies

COPY src src
RUN --mount=type=cache,target=/root/.gradle \
    --mount=type=cache,target=/workspace/app/.gradle \
    ./gradlew --no-daemon clean bootJar -x test

# === Runtime stage ===
FROM gcr.io/distroless/java17-debian11:nonroot AS runtime
LABEL stage=runtime

WORKDIR /app

ARG JAR_FILE=build/libs/tasktracker-0.0.1.jar
COPY --from=builder /workspace/app/${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
