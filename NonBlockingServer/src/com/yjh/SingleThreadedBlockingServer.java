package com.yjh;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedBlockingServer {
	
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		while(true) {
			Socket s = ss.accept();
			handle(s);
		}
	
	}

	private static void handle(Socket s) throws IOException {
		System.out.println("Connected to " + s);
		try (
			Socket temp = s;
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
		)   {
			int data;
			while((data = in.read()) != -1 ) {
				out.write(transmogrify(data));
			}
		} finally {
			System.out.println("Disconnected from " + s );
		}
	}
	
	private static int transmogrify(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
