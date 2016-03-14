import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.jassur.database.PoolConnection;
import com.jassur.message.Dispatcher;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;

public class ServerJassur {
	
	private static PoolConnection poolConnection = null;

	public static void main(String[] args) {
		
		/* Create Connection Pool */
		poolConnection = new PoolConnection();
	
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(6789);
			System.out.println("Server now listening on :"+serverSocket.getLocalPort()+" ...");
			
			/* Start the server */
			while (true) {
				
				BufferedReader inputClient = null;
				DataOutputStream outputClient = null;
				
				try {
					Socket socket = serverSocket.accept();
					inputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					outputClient = new DataOutputStream(socket.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
					
				try {
					/* Read input client message */
					String message = inputClient.readLine();	
					
					/* Start message analyze 
					 * and give the phone (outpuClient) to the router 
					 * for callback
					 */
					
					Dispatcher dispatcher = new Dispatcher(outputClient, poolConnection);
					dispatcher.analyze(message);	
					
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} // end infinite loop
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
