# Stage 1 : compilation
FROM maven:3.8.7-eclipse-temurin-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : exécution
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/Employeer-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/tsenavao
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=1234

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
