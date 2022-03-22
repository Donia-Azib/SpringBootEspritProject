FROM java:8
#EXPOSE 8081
EXPOSE 8082
WORKDIR app
ADD /target/auth-1.0.jar auth-1.0.jar
ENTRYPOINT ["java","-jar","auth-1.0.jar"]
#CMD echo "sleeping for 10s" sleep 10 && java -jar auth-1.0.jar
