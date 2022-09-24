FROM openjdk:8-jdk-alpine
ADD target/severity-ms.jar severity-ms.jar
EXPOSE 8300
ENTRYPOINT ["java","-jar","/severity-ms.jar"]