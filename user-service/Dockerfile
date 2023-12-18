FROM amazoncorretto:17

ARG JAR_APP=target/*.jar

WORKDIR /app

COPY ${JAR_APP} /app/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]