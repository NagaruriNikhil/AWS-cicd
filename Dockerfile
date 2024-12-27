FROM openjdk:17
WORKDIR /app
COPY ./target/AWS_CICD.jar /app
EXPOSE 8080
CMD ["java", "-jar", "AWS_CICD.jar"]