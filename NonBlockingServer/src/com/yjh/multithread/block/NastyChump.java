package com.yjh.multithread.block;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NastyChump {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket[] socket = new Socket[3000];
		for(int i = 0 ; i < socket.length ; i++) {
			socket[i] = new Socket("localhost", 8080);
			
		}
	}
	
}
