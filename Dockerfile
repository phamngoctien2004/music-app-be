# Use a base JDK image
FROM eclipse-temurin:17-jdk-jammy as build

# Set work directory
WORKDIR /app

# Copy jar file (adjust the path and name if needed)
COPY target/*.jar app.jar

# Expose the port that your Spring Boot app uses
EXPOSE 8080

# Command to run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]