package com.yjh.one;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		while(true) {
			Socket s = ss.accept();
			handle(s);
		}
	
	}

	private static void handle(Socket s) throws IOException {
		new Thread(() -> {
			try (
					Socket temp = s;
					InputStream in = s.getInputStream();
					OutputStream out = s.getOutputStream();
			)   {
				int data;
				while((data = in.read()) != -1 ) {
					out.write(transmogrify(data));
				}
			} catch (IOException ex) {
//				RuntimeException을 확장시킨 모양, 그래서 간단하게 죽는 원인이 될 수 있다.
				throw new UncheckedIOException(ex);
			} finally {
				System.out.println("Disconnected from " + s );
			}
		}).start();
	}

	public static int transmogrify(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}


}
