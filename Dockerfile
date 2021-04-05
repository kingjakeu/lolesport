FROM openjdk:11-jdk-slim
ARG JAR_FILE=bulid/libs/
COPY ${JAR_FILE} promode-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/promode-api-0.0.1-SNAPSHOT.jar"]