# Quote Service

## Introduction

This project is a Spring Boot application that provides a REST API for managing quotes.

## Prerequisites

- Docker
- Java 21
- Git

## Building and Running the Project with Docker

1. **Move to base directory and build the JAR file:**
   ```sh
   ./mvnw clean package

2. **Build and run app**
   ```sh
   docker build -t quote-service .  

3. **Create .env file in base directory and select appropriate user and password**
   ```text
   #Postgres DB credentials
   DATABASE_USER=pguser
   DATABASE_PASSWORD=pgpassword
   
   #Quote API user credentials
   QUOTE_READER_USER=qruser
   QUOTE_READER_PASSWORD=qrpassword
   
   #Quote API admin credentials
   QUOTE_ADMIN_USER=admin
   QUOTE_ADMIN_PASSWORD=adminpasword

4. **Start the application**
   ```sh
   docker compose up

5. **API Endpoints**

### Create a Quote

POST http://localhost:8080/api/v1/quotes
Content-Type: application/json

{
"author": "Saurabh",
"content": "This is a quote application."
}

### Get All Quotes (Paginated)

GET http://localhost:8080/api/v1/quotes?page=0&size=5

### Get Quotes by Author (Paginated)

GET http://localhost:8080/api/v1/quotes/author?author=Saurabh&page=0&size=5
   
