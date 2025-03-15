#!/bin/bash

set -e

COMPOSE_FILE="docker-compose-infra_only.yml"
SERVICE_NAME="kafka-msa"

echo "Stopping and removing existing container: $SERVICE_NAME..."
docker-compose -f $COMPOSE_FILE rm -sf $SERVICE_NAME

echo "Building new image for $SERVICE_NAME..."
docker-compose -f $COMPOSE_FILE build --no-cache $SERVICE_NAME

echo "Starting container for $SERVICE_NAME..."
docker-compose -f $COMPOSE_FILE up -d $SERVICE_NAME

echo "Deployment of $SERVICE_NAME and topic creation completed successfully!"