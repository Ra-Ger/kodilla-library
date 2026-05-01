# 1
FROM eclipse-temurin:21-jdk-alpine AS build
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build -x test

# 2
FROM eclipse-temurin:21-jre-alpine
COPY --from=build /build/libs/*.jar app.jar
EXPOSE 8080