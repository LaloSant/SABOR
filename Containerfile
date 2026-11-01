FROM docker.io/eclipse-temurin:25-jre-alpine

WORKDIR /sabor

COPY target/*.jar sabor-app.jar

EXPOSE 5080

ENTRYPOINT ["java", "-jar", "sabor-app.jar"]


#Para construir 
#podman build -t sabor .

#Para correr primera vez

# podman run  \
#     --name sabor \
#     -p 5080:5080  \
#     -e DB_URL='jdbc:oracle:thin:@//host.containers.internal:1521/freepdb1' \
#     -e DB_USER=RECETARIO  \
#     -e DB_PASSWORD=FISISGP   \
#     sabor:latest

# podman run  \
#     --name sabor \
#     -p 5080:5080  \
#     sabor:latest

# Para ejecutar despues
# podman start sabor

# podman stop sabor