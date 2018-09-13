#! /bin/bash

docker stop person-postgres
docker rm person-postgres
docker run --name person-postgres -p 15432:5432 -e POSTGRES_PASSWORD=password -e POSTGRES_USER=testuser -d postgres
