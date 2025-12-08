# CRUD Items Spring Boot Project

## Overview

This project is a Spring Boot-based RESTful API for managing personal information, skills, education, and experience records. It uses PostgreSQL as the database and supports full CRUD operations for each entity. The application is containerized with Docker for both development and production environments.

- 🧩 Data Replication, 🧩 Criteria Pattern, 🛑 Domain Driven Design, 🧪 Unit & Integration Testing, 🏗️ Clean Architecture, 🔌 Connection Pooling, 🔄 Streaming Replication, ⚖️ Load Balancing
- 🐳 Docker, 🐘 PostgreSQL, ☕ Java 21, 🟦 Spring Boot 4, 🧪 JUnit 5, 🛡️ Jakarta Bean Validation, 🖥️ Maven, 📄 Thymeleaf, 🗄️ Spring Data JDBC

## Features

- **RESTful API** for Personal Info, Skills, Education, and Experience
- **Validation** using Spring Boot's validation framework
- **Database migrations** with SQL scripts
- **Integration and unit tests** with JUnit and Spring Boot Test
- **Dockerized** for easy development and deployment
- **Thymeleaf error templates** for validation errors

## Architecture

- **Backend:** Java 21, Spring Boot 4, Maven
- **Database:** PostgreSQL
- **Persistence:** Spring Data JDBC with custom repositories
- **Validation:** Jakarta Bean Validation
- **Error Handling:** Global exception handler with Thymeleaf templates
- **Testing:** JUnit 5, Spring Boot Test

## Project Structure

```
src/
  main/
    java/com/crud_items/crud_items_back/
      model/         # Entity classes
      repository/    # Data access layer
      service/       # Business logic
      rest/          # REST controllers
      exception/     # Custom exceptions and handlers
    resources/
      application.properties
      templates/error/validation.html
  test/
    java/com/crud_items/crud_items_back/
      integration/   # Integration tests
      unit/          # Unit tests
SERVICES/
  db/
    Dockerfile
    migrations/
      01.schema.sql
      02.data.sql
  DOCS/
    *.http           # Example API requests
Dockerfile.dev
Dockerfile.prod
docker-compose.dev.yml
docker-compose.prod.yml
pom.xml
README.md
```

## Database Schema

See `SERVICES/db/migrations/01.schema.sql` for table definitions:

- `personal_info`
- `skills` (FK: personal_info_id)
- `educations` (FK: personal_info_id)
- `experiences` (FK: personal_info_id)

Sample data is provided in `02.data.sql`.

## API Endpoints

### Personal Info

- `GET /api/test-personal-info/all` — List all personal info
- `GET /api/test-personal-info/{id}` — Get personal info by ID
- `POST /api/test-personal-info` — Create new personal info
- `PUT /api/test-personal-info/{id}` — Update personal info
- `DELETE /api/test-personal-info/{id}` — Delete personal info

### Skills

- `GET /api/skills/all` — List all skills
- `GET /api/skills/{id}` — Get skill by ID
- `POST /api/skills` — Create new skill
- `PUT /api/skills/{id}` — Update skill
- `DELETE /api/skills/{id}` — Delete skill

### Education

- `GET /api/education/all` — List all education records
- `GET /api/education/{id}` — Get education by ID
- `POST /api/education` — Create new education
- `PUT /api/education/{id}` — Update education
- `DELETE /api/education/{id}` — Delete education

### Experience

- `GET /api/experience/all` — List all experiences
- `GET /api/experience/{id}` — Get experience by ID
- `POST /api/experience` — Create new experience
- `PUT /api/experience/{id}` — Update experience
- `DELETE /api/experience/{id}` — Delete experience

See `SERVICES/DOCS/*.http` for example requests.

## Setup & Running

### Prerequisites

- Java 21+
- Maven
- Docker & Docker Compose

### Local Development

1. **Start PostgreSQL with Docker Compose:**
   ```sh
   docker-compose -f docker-compose.dev.yml up db
   ```
2. **Run the Spring Boot app:**
   ```sh
   ./mvnw spring-boot:run
   ```
   Or use the development Dockerfile:
   ```sh
   docker build -f Dockerfile.dev -t crud-items-dev .
   docker run -p 8080:8080 --env SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/main crud-items-dev
   ```

### Production

1. **Build and run with Docker Compose:**
   ```sh
   docker-compose -f docker-compose.prod.yml up --build
   ```
   The production image uses a multi-stage build for optimized deployment.

### Configuration

Edit `src/main/resources/application.properties` for database credentials and connection settings.

## Testing

- **Run all tests:**
  ```sh
  ./mvnw test
  ```
- Integration and unit tests are located in `src/test/java/com/crud_items/crud_items_back/`.

## Error Handling

Validation errors are handled globally and rendered using the Thymeleaf template at `src/main/resources/templates/error/validation.html`.

## License

This project is for educational/demo purposes.
