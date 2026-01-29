# Etapa 1: Build con Temurin 21 y Maven instalado manualmente
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Instalar Maven
RUN apt-get update && apt-get install -y maven

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Etapa 2: runtime con Java 21
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","app.jar"]


