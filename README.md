# Quote Service

## Introduction

This project is a Spring Boot application that provides a REST API for managing quotes.

## Prerequisites

- Docker
- Java 21

## Building and Running the Project with Docker

1. **Build the JAR file:**
   ```sh
   ./mvnw clean package

2. **Build the Docker image:**
   ```sh
   docker build -t quote-service .

3. **Run the Docker container:**
    ```sh
    docker run -p 8080:8080 quote-service

4. **Access the application:**
   Open your browser and navigate to http://localhost:8080.

5. **API Endpoints**
Refer QuoteRequests.http file for API endpoints and sample requests.
