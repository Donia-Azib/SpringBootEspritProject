FROM openjdk:11
EXPOSE 8888
WORKDIR app
ADD /target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
ADD ./src/main/resources/centralRepo ./src/main/resources/centralRepo
ENTRYPOINT ["java","-jar","config-server-0.0.1-SNAPSHOT.jar"]
