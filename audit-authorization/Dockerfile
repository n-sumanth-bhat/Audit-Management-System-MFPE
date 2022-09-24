FROM openjdk:8-jdk-alpine
ADD target/auth-ms.jar auth-ms.jar
EXPOSE 8100
ENTRYPOINT ["java","-jar","/auth-ms.jar"]