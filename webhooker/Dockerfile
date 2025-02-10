# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/${JAR_FILE} /app/app.jar

# Make port available to the world outside this container
EXPOSE ${APP_PORT}

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

