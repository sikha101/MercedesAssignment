package com.mercedes.MercMicroservice1.service;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.databind.json.JsonMapper.Builder;
import com.google.protobuf.Struct;
import com.google.protobuf.util.JsonFormat;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


@Profile("send")
public class Send {
	static Logger logger = LoggerFactory.getLogger(Send.class);

	private final static String QUEUE_NAME = "storeQ";

	public static void sendMethod(String data, String fileType) throws IOException, TimeoutException  {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		data = data.toString()+fileType;
		String BasicBase64format= Base64.getEncoder().encodeToString(data.getBytes());
		channel.basicPublish("", QUEUE_NAME, null, BasicBase64format.getBytes());
		logger.info("[!] Sent '" + "data json" + "'");
		channel.close();
		connection.close();
	}
}
