FROM gradle:8.13-jdk21 AS build

WORKDIR /home/gradle/project

COPY gradlew .
COPY gradle ./gradle

COPY build.gradle.kts settings.gradle.kts ./

RUN chmod +x gradlew

RUN ./gradlew --version

COPY src ./src

RUN ./gradlew clean build -x test --no-daemon --stacktrace

FROM eclipse-temurin:21-jdk-jammy

RUN addgroup --system appgroup && adduser --system appuser --ingroup appgroup

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-Xms512m", "-Xmx1024m", "-jar", "/app/app.jar"]
