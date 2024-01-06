FROM openjdk:11-jre-slim
COPY target/notification-0.0.1.jar /notification-0.0.1.jar
#CMD java -cp /notification-0.0.1.jar service.NotificationApplication $container_name
CMD ["java", "-jar", "/notification-0.0.1.jar"]