package simulation_pret_MVC;
 
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
	private String nom;
	private String prenom;
	
	/** Methode qui recherche l'id du client
	 * @param nom_jtf,prenom_jtf
	 * @return id_client
	 * @author Sarah
	 * @see 
	 */	
			public int recherche_id(String nom_jtf ,String prenom_jtf)
			{
				
				PoolConnection poolConnexion = new PoolConnection();
				this.setConnect(poolConnexion.pop().getConnection()); 
				
				nom=nom_jtf;
				prenom=prenom_jtf;
				
				try {
					ResultSet result = this.connect.createStatement(
							ResultSet.TYPE_FORWARD_ONLY,
							ResultSet.CONCUR_READ_ONLY
					).executeQuery(
									"SELECT id_client"
									+ " From clients "
									+ "where last_name='"+nom+"'and first_name='"+prenom+"';");
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
			 * @param 
			 * @return connect
			 * @author Sarah
			 * @see 
			 */			
			public Connection getConnect() {
				return connect;
			}
			/** Methode qui set la connexion
			 * @param connect
			 * @return 
			 * @author Sarah
			 * @see 
			 */	
			public void setConnect(Connection connect) {
				this.connect = connect;
			}
}
