package echo;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final int port = 80;
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("Server is started, listening...");
					
			Socket s = serverSocket.accept();
			System.out.println("Client Request: " + s);
				
			Myclient client = new Myclient(s);
			client.getRequest();
			serverSocket.close();
			
		}	
		
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
