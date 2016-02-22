import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import database.ConfigurationDB;
import database.Connexion;
import database.PoolConnexion;
import message.Message;
import message.Routeur;
import model.Pret;

public class Server {
	
	private static PoolConnexion poolConnexion = null;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		/* Create Connection Pool */
		poolConnexion = new PoolConnexion();
		
		JSONObject o = new JSONObject();
		
		// Test de création de message
		/*Message out = new Message();
		Message in = new Message();
		
		JSONObject o = new JSONObject();
		
		o.put("montant", 10000.0);
		o.put("taux", 1.90);
		o.put("mensualite", 24);
		
		
		in.read(out.post("pret", o));
		
		System.out.println(in.toString());
		System.exit(0);*/
		
		
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
