package com.mercedes.MercMicroservice2.service;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@Profile("send")
public class Send {
	static Logger logger = LoggerFactory.getLogger(Send.class);

	private final static String QUEUE_NAME = "ReadingQ";

	public static void sendMethod(String res) throws IOException, TimeoutException  {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String BasicBase64format= Base64.getEncoder().encodeToString(res.getBytes());
		channel.basicPublish("", QUEUE_NAME, null, BasicBase64format.getBytes());
		logger.info("[!] Sent '" + "last data json" + "'");
		channel.close();
		connection.close();
	}
}
