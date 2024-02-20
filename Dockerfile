
FROM eclipse-temurin:11-jdk-jammy as deps

COPY spring-boot-user-microservice.jar users.jar

EXPOSE 8085

ENTRYPOINT [ "java", "-jar","users.jar" ]
