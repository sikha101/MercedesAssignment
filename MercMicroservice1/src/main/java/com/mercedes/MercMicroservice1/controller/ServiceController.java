package com.mercedes.MercMicroservice1.controller;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mercedes.MercMicroservice1.MercMicroservice1Application;
import com.mercedes.MercMicroservice1.service.Receive;
import com.mercedes.MercMicroservice1.service.SendRead;
import com.mercedes.MercMicroservice1.service.dataService1;

@RestController
public class ServiceController {

	@Autowired
	dataService1 service;
	
	public static String fileType;
	
	@PostMapping("/store")
	public void store(@Valid @RequestBody String data, @Valid @RequestParam String fileType)
			throws IOException, TimeoutException {
		ServiceController.fileType = fileType;
		SendRead.sendReadMethod("store:"+fileType);
		service.store1(data, fileType);
	}

	@PutMapping("/update")
	public void update(@Valid @RequestBody String data)
			throws IOException, TimeoutException {
		SendRead.sendReadMethod("update:"+fileType);
		service.update1(data, fileType);
	}

	@GetMapping("/read")
	public String read() throws IOException, TimeoutException {
		read_dup();
		return read_dup();
	}
	
	public String read_dup() throws IOException, TimeoutException {
		SendRead.sendReadMethod("read:"+fileType);
		Receive.receiveMethod();
		return MercMicroservice1Application.lastData;
	}
}
