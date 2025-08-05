
FROM maven:3.9.6-eclipse-temurin-17-focal AS build


WORKDIR /app


COPY pom.xml .


RUN mvn dependency:go-offline


COPY src ./src


RUN mvn clean package -DskipTests



FROM openjdk:21


WORKDIR /app


COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]