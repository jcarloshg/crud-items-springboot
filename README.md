
# CRUD Items Spring Boot Backend

This project is a Spring Boot backend for managing personal information, skills, education, and experience. It is designed as a RESTful API with validation, PostgreSQL database integration, and Docker support. The backend is suitable for use in portfolio or resume web applications.

---

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Database Schema & Migrations](#database-schema--migrations)
- [Running with Docker](#running-with-docker)
- [API Endpoints](#api-endpoints)
- [Validation](#validation)
- [Testing](#testing)
- [License](#license)

---

## Features
- RESTful CRUD operations for:
  - Personal Info
  - Skills
  - Education
  - Experience
- Input validation using `jakarta.validation`
- PostgreSQL database with schema and seed data
- Dockerized for easy development and deployment
- Hot reload support with Spring DevTools

---

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/com/crud_items/crud_items_back/
│   │   │   ├── model/         # Entity classes
│   │   │   ├── repository/    # Data access layer
│   │   │   ├── service/       # Business logic
│   │   │   ├── rest/          # REST controllers
│   │   │   └── exception/     # Custom exceptions & handlers
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/error/validation.html
│   └── test/
│       └── java/com/crud_items/crud_items_back/CrudItemsBackApplicationTests.java
├── SERVICES/
│   ├── db/                    # Database Dockerfile & migrations
│   └── DOCS/                  # HTTP request samples for API
├── Dockerfile                 # Backend Dockerfile
├── docker-compose.yml         # Multi-service Docker Compose
├── pom.xml                    # Maven build file
└── README.md                  # Project documentation
```

---

## Getting Started

### Prerequisites
- Java 21+
- Maven 3.9+
- Docker & Docker Compose

### Build & Run (Locally)
```sh
# Build the project
./mvnw clean install

# Run the Spring Boot app
./mvnw spring-boot:run
```

### Run with Docker Compose
```sh
docker-compose up --build
```
This will start the PostgreSQL database. To run the backend in Docker, uncomment the `back` service in `docker-compose.yml` and rebuild.

---

## Configuration

Edit `src/main/resources/application.properties` to set database connection details:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/main
spring.datasource.username=admin
spring.datasource.password=123456
```

---

## Database Schema & Migrations

Database schema and seed data are in:
- `SERVICES/db/migrations/01.schema.sql` (tables)
- `SERVICES/db/migrations/02.data.sql` (sample data)

The database includes:
- `personal_info` (main profile)
- `skills` (linked to personal_info)
- `educations` (linked to personal_info)
- `experiences` (linked to personal_info)

---

## Running with Docker

### Backend (Dev Hot Reload)
```sh
docker build -t crud-items-back .
docker run -p 8080:8080 --env SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/main \
  --env SPRING_DATASOURCE_USERNAME=admin --env SPRING_DATASOURCE_PASSWORD=123456 crud-items-back
```

### Database
```sh
cd SERVICES/db
docker build -t crud-items-db .
docker run -p 5432:5432 crud-items-db
```

Or use `docker-compose up` from the project root.

---

## API Endpoints

### Personal Info
- `GET    /api/test-personal-info/all` — List all
- `GET    /api/test-personal-info/{id}` — Get by ID
- `POST   /api/test-personal-info` — Create
- `PUT    /api/test-personal-info/{id}` — Update
- `DELETE /api/test-personal-info/{id}` — Delete

### Skills
- `GET    /api/skills/all` — List all
- `GET    /api/skills/{id}` — Get by ID
- `POST   /api/skills` — Create
- `PUT    /api/skills/{id}` — Update
- `DELETE /api/skills/{id}` — Delete

### Education
- `GET    /api/education/all` — List all
- `GET    /api/education/{id}` — Get by ID
- `POST   /api/education` — Create
- `PUT    /api/education/{id}` — Update
- `DELETE /api/education/{id}` — Delete

### Experience
- `GET    /api/experience/all` — List all
- `GET    /api/experience/{id}` — Get by ID
- `POST   /api/experience` — Create
- `PUT    /api/experience/{id}` — Update
- `DELETE /api/experience/{id}` — Delete

#### Example HTTP Requests
See `SERVICES/DOCS/*.http` for ready-to-use API request samples.

---

## Validation

This project uses `jakarta.validation.constraints` for input validation. Common annotations:

- `@NotNull` — Value must not be null
- `@NotBlank` — String must not be null/empty
- `@Min` / `@Max` — Numeric bounds
- `@Email` — Valid email address
- `@PastOrPresent` — Date must be in the past or present

See the model classes for detailed validation rules.

---

## Testing

Run tests with:
```sh
./mvnw test
```

---

## License

This project is for educational/demo purposes. No specific license.
