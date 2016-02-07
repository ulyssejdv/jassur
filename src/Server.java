import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import database.ConfigurationDB;
import database.Connexion;
import database.PoolConnexion;
import message.Message;
import message.Routeur;

public class Server {
	
	private static PoolConnexion poolConnexion = null;

	public static void main(String[] args) {
		
		/* Loading DB configuration */
		System.out.println("Loading configuration file ...");
		ConfigurationDB conf = new ConfigurationDB();
		
		System.out.println("Creating connexion pool ...");
		poolConnexion = new PoolConnexion();
		
		/* Create the connection pool */
		for (int i = 0; i < PoolConnexion.MAX_CONNEXION; i++) {
			System.out.println("Connexion "+i+" OK");
			poolConnexion.push(new Connexion(conf));
		}
		
		ServerSocket serverSocket = null;
		String clientMsg = new String();
		
		try {
			serverSocket = new ServerSocket(6789);
			System.out.println("Server now listening on :"+serverSocket.getLocalPort()+" ...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/* Start the server */
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				BufferedReader inputClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				DataOutputStream outputClient = new DataOutputStream(socket.getOutputStream());
				
				/* Read input client message */
				Message message = new Message();
				message.read(inputClient.readLine());
				
				/* Start analyze of the message for routing 
				 * and give the phone (outpuClient) to the router 
				 * for callback
				 */
				Routeur routeur = new Routeur(outputClient, poolConnexion);
				routeur.analyse(message);	
				
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} // end infinite loop
	}
}
