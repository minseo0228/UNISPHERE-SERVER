#!/bin/bash

cd /home/ec2-user

mkdir -p /home/ec2-user/deploy/zip
cd /home/ec2-user/deploy/zip/

docker-compose down
docker-compose pull
docker-compose up -d
