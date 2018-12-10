package com.yjh.three.handler;

import com.yjh.three.handler.DecoratedHandler;
import com.yjh.three.handler.Handler;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class BlockingChannelHandler extends DecoratedHandler<SocketChannel>{
    public BlockingChannelHandler(Handler<SocketChannel> other) {
        super(other);
    }

    @Override
    public void handle(SocketChannel sc) throws IOException {
        while (sc.isConnected()) {
            super.handle(sc);
        }
    }
}
