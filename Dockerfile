FROM maven:3.8.3-jdk-11-slim as BUILD_DBC
COPY src /dbc/src
COPY pom.xml /dbc
RUN mvn -f /dbc/pom.xml clean package

FROM openjdk:11-jre-slim
RUN mkdir /app
COPY --from=BUILD_DBC /dbc/target/*.jar /app/dbc-spring-boot-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/app/dbc-spring-boot-app.jar"]