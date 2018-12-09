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
		    	), Executors.newCachedThreadPool(),
					(t,e) -> System.out.println("uncaught: " + t + " error " + e)
	    	);

		while(true) {
			Socket s = ss.accept();
			handler.handle(s);
		}
	
	}
	
}
