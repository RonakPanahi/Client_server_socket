/**
 * AUTHOR: RONAK PANAHI
 * Date: November 2015
 * This program opens a server socket on the http port, port 80, 
 * and listens for requests from web browsers. When a request comes 
 * for a file, it always sends a fixed single file. 
 */ 

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide extends Thread {

	  private byte[] content;
	  private byte[] header;
	  private int port = 80;
	  
	  public ServerSide(byte[] data, String encoding, String contentType) throws UnsupportedEncodingException {
			
		  this.content = data;
		  String header = "HTTP/1.0 200 OK\r\n"  + "Content-length: " 
		    + "Content-type: " + contentType + "\r\n\r\n";
		  this.header = header.getBytes(encoding);
	}
	  
	  public void run()
	  {
		  try {
			  	ServerSocket serverSocket = new ServerSocket(port); 
			  	Socket client = serverSocket.accept();
			  	
			    if(client != null)                
			    	{
			    	System.out.println("Client Connected to the port "+port);
			    	System.out.println("Please view the response");
			    	}
			    	
			 
			    OutputStream out = new BufferedOutputStream(client.getOutputStream());
			    InputStream in   = new BufferedInputStream(client.getInputStream());
			    
			    StringBuffer request = new StringBuffer(80);
			  	while (true) {
			  		int c = in.read();
			  		if (c == '\r' || c == '\n' || c == -1) break;
			  		request.append((char) c);         
			  		}
			  	if (request.toString().indexOf("HTTP/") != -1) {
			  		out.write(this.header);
			  	}        
			  	out.write(this.content);
			  	
			    out.flush(); 
				client.close();
				serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Couldnt establish a connection");
			e.printStackTrace();
		}
		  	
		  
	  }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		final String encoding = "ASCII";
		String contentType = "text/plain";
		
		  
	       try {
	            
	            String file = "C://data//socket.html";
	 	        int b ;
	 	        if (file.endsWith(".html") || file.endsWith(".htm")) 
			  	{
			  		contentType = "text/html";}

	 	        InputStream in = new FileInputStream(file);
	 	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 	        
	 	        while((b = in.read()) != -1) {
	 	            baos.write(b);
	 	            }
	 	        byte[] data = baos.toByteArray();
	 	      	
			  	
			  	Thread t = new ServerSide(data,encoding,contentType);
			  	t.start();
			  	
				in.close();
	           } 
	       catch (IOException e) 
	           {
	             System.err.println("could not listen on port 80.");
	             System.exit(1);
	           }
	}

	
	    
}
