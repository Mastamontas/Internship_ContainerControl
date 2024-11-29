#amazon correto jdk 17
FROM openjdk:17-alpine

#set working directory inside container
WORKDIR /app

#copy springboot jar file to container
COPY target/DEVLOP_ContainerControl_Project-0.0.1-SNAPSHOT.jar /app/app.jar
#expose port 8080
EXPOSE 8080

#entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

LABEL authors="pedro.monteiro"

