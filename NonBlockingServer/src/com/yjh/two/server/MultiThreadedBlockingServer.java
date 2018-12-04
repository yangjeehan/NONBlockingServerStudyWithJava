package com.yjh.two.server;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.yjh.two.handler.*;

public class MultiThreadedBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler =
			new ThreadHandler<>(
				new PrintingHandler<>(
					new TransmogrifyHandler()
		    	)
	    	);

		while(true) {
			Socket s = ss.accept();
			handler.handle(s);
		}
	
	}
	
}
