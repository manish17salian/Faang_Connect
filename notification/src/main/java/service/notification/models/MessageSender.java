package service.notification.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	private final RabbitTemplate rabbitTemplate;
	private final List<String> notificationMessages;

	// public void setupCallbacks() {
    //     System.out.println("Called Setup");
	// 	rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
	// 		if (ack) {
	// 			System.out.println("Message sent successfully");
	// 		} else {
	// 			System.out.println("Message sending failed due to " + cause);
	// 		}
	// 	});

	// 	rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
    //         System.out.println("Returned message: " + new String(message.getBody()));
    //     });
	// }

	@Autowired
	public MessageSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.notificationMessages = new ArrayList<>();
	}


	// @PostConstruct
    // public void init() {
    //     System.out.println("called Init");
    //     setupCallbacks();
    // }


	public void sendMessage(String queueName, String message) {
		System.out.println(queueName + "/" +message);
		rabbitTemplate.convertAndSend("", queueName, "Hello My Name is Manish");
		String companyName = extractCompanyName(queueName);

		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	     String currentDate = dateFormat.format(new Date());

		String notificationMessage = "Successfully applied for the position '" + message + " in " + companyName
				+ " dated " + currentDate;
		System.out.println(notificationMessage);
		notificationMessages.add(notificationMessage);
	}


	public List<String> getNotificationMessages() {
		return new ArrayList<>(notificationMessages);
	}

	private String extractCompanyName(String queueName) {
		int index = queueName.lastIndexOf("JobQueue");

		if (index != -1) {
			return queueName.substring(0, index);
		} else {
			return queueName;
		}
	}
}
