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
import model.ConnectionDB;
import model.ListPret;
import model.Pret;

public class Server {
	
	private PoolConnexion pc = null;

	public static void main(String[] args) {
		
		/* Loading DB configuration */
		System.out.println("Loading configuration file ...");
		ConfigurationDB conf = new ConfigurationDB();
		
		System.out.println("Creating connexion pool ...");
		PoolConnexion pc = new PoolConnexion();
		
		/* Create the connection pool */
		for (int i = 0; i < PoolConnexion.MAX_CONNEXION; i++) {
			System.out.println("Connexion "+i+" OK");
			pc.push(new Connexion(conf));
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
				
				clientMsg = inputClient.readLine();
				
				Message message = new Message(clientMsg);
				
				ConnectionDB cdb = new ConnectionDB(pc.pop().getConnection());
				ListPret lp = new ListPret(cdb.getPrets());
				System.out.println(lp.toXml());
				outputClient.writeBytes(lp.toXml()+'\n');
				
				System.out.println(message.toString());
			
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
