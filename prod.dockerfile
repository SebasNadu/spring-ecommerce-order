# Stage 1: Build with limited memory for Gradle daemon
FROM gradle:8.13-jdk21 AS build

WORKDIR /home/gradle/project

COPY gradlew .
COPY gradle ./gradle

COPY build.gradle.kts settings.gradle.kts ./

RUN chmod +x gradlew

RUN ./gradlew --version

# Limit Gradle JVM memory to avoid OOM
ENV GRADLE_OPTS="-Xmx256m -Xms128m"

COPY src ./src

RUN ./gradlew clean build -x test --no-daemon --stacktrace -Dorg.gradle.jvmargs="-Xmx256m -Xms128m"

# Stage 2: Run with minimal memory settings
FROM eclipse-temurin:21-jdk-jammy

RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

USER appuser

EXPOSE 8080

# JVM tuned for low memory footprint, minimal heap and metaspace
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-XX:MaxMetaspaceSize=128m", "-XX:+UseG1GC", "-jar", "/app/app.jar"]
