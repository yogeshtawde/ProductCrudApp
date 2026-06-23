# Use Java 21 Runtime
FROM eclipse-temurin:21-jre

# Set Working Directory
WORKDIR /app

# Copy Jar File
COPY target/*.jar app.jar

# Expose Application Port
EXPOSE 8080

# Run Application
ENTRYPOINT ["java","-jar","app.jar"]