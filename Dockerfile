FROM openjdk:11-jdk-slim
ADD target/cook-book-app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar cook-book-app-0.0.1-SNAPSHOT.jar
