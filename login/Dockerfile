FROM openjdk:11-jre-slim
EXPOSE 8090
COPY target/login-0.0.1.jar /login-0.0.1.jar

COPY wait.sh /wait.sh
RUN chmod +x wait.sh
# ENTRYPOINT ["java", "-jar", "/login-0.0.1.jar"]