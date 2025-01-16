# Step 1: Use the official OpenJDK base image for Java 17 (or your desired version)
FROM openjdk:17-jdk-slim

# Step 2: Set the working directory inside the container
WORKDIR /app

# Step 3: Copy the Spring Boot JAR file from the local machine to the container
COPY build/libs/AccountsMs.jar app.jar

# Step 4: Expose the port that the Spring Boot app will run on
EXPOSE 8081

# Step 5: Run the Spring Boot application using 'java -jar'
CMD ["java", "-jar", "app.jar"]