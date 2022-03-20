FROM java:11
WORKDIR app
ADD /target/commandeApp-1.0.jar commandeApp-1.0.jar
ENTRYPOINT ["java","-jar","commandeApp-1.0.jar"]
EXPOSE 8081