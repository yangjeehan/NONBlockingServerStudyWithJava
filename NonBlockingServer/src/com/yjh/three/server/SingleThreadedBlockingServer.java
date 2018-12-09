package com.yjh.three.server;

import com.yjh.three.handler.Handler;
import com.yjh.three.handler.PrintingHandler;
import com.yjh.three.handler.TransmogrifyHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(7070);
		Handler<Socket> handler =
			new PrintingHandler<>(
					new TransmogrifyHandler()
		);
		while(true) {
			Socket s = ss.accept();
			handler.handle(s);
		}
	
	}


}
