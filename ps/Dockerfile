FROM maven:3.9.9-eclipse-temurin-21 AS builder 

WORKDIR /app 
#keeps code

COPY pom.xml .

RUN mvn dependency:go-offline -B
#install all the packages. cache the dependencies. 

COPY src ./src

RUN mvn clean package -DskipTests
#tells maven create a new package from springboot app 

FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder /app/target/ps-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]