FROM docker.io/eclipse-temurin:25-jre-alpine

WORKDIR /sabor

COPY target/*.jar sabor-app.jar

EXPOSE 5080

ENTRYPOINT ["java", "-jar", "sabor-app.jar"]
