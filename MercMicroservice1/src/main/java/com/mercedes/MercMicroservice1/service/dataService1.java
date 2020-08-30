package com.mercedes.MercMicroservice1.service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.springframework.stereotype.Component;

@Component
public interface dataService1 {

	public void store1(String data, String fileType) throws IOException, TimeoutException;

	public void update1(String data, String fileType) throws IOException, TimeoutException;

	public void read1() throws IOException, TimeoutException;
}
