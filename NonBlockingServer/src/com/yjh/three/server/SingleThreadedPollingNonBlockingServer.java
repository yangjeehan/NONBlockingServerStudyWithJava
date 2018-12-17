package com.yjh.three.server;

import com.yjh.three.handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;

public class SingleThreadedPollingNonBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc =  ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(7070));
		ssc.configureBlocking(false);

        Handler<SocketChannel> handler = new TransmogrifyChannelHandler();
		Collection<SocketChannel> sockets = new ArrayList<>();

		while(true) {
			SocketChannel sc = ssc.accept(); // almost always null
			if ( sc != null ) {
				sockets.add(sc);
				System.out.println("Connected to " + sc);
				sc.configureBlocking(false);
			}

			for (SocketChannel socket : sockets) {
				if( socket.isConnected() ) {
					handler.handle(socket);
				}
			}
			sockets.removeIf(socket -> !socket.isConnected());

		}
	}
}
