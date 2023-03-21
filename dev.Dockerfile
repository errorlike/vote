FROM eclipse-temurin:17-jdk-alpine
WORKDIR /user/service
VOLUME /tmp
COPY target/*.jar app.jar
ENV spring.datasource.url=jdbc:mysql://172.26.227.173:3306/vote
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]