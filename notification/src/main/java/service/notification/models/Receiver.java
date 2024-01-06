package service.notification.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);
	private String receivedMessage;

	public void receiveMessage(byte[] message) {
		//System.out.println("Received <" + new String(message) + ">");
		this.receivedMessage = new String(message);
		latch.countDown();
	}

	public String getReceivedMessage() {
		return receivedMessage;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
