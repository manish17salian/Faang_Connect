FROM openjdk:11-jre-slim
COPY target/employee-0.0.1.jar /employee-0.0.1.jar
EXPOSE 8080
# CMD java -jar /employee-0.0.1.jar


COPY wait.sh /wait.sh
RUN chmod +x wait.sh