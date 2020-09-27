#docker pull openjdk:8-jre
#docker run -it --entrypoint bash openjdk:8-jre
#Java -version
FROM openjdk:8-jre
MAINTAINER liuyao
COPY target/user-thrift-service-1.0-SNAPSHOT.jar /user-thrift-service.jar
ENTRYPOINT [ "java", "-jar", "/user-thrift-service.jar" ]


