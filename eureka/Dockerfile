# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY build/libs/eureka-0.0.1-SNAPSHOT.jar app.jar

# Expose the Eureka server port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

