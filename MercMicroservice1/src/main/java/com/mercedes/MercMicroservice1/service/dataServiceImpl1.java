package com.mercedes.MercMicroservice1.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Component;

@Component
public class dataServiceImpl1 implements dataService1 {

	@Override
	public void store1(String data, String fileType) throws IOException, TimeoutException {
		Send.sendMethod(data, fileType);
	}

	@Override
	public void update1(String data, String fileType) throws IOException, TimeoutException {
		SendUpdate.sendUpdateMethod(data, fileType);

	}

	@Override
	public void read1() throws IOException, TimeoutException {
				
	}

}
