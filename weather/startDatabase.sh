#! /bin/bash

docker stop weather-postgres
docker rm weather-postgres
docker run --name weather-postgres -p 15431:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testuser -d postgres
