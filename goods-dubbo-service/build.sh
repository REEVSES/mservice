#!/usr/bin/env bash

mvn package
#创建镜像
docker build -t goods-service:latest .
#运行镜像
#docker run -idt -p 7911:7911 user-service:latest \
# --mysql.address=10.71.2.207
