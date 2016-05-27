package com.jassur.model;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jassur.database.PoolConnection;

/** le modele pour la Gestion id
 * @param 
 * @return
 * @author Sarah
 * @see 
 */	
public class Modele_Gestion_id {
	
	protected Connection connect = null;
	private int id_client;
	private String lastName;
	private String firstName;
	
	/** Methode qui recherche l'id du client
	 * Method that search the id client
	 * @param nom_jtf,prenom_jtf
	 * @return id_client
	 * @author Sarah
	 * @see 
	 */	
			public int search_id(String lastName_jtf ,String firstName_jtf)
			{
				
				PoolConnection poolConnexion = new PoolConnection();
				this.setConnect(poolConnexion.pop().getConnection()); 
				
				lastName=lastName_jtf;
				firstName=firstName_jtf;
				
				try {
					ResultSet result = this.connect.createStatement(
							ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY
					).executeQuery(
									"SELECT id_client"
									+ " From clients "
									+ "where last_name='"+lastName+"'and first_name='"+firstName+"';");
					if(result.first()) 
					{
						id_client=result.getInt(1);
						return id_client;
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return 0;
				
			}
			
			/** Methode qui retourne la connexion
			 * Return the connection
			 * @param 
			 * @return connect
			 * @author Sarah
			 * @see 
			 */			
			public Connection getConnect() {
				return connect;
			}
			/** Methode qui set la connexion
			 * set the connection
			 * @param connect
			 * @return 
			 * @author Sarah
			 * @see 
			 */	
			public void setConnect(Connection connect) {
				this.connect = connect;
			}
}
