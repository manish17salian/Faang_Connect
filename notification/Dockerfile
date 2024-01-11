FROM openjdk:11-jre-slim
EXPOSE 8087

COPY target/notification-0.0.1.jar /notification-0.0.1.jar
COPY wait.sh /wait.sh

RUN chmod +x wait.sh
#CMD java -cp /notification-0.0.1.jar service.NotificationApplication $container_name
# CMD ["java", "-jar", "/notification-0.0.1.jar"]