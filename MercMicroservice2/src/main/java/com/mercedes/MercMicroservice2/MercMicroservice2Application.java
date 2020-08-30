package com.mercedes.MercMicroservice2;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.mercedes.MercMicroservice2.service.ReceiveRead;
import com.mercedes.MercMicroservice2.service.dataServiceImpl2;

@SpringBootApplication
public class MercMicroservice2Application {

	public static String request;

	public static void called() throws IOException, TimeoutException, InterruptedException {

		System.out.println("in called method:" + MercMicroservice2Application.request);
		if (MercMicroservice2Application.request == null) {
			System.out.println("No Request Passed");
			ReceiveRead.receiveMethod();
		} else if (MercMicroservice2Application.request.contains("store")) {
			System.out.println("calling store");
			dataServiceImpl2.store2();
		} else if (MercMicroservice2Application.request.contains("update")) {
			System.out.println("calling update");
			dataServiceImpl2.update2();
		} else if (MercMicroservice2Application.request.contains("read")) {
			System.out.println("calling read");
			String ft = MercMicroservice2Application.request.split(":")[1];
			dataServiceImpl2.read2(ft);
		}
		System.out.println("Checking if more requests are passed");
		ReceiveRead.receiveMethod();
		called();
	}

	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		SpringApplication.run(MercMicroservice2Application.class, args);
		ReceiveRead.receiveMethod();
		called();
	}

}
