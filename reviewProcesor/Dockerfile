# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY build/libs/reviewProcesor-0.0.1.jar app.jar

# Expose the default Spring Boot port
EXPOSE 5051

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

