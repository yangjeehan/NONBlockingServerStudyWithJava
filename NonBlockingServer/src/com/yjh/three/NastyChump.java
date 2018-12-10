package com.yjh.three;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NastyChump {

	public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
		Socket[] socket = new Socket[3000];
		for(int i = 0 ; i < socket.length ; i++) {
			try {
				socket[i] = new Socket("localhost", 7070);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		Thread.sleep(100_000);
	}
	
}
