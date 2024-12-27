FROM openjdk:17
WORKDIR /app
COPY ./target/demo.jar /app
EXPOSE 8080
CMD ["java", "-jar", "AWS_CICD.jar"]