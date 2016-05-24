package com.jassur.server;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.jassur.database.PoolConnection;
import com.jassur.message.Dispatcher;

public class ServerJassur {
	
	private static PoolConnection poolConnection = null;

	public static void main(String[] args) {
		
		
		/* Create Connection Pool */
		poolConnection = new PoolConnection();
	
		try {
			/* Create logger */
			
			Logger logger= Logger.getLogger("myPackage.mySubPackage.myClasse");
			Handler fh = new FileHandler("journal.txt");
			fh.setFormatter(new SimpleFormatter());
			Logger.getLogger("journal").addHandler(fh);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
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
					String localAddress = socket.getLocalAddress().getHostAddress().toString();

					inputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					outputClient = new DataOutputStream(socket.getOutputStream());
			
					
				
					/* Read input client message */
					String message = inputClient.readLine();	
					
					// Get Info in journal.txt
					Logger.getLogger("journal").log(Level.INFO,localAddress+""+ message +'\n' );	

					
					/* Start message analyze 
					 * and give the phone (outpuClient) to the router 
					 * for callback
					 */
					
					Dispatcher dispatcher = new Dispatcher(outputClient, poolConnection, message);
					dispatcher.start();
					//dispatcher.analyze(message);	
					
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} // end infinite loop
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
