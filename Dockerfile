# Development Dockerfile with hot reload
FROM maven:3.9.9-eclipse-temurin-21
WORKDIR /app

# Copy Maven wrapper and pom.xml first for better caching
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Build dependencies (for better layer caching)
RUN ./mvnw clean install -DskipTests

# Copy source code (will be overwritten by volume mount)
COPY src ./src

RUN ./mvnw clean install 

# Expose port 8080
EXPOSE 8080
