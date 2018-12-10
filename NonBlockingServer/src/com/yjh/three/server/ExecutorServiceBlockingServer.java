package com.yjh.three.server;

import com.yjh.three.handler.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class ExecutorServiceBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(7070);
        Handler<Socket> handler =
			new ExecutorServiceHandler<>(
				new PrintingHandler<>(
					new TransmogrifyHandler()
		    	),
//					Executors.newCachedThreadPool(),
					Executors.newFixedThreadPool(10));

		while(true) {
			Socket s = ss.accept();
			handler.handle(s);
		}
	
	}
	
}
