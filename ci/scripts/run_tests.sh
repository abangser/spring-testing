#!/usr/bin/env bash

set -e -u -x

cd spring-testing
./gradlew build

ls
ls build/