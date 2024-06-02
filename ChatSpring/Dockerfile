FROM --platform=linux/amd64 openjdk:17
ARG JAR_FILE=target/*.jar
COPY ./target/ChatSpring-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Xmx512m","-jar","/app.jar"]