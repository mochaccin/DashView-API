# Use a base image with Java pre-installed
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR .

# Copy over source code
COPY ./target/viewdash-0.0.1-SNAPSHOT.jar .

EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "viewdash-0.0.1-SNAPSHOT.jar"]