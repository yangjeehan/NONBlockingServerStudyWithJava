package com.yjh.two.server;
import com.yjh.two.handler.Handler;
import com.yjh.two.handler.PrintingHandler;
import com.yjh.two.handler.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		Handler<Socket> handler = new PrintingHandler<>(
			new TransmogrifyHandler()
		);
		while(true) {
			Socket s = ss.accept();
			handler.handle(s);
		}
	
	}


}
