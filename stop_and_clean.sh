#!/bin/bash

# Step 1: Stop the Docker Compose services
echo "Stopping Docker Compose services..."
docker-compose down

# Step 2: Remove the quote-service image
echo "Removing the quote-service image..."
docker rmi quote-service:latest

# Step 3: Remove all volumes
echo "Removing all Docker volumes..."
docker volume rm quoteservice_postgres_data
