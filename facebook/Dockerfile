FROM openjdk:11-jre-slim
#RUN apk --no-cache add netcat-openbsd
#RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*
COPY target/facebook-0.0.1.jar /facebook-0.0.1.jar
CMD  java -jar /facebook-0.0.1.jar