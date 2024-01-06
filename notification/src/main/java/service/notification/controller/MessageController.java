package service.notification.controller;

import service.NotificationApplication;
import service.notification.models.MessageSender;
import service.notification.models.Notification;
import service.notification.models.Receiver;

import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;
    private final MessageSender messageSender;

    @Autowired
    public MessageController(RabbitTemplate rabbitTemplate, Receiver receiver, MessageSender messageSender) {
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
        this.messageSender = messageSender;
    }

    @PostMapping(value = "/sendJobApplication", consumes = "application/json")
    public String sendJobApplication(@RequestBody Notification notification) {
    	System.out.println("Inside Job Application");
        rabbitTemplate.convertAndSend(NotificationApplication.topicExchangeName, "job.application", notification);

        try {
            receiver.getLatch().await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Received message: " + receiver.getReceivedMessage();
    }
    
    @PostMapping("/sendToQueue")
    public String sendToQueue(@RequestBody Map<String, String> payload) {
       String queueName = payload.get("queueName");
        String message = payload.get("message");

        messageSender.sendMessage(queueName, message);

        return "Message sent successfully!";
    }
    
    @GetMapping("/getNotificationMessages")
    public List<String> getNotificationMessages() {
    	List<String> messages = messageSender.getNotificationMessages();
        System.out.println("Notification Messages: " + messages);
        return messages;
    }
}
