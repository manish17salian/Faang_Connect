FROM openjdk:8
#RUN apk --no-cache add netcat-openbsd
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*
#RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*
COPY target/amazon-0.0.1.jar /amazon-0.0.1.jar
CMD java -jar /amazon-0.0.1.jar
