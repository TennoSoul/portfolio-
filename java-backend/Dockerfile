# Java backend (Spring Boot) multi-stage build
FROM maven:3.8.8-eclipse-temurin-17 AS builder
WORKDIR /app

# Copy pom and download dependencies
COPY pom.xml ./
RUN mvn -B -f pom.xml -DskipTests dependency:go-offline

COPY . .
RUN mvn -B -DskipTests package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
