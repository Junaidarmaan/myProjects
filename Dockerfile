# Use an official Maven base image to build the app
FROM maven:3.8.4-openjdk-17-slim AS build

# Set working directory
WORKDIR /app

# Copy the pom.xml and the source code into the container
COPY pom.xml .
COPY src ./src

# Run Maven build to create the target directory
RUN mvn clean install

# Use a different base image for the final container
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/my-app.jar /app/my-app.jar

# Command to run the app
CMD ["java", "-jar", "/app/my-app.jar"]
