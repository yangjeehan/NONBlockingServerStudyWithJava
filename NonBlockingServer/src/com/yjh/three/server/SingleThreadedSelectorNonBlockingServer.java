package com.yjh.three.server;

import com.yjh.three.handler.Handler;
import com.yjh.three.handler.TransmogrifyChannelHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

public class SingleThreadedSelectorNonBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocketChannel ssc =  ServerSocketChannel.open();
		ssc.bind(new InetSocketAddress(7070));
		ssc.configureBlocking(false);
		Selector selector = Selector.open();
		ssc.register(selector, SelectionKey.OP_ACCEPT);

		Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();
        Handler<SelectionKey> acceptHandler = new AcceptHandler(pendingData);
		Handler<SelectionKey> readHandler = new ReadHandler(pendingData);
		Handler<SelectionKey> writeHandler = new WriteHandler(pendingData);

		while(true) {
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			for (Iterator<SelectionKey> it = keys.iterator(); it.hasNext(); ) {
				SelectionKey key =  it.next();
				it.remove();
				if(key.isValid()) {
					if (key.isAcceptable()) {
						acceptHandler.handle(key);
					} else if (key.isReadable()) {
						readHandler.handle(key);
					} else if (key.isWritable()) {
						writeHandler.handle(key);
					}
				}
			}
		}
	}
}
