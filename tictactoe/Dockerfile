# Use an official Maven image to build the application
FROM maven:3.9.5-eclipse-temurin-21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Package the application (creates the .jar file)
RUN mvn clean package -DskipTests

# Use a lightweight Java runtime image for running the application
FROM eclipse-temurin:21-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/tictactoe-0.0.1-SNAPSHOT.jar app.jar

# Expose the application's port
EXPOSE 8080

# Set the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
