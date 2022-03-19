FROM java:8
WORKDIR app
ADD /target/auth-1.0.jar auth-1.0.jar
ENTRYPOINT ["java","-jar","auth-1.0.jar"]
EXPOSE 8081
