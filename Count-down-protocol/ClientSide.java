import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientSide {
	
	private static Socket socket;
	 
	    public static void main(String args[])
	    {
	    	int port = 2525;
	    	System.out.println("Please enter the number of count down? ");
	    	Scanner sc = new Scanner(System.in);
			String number =sc.nextLine().toString();
			
	try
    {
        InetAddress address = InetAddress.getLocalHost();
        socket = new Socket(address, port);

        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String sendingMessage = number + "\r\n";
        bw.write(sendingMessage);
        bw.flush();
        System.out.println("Count down from "+sendingMessage);

        
	    InputStream is = socket.getInputStream();
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br = new BufferedReader(isr);
	        
	        for(int i=0 ; i<=Integer.parseInt(number) ;i++)
	        {
	        String message = br.readLine();
	        System.out.println("recieved: " +message);
	        }
    }
    catch (Exception exception)
    {  	// TODO Auto-generated catch block
    	System.err.println("Connection in refused....");
    }
  
            try {
				socket.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
       
}
}
