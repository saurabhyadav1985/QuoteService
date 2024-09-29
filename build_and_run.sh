#!/bin/bash

# Step 1: Build the Maven project
echo "Building the Maven project..."
./mvnw clean package

# Step 2: Build the Docker image
echo "Building the Docker image..."
docker build -t quote-service:latest .

# Step 3: Start the Docker containers
echo "Starting the Docker containers..."
docker-compose --env-file .env up --build