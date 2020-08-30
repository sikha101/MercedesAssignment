package com.mercedes.MercMicroservice2.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class dataServiceImpl2 implements dataService2 {

	public static void store2() throws IOException, TimeoutException {
		System.out.println("calling store method of MS2");
		Receive.receiveMethod();
	}

	public static void update2() throws IOException, TimeoutException {
		System.out.println("calling update method of MS2");
		ReceiveUpdate.receiveUpdateMethod();

	}

	public static void read2(String ft) throws IOException, FileNotFoundException, TimeoutException {
		
		String loc="D:/Mercedes_file.";
		
		String fname = loc + ft.toLowerCase();
		System.out.println(fname);
		String res = "";
		File f = new File(fname);
		if (f.exists()) {

			FileReader fr = new FileReader(fname);
			int i;
			while ((i = fr.read()) != -1)
				res = res + (char) i;
			fr.close();
		} else {
			res = "No file";
		}
		//System.out.println("&&&&&&&&&&&& running read2 method : "+res);
		Send.sendMethod(res);
		
	}

}
