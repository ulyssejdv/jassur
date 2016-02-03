package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;


public class Client implements java.io.Serializable {

	private int idClient;
	private TypeClient typeClient;
	
	public Client() {
	}

	public Client(int idClient, TypeClient typeClient) {
		this.idClient = idClient;
		this.typeClient = typeClient;
	}
	
	public int getIdClient() {
		return this.idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public TypeClient getTypeClient() {
		return this.typeClient;
	}

	public void setTypeClient(TypeClient typeClient) {
		this.typeClient = typeClient;
	}
	
	public String toString(){
		return Integer.toString(this.idClient)+" "+Integer.toString(this.typeClient.getIdTypeClient());
	}

	public static ArrayList<Client> selectClient(Connection conn, ConnectionDB database) {
		Statement statement = null;
		ResultSet resultat= null;
		ArrayList<Client> clients=new ArrayList<Client>();
		try {
			statement = conn.createStatement();
	        resultat = statement.executeQuery( "select * from client" );
	 
	        while ( resultat.next() ) {
	        	clients.add(new Client(Integer.parseInt(resultat.getString(1)),database.getTypeClient(Integer.parseInt(resultat.getString(2)))));
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return clients;
}
	
}
