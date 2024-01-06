package service.notification.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	private final RabbitTemplate rabbitTemplate;
	private final List<String> notificationMessages;

	@Autowired
	public MessageSender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.notificationMessages = new ArrayList<>();
	}

	public void sendMessage(String queueName, String message) {
		rabbitTemplate.convertAndSend(queueName, message);
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
