# Spring Boot Kafka Project README

## Overview

This project integrates Spring Boot with Apache Kafka to produce and consume `Employee` objects. It includes:

- **Producer**: Sends `Employee` objects to a Kafka topic.
- **Consumer**: Listens to the Kafka topic to process `Employee` objects.

## Prerequisites

- Docker
- Docker Compose
- Java (as per `pom.xml` or `build.gradle`)
- Maven or Gradle

## Project Structure

```plaintext
project-root/
├── docker-compose.yml
├── src/
│   ├── main/
│   │   ├── java/com/pawan07thapa/kafka/spring_kafka_avro/
│   │   │   ├── Application.java
│   │   │   ├── controller/EmployeeController.java
│   │   │   ├── model/Employee.java
│   │   │   ├── producer/EmployeeDataProducer.java
│   │   │   └── consumer/EmployeeDataConsumer.java
│   │   └── resources/application.yml
├── pom.xml (or build.gradle)
└── README.md
```

## Running Locally with Docker Compose

1. **Start Kafka Ecosystem:**

   ```
   docker compose up -d

2. **Run Spring Boot Application:**

   ```
    mvn spring-boot:run
   
3. **Testing:**

 - **Producer:** Send a POST request to `http://localhost:8080/employee/` with an `Employee` JSON payload using Postman.
 - **Consumer:** Check logs for consumed messages.

4. **Monitoring:**

- Access Kafka Control Center at `http://localhost:9021`.

5. **Stopping the Project**
   ```
   docker compose down
   
### Notes

- Ensure application.yml points to localhost:9092 for Kafka.
- For issues, check Docker logs with docker logs <container_name>.