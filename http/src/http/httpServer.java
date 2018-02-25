package http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class httpServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss=null;
		try {
			ss = new ServerSocket(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		while (true) {
            
			try {
				 
				  Socket s = ss.accept();
	              TaskThread t = new TaskThread(s);
	              t.start();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           
        }

	}

}
