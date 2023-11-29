#!/bin/bash
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[0;33m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
WHITE='\033[0;37m'
RESET='\033[0m'

echo -e $GREEN "이 스크립트는 UNISPHERE-SERVER 루트 디렉토리에서 실행되어야 합니다!!" $RESET

docker compose down
docker compose up --build -d

chmod +x ./gradlew
./gradlew build -x test

java -Dspring.profiles.active=local -jar build/libs/unisphere-0.0.1-SNAPSHOT.jar
