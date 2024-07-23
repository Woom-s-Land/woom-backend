FROM openjdk:22-jdk-slim
WORKDIR /app
ARG JAR_FILE=/build/libs/wooms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/wooms.jar
ENTRYPOINT ["java", "-jar", "/app/wooms.jar"]