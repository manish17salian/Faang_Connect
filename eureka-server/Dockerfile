FROM openjdk:11-jre-slim
EXPOSE 8761
COPY target/eureka-server-0.0.1.jar /eureka-server-0.0.1.jar
# CMD java -jar /eureka-server-0.0.1.jar

COPY wait.sh /wait.sh
RUN chmod +x /wait.sh
