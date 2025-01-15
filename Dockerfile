# Use Amazon Corretto 21 JDK as base image
FROM amazoncorretto:21-alpine

# Install Maven
RUN apk add --no-cache curl bash
RUN curl -sL https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz | tar xz -C /opt
RUN ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/local/bin/mvn

# Set the working directory in the container
WORKDIR /app

# Copy your pom.xml and project files
COPY . .

# Install dependencies and build the project
RUN mvn clean install

# Use a standard OpenJDK 21 base image for the final container
FROM amazoncorretto:21-alpine

# Set the working directory in the final image
WORKDIR /app

# Copy the jar from the build stage
COPY --from=0 /app/target/whatsappclone-0.0.1-SNAPSHOT.jar /app/whatsappclone.jar

# Expose the port your app will run on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/whatsappclone.jar"]
