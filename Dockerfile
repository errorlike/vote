FROM eclipse-temurin:17-jdk-alpine
WORKDIR /user/service
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","app.jar"]