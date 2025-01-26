# Use an official Maven image to build the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the project files to the working directory
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package

# Use a JDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

EXPOSE 8081

# Copy the built jar file from the Maven build stage
COPY --from=build /app/target/npr211.jar ./app.jar

# Specify the command to run the application
CMD ["java", "-jar", "app.jar"]