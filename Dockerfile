# Development Dockerfile with hot reload
FROM maven:3.9.9-eclipse-temurin-21
WORKDIR /app

# Copy Maven wrapper and pom.xml first for better caching
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Download dependencies (cached layer)
RUN mvn dependency:go-offline -B

# Copy source code (will be overwritten by volume mount)
COPY src ./src

# Expose port 8080
EXPOSE 8080

# Run with spring-boot:run for hot reload support
# DevTools will automatically restart when files change
ENTRYPOINT ["mvn", "spring-boot:run", "-Dspring-boot.run.jvmArguments=-Dspring.devtools.restart.enabled=true -Dspring.devtools.livereload.enabled=true"]
