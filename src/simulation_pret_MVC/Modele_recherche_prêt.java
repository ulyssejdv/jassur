package simulation_pret_MVC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jassur.database.PoolConnection;
/** le modele de la recherche pret
 * @param 
 * @return
 * @author Sarah
 * @see 
 */	
public class Modele_recherche_prêt {
	protected Connection connect = null;
	
	/**Constructeur du modele recherche pret
	 * @param 
	 * @return
	 * @author Sarah
	 * @see 
	 */		
	public Modele_recherche_prêt()
	{
		
				PoolConnection poolConnexion = new PoolConnection();
				this.setConnect(poolConnexion.pop().getConnection());
	}
	/**Methode qui calcule le nombre de type de pret different pour un client
	 * @param id_du_client
	 * @return nb_type_pret
	 * @author Sarah
	 * @see 
	 */	
	public int Calcul_nb_type_pret(int id_du_client){
			
			int nb_type_pret;
			
			try {
				ResultSet res = this.connect.createStatement(
						ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_READ_ONLY
				).executeQuery(
						"SELECT count(distinct category_id) "
						+ "FROM loans"
						+ " WHERE client_id='"+id_du_client+"';");			
				if(res.first())
				{				
					nb_type_pret=res.getInt(1);
					return nb_type_pret;
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return 0;
			
			
		}
	
	/**Methode return le tableau des types de pret du client
	 * @param id_client,tab_type_pret
	 * @return tab_type_pret
	 * @author Sarah
	 * @see 
	 */	
	public String [] typepret(int id_client,String []tab_type_pret ){
		 
		int cpt=0;
		
		try {
			ResultSet res = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
							"SELECT DISTINCT label_category "
							+ "FROM categories,loans"
							+ " WHERE category_id = id_category and client_id = '"+id_client+"';");
		while(res.next()){
			
			tab_type_pret[cpt]= res.getString(1);
			cpt++;
			}
		return tab_type_pret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
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
