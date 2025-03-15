#!/bin/bash

set -e

COMPOSE_FILE="docker-compose-service.yml"

echo "Stopping and removing existing containers..."
docker-compose -f $COMPOSE_FILE down --rmi all

echo "Building new images without cache..."
docker-compose -f $COMPOSE_FILE build --no-cache

echo "Starting containers with new images..."
docker-compose -f $COMPOSE_FILE up -d

echo "Deployment completed successfully!"