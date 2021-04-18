FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/ssm-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ssm-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/ssm-0.0.1-SNAPSHOT.jar"]