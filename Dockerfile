FROM openjdk:11-slim-buster
WORKDIR /app
COPY /target/apivendas-*.jar /app/apivendas.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","apivendas.jar"]
