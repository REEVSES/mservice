#!/usr/bin/env bash

mvn package
docker build -t goods-edge-service:latest .

#docker run -idt -p 7911:7911 imooc-user-service:latest \
# --mysql.address=10.71.2.207
