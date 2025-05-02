# syntax = docker/dockerfile:1.4

# === Build stage ===
FROM eclipse-temurin:17-jdk AS builder
LABEL stage=builder

WORKDIR /workspace/app

COPY . .

RUN chmod +x gradlew

COPY src src

# === Runtime stage ===
FROM gcr.io/distroless/java17-debian11:nonroot AS runtime

WORKDIR /app

COPY --from=builder /workspace/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]

