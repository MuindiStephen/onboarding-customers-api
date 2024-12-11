
FROM eclipse-temurin:11-jdk-jammy AS deps

COPY spring-boot-user-microservice.jar agrisasa-backend-application.jar

EXPOSE 8086

ENTRYPOINT [ "java", "-jar","agrisasa-backend-application.jar" ]
