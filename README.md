# Reviews Microservices Platform

One-liner: **A distributed microservices platform for managing, processing, and analyzing user reviews, built with Java, Spring Boot, gRPC, Kafka, MongoDB, PostgreSQL, and Docker.**

---

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)
![Java](https://img.shields.io/badge/java-21-blue)
![Spring Boot](https://img.shields.io/badge/spring--boot-3.5.3-brightgreen)

---

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Architecture or Project Structure](#architecture-or-project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgments](#acknowledgments)

---

## Features
- Modular microservices: **reviewWrite**, **reviewRead**, **reviewApi**, **reviewProcesor**, **eureka** (service discovery)
- REST and gRPC APIs for review management
- Event-driven architecture with Kafka and Debezium
- MongoDB for read-side storage, PostgreSQL for write-side
- Schema migrations with Flyway
- Service discovery with Eureka
- API documentation with Swagger/OpenAPI
- Docker and Docker Compose for local development
- Kafka UI and monitoring tools included

---

## Getting Started

### Prerequisites
- Java 21
- Gradle 8+
- Docker & Docker Compose
- (Optional) grpcurl, Postman, or similar for gRPC testing

### Clone the Repository
```bash
git clone https://github.com/your-org/reviews.git
cd reviews
```

### Start Infrastructure (DB, Kafka, Zookeeper, etc.)
```bash
docker-compose -f Docker-compose.yml up -d
```

### Build All Services
```bash
./gradlew clean build
```

### Run Services (Example: reviewWrite)
```bash
cd reviewWrite
./gradlew bootRun
```
Or run with Docker:
```bash
docker build -t review-write .
docker run -p 5153:5153 review-write
```

Repeat for other services (reviewRead, reviewApi, reviewProcesor, eureka).

### Run Tests
```bash
./gradlew test
```

---

## Usage

### REST API Example (reviewApi)
```bash
curl -X GET http://localhost:5049/api/v1/reviews
```

### gRPC Example (reviewWrite)
```bash
grpcurl -plaintext localhost:5153 list
```

### Swagger UI
- Visit: [http://localhost:4090/swagger-ui.html](http://localhost:8080/swagger-ui.html) (or the port for your service)

### Kafka UI
- Visit: [http://localhost:8080](http://localhost:8080) (default Kafka UI port)

---

## Architecture or Project Structure

```
reviews/
├── Docker-compose.yml
├── eureka/           # Eureka service discovery
├── reviewApi/        # API gateway (REST, gRPC, Swagger)
├── reviewWrite/      # Write-side microservice (PostgreSQL, gRPC, Kafka)
├── reviewRead/       # Read-side microservice (MongoDB, REST)
├── reviewProcesor/   # Event processor (Kafka, Debezium)
├── config/           # Shared configuration
└── db/               # Database volumes
```

<details><summary>Click to expand: Example Java package structure</summary>

```
reviewWrite/
└── src/main/java/com/yinnoh/reviwts/reviewWrite/
    ├── application/
    ├── domain/
    ├── infrastructure/
    └── ...
```
</details>

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to your fork (`git push origin feature/your-feature`)
5. Open a Pull Request

Please read our [Code of Conduct](CODE_OF_CONDUCT.md) and [Contribution Guidelines](CONTRIBUTING.md) before contributing.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Acknowledgments
- [Spring Boot](https://spring.io/projects/spring-boot)
- [gRPC](https://grpc.io/)
- [Kafka](https://kafka.apache.org/)
- [MongoDB](https://www.mongodb.com/)
- [PostgreSQL](https://www.postgresql.org/)
- [Debezium](https://debezium.io/)
- [Swagger/OpenAPI](https://swagger.io/)
- [Provectus Kafka UI](https://github.com/provectus/kafka-ui)

