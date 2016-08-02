package echo;

import java.net.Socket;
import java.io.*;

public class Myclient {
	
	Socket soc;
	BufferedReader br = null;
	DataOutputStream dos = null;
	
	public Myclient(Socket s) throws IOException {
		// TODO Auto-generated constructor stub
		 this.soc = s;
		 br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		 dos = new DataOutputStream(s.getOutputStream());
		
	}

	String getRequest() throws Exception {
		
	    String str = null; 
	     
	    while ((str=br.readLine()) != null)
	    {
	       System.out.println("got: "+soc);
	    }
		return str;
	  }

}
