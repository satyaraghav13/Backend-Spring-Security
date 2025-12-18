# ================================
# Stage 1: Build the application
# ================================
FROM eclipse-temurin:17-jdk-focal AS builder

# Set working directory
WORKDIR /app

# Copy Gradle files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Give execute permission to gradlew
RUN chmod +x gradlew

# Build executable JAR (skip tests for faster build)
RUN ./gradlew bootJar -x test


# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre-focal

# Set working directory
WORKDIR /app

# Copy jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
