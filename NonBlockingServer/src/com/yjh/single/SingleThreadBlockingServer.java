package com.yjh.single;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadBlockingServer {
	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(8080);
		while(true) {
			Socket s = ss.accept();
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			int data;
			while((data = in.read()) != -1 ) {
				out.write(transmogrify(data));
			}
//			in.transferTo(out);
			out.close();
			in.close();
			s.close();
		}
		
	}

	private static int transmogrify(int data) {
		return Character.isLetter(data) ? data ^ ' ' : data;
	}
}
