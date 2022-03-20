FROM java:8
WORKDIR app
ADD /target/feedbackstat-1.0.jar feedbackstat-1.0.jar
ENTRYPOINT ["java","-jar","feedbackstat-1.0.jar"]
EXPOSE 8081