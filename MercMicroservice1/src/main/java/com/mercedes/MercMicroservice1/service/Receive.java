package com.mercedes.MercMicroservice1.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import com.mercedes.MercMicroservice1.MercMicroservice1Application;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@Profile("receive")
public class Receive {

	static Logger logger = LoggerFactory.getLogger(Receive.class);
	private final static String QUEUE_NAME = "ReadingQ";

	public static void receiveMethod() throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		com.rabbitmq.client.Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		logger.info("[!] Waiting for messages. To exit press Ctrl+C");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {

				String message = new String(body, "UTF-8");
				logger.info("[x] Message Recieved - " + message);

				byte[] byteArray = Base64.decodeBase64(message.getBytes());
				String decodedString = new String(byteArray);
				System.out.println("########## Data Received from the file is: ######### "+decodedString);
				MercMicroservice1Application.lastData = decodedString;
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
