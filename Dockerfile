# Use Amazon Corretto JDK 21 as the base image
FROM amazoncorretto:21-alpine as build

# Set working directory
WORKDIR /app

# Copy your project files into the container
COPY . .

# Install dependencies and build the project
RUN mvn clean install

# Use a lightweight base image for the final container
FROM openjdk:21-alpine

# Set working directory
WORKDIR /app

# Copy the build artifacts from the previous stage
COPY --from=build /app/target/whatsappclone-1.0-SNAPSHOT.jar /app/whatsappclone.jar

# Expose the port your application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "whatsappclone.jar"]
