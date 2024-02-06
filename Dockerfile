FROM openjdk:17
EXPOSE 8010
ARG JAR_FILE=./target/Movie-Portal.jar
COPY $JAR_FILE app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
