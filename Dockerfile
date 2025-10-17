FROM openjdk:latest
COPY ./target/classes/com /tmp/com
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEM-Group-1-1.0-SNAPSHOT.jar"]