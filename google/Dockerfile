FROM openjdk:11-jre-slim
#RUN apk --no-cache add netcat-openbsd
#RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*
COPY target/google-0.0.1.jar /google-0.0.1.jar
CMD java -jar /google-0.0.1.jar