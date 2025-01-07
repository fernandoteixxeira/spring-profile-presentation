FROM maven:3.9.9-eclipse-temurin-17 as builder

COPY . /app/
WORKDIR /app/

RUN mvn clean install

FROM openjdk:17-slim as release

WORKDIR /

EXPOSE 8080

COPY --from=builder /app/target/*.jar /app.jar

ENV SPRING_PROFILES_ACTIVE=fromenv

#CMD java -jar /app.jar
CMD java -Dspring.profiles.active=fromparam -jar /app.jar