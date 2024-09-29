# Quote Service

## Introduction

This project is a Spring Boot application that provides a REST API for managing quotes.

## Prerequisites

- Docker
- Java 21
- Git

## Building and Running the Project with Docker

1. **Move to base directory and give execute permission to scripts:**
   ```sh
   chmod +x build_and_run.sh
   
   ```sh
   chmod +x stop_and_clean.sh

2. **Create .env file in base directory and select appropriate user and password**
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

3. **Build and run app**
   ```sh
   ./build_and_run.sh

4. **Generate Base64 encoded string for user authentication**
   ```sh
   #UserBase64EncoderString
   echo -n "User:Password" | base64
   ```
   
   ```sh
   #AdminBase64EncoderString
   echo -n "Admin:Password" | base64 
   ```
   
5. **API Endpoints**

### Create a Quote

Refer quotes.http file in the project for sample request and response. Update with base64 encoded string for user and admin authentication.
   
5. **Stop and clean app**
   ```sh
   ./stop_and_clean.sh