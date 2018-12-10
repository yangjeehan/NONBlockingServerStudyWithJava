package com.yjh.three.server;

import com.yjh.three.handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

public class BlockingNIOServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc =  ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(7070));
        Handler<SocketChannel> handler =
			new ExecutorServiceHandler<>(
				new PrintingHandler<>(
					new BlockingChannelHandler(
						new TransmogrifyChannelHandler()
					)
		    	),
				Executors.newFixedThreadPool(10));

		while(true) {
			SocketChannel s = ssc.accept();
			handler.handle(s);
		}
	
	}
	
}
