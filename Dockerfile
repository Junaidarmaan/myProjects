# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the local machine to the container
COPY target/whatsappclone-0.0.1-SNAPSHOT.jar whatsappclone.jar

# Expose the port your Spring Boot application is running on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "whatsappclone.jar"]
