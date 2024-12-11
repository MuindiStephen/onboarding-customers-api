
FROM eclipse-temurin:11-jdk-jammy AS deps

COPY spring-boot-agrisasa.jar agrisasa-backend.jar

ENTRYPOINT [ "java", "-jar","agrisasa-backend.jar" ]
