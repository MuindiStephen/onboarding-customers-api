
FROM eclipse-temurin:11-jdk-jammy as deps

COPY spring-boot-user-microservice.jar agrisasa-backend.jar

EXPOSE 8086

ENTRYPOINT [ "java", "-jar","agrisasa-backend.jar" ]
