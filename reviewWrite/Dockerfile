# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY build/libs/reviewWrite-0.0.1.jar app.jar

# Expose the correct Spring Boot port (from application.yml)
EXPOSE 5153

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

