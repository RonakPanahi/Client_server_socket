import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

	private static Socket socket;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket serverSocket = null;
		String message;
        int port = 2525;
		
		try
        {
			serverSocket = new ServerSocket(port);
 
            while(true)
            {
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
 
                try
                {
                    int countdown = Integer.parseInt(number);
                    
                    for(int i = countdown ; i > 0  ; i--)
                    {
                        message = String.valueOf(i) + "\r\n";
                    	OutputStream os = socket.getOutputStream();
	                    OutputStreamWriter osw = new OutputStreamWriter(os);
	                    BufferedWriter bw = new BufferedWriter(osw);
	                    bw.write( message);
	                    bw.flush();
	                    
                    }
                    
                }
                catch(NumberFormatException e)
                {
                	// TODO Auto-generated method stub
                   System.err.println("Please send a number not string or character\n");
                }
            }
        }
        catch (Exception e)
        {
        	// TODO Auto-generated method stub
            e.printStackTrace();
        }
		try
        { socket.close();
            serverSocket.close();
        }catch(Exception e){
        	// TODO Auto-generated method stub
        }
        
	}

}
