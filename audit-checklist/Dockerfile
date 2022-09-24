FROM openjdk:8-jdk-alpine
ADD target/checklist-ms.jar checklist-ms.jar
EXPOSE 8200
ENTRYPOINT ["java","-jar","/checklist-ms.jar"]