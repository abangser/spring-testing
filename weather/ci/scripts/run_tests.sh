#!/usr/bin/env bash

set -e -u -x

cd spring-testing/weather
./gradlew build --debug