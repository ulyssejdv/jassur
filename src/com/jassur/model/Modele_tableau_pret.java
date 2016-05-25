package com.jassur.model;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.jassur.database.PoolConnection;



/** le modele du tableau des prets
 * @param 
 * @return
 * @author Sarah
 * @see 
 */	
public class Modele_tableau_pret {

	protected Connection connect = null;

	/**Constructeur du modele du tableau des prets
	 * @param 
	 * @return
	 * @author Sarah
	 * @see 
	 */		
	public Modele_tableau_pret()
	{
			PoolConnection poolConnexion = new PoolConnection();
			this.setConnect(poolConnexion.pop().getConnection());
	}
	
	/**Methode qui recherche l'id du type de pret selectionne
	 * @param type_pret
	 * @return id_type_pret
	 * @author Sarah
	 * @see 
	 */	
	public int recherche_id_pret(String type_pret )
	{
		int id_type_pret;
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT id_category "
					+ "FROM categories"
					+ " WHERE label_category ='"+type_pret+"';");		
			if(result.first())
			{
				id_type_pret=result.getInt(1);	
				return id_type_pret;
						
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/**Methode qui return le nombre de prets du client pour un type donnee
	 * @param id_type_pret,id_client
	 * @return nb_pret
	 * @author Sarah
	 * @see 
	 */	
	public int nombre_pret_client(int id_type_pret,int id_client)
	{
			
		int nb_pret;
		String date_systeme =this.get_date();
		try {
			  ResultSet result = this.connect.createStatement(
			  ResultSet.TYPE_FORWARD_ONLY,
			  ResultSet.CONCUR_READ_ONLY
				).executeQuery("SELECT COUNT(*) "
						+ "FROM loans"
						+ " WHERE client_id='"+id_client+"' AND category_id='"+id_type_pret+"'"
						+ "AND (created_at >='"+date_systeme+"'OR updated_at >='"+date_systeme+"');");		
				if(result.first())
				{ 
					nb_pret=result.getInt(1);
					return nb_pret;
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;
	}
	
	/**Methode return la date en format annee-mois-jour
	 * @param 
	 * @return dat
	 * @author Sarah
	 * @see 
	 */	
	public String get_date()
	{
		DateFormat dateFormat = new SimpleDateFormat("-MM-dd");
		Date date = new Date();
		String dat = dateFormat.format(date);
		int year = Integer.valueOf(String.format("%1$tY",date));
		year=year-2;
		dat=year+dat;
		return dat;
	}
	
	/**Methode qui recupere les donnees des prets du client pour le type selectionne et le retourne
	 * @param donne_jtable,id_type_pret,id_client,type_pret
	 * @return donne_jtable
	 * @author Sarah
	 * @see 
	 */	
	public Object[][]  recuperation_donne_pret(Object[][] donne_jtable,int id_type_pret,int id_client , String type_pret)
	{
		
		int col=0,ligne=0;
		String date;
		String durer;
		String mensualite;
		String totalmensualite;
		String date_systeme =this.get_date();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT created_at,total_duration,amount,total_amount"
					+ " FROM loans "
					+ "WHERE client_id='"+id_client+"' AND category_id='"+id_type_pret+"' "
					+ "AND (created_at >='"+date_systeme+"'OR updated_at >='"+date_systeme+"');");		
			while(result.next())
			{	
				donne_jtable[ligne][col]=type_pret;
				date=result.getString(1);
				col++;
				donne_jtable[ligne][col]=date;
				durer=result.getString(2);
				col++;
				donne_jtable[ligne][col]=durer;
				mensualite=result.getString(3);
				col++;
				donne_jtable[ligne][col]=mensualite;
				totalmensualite=result.getString(4);
				col++;
				donne_jtable[ligne][col]=totalmensualite;
				col=0;	
				ligne++;
				
			}	
			return donne_jtable;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donne_jtable;
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
