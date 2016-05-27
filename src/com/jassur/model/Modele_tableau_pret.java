package com.jassur.model;
 
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.jassur.database.PoolConnection;



/** le modele du tableau des prets
 * the model of loans table
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
	public int recherche_id_loan(String type_pret )
	{
		int id_type_loan;
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT id_category "
					+ "FROM categories"
					+ " WHERE label_category ='"+type_pret+"';");		
			if(result.first())
			{
				id_type_loan=result.getInt(1);	
				return id_type_loan;
						
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	/**Methode qui retourne le nombre de prets du client pour un type donne
	 * Return the number of loans for a client with a specific type
	 * @param id_type_pret,id_client
	 * @return nb_pret
	 * @author Sarah
	 * @see 
	 */	
	public int number_loan_client(int id_type_loan,int id_client)
	{
			
		int nb_loan;
		String date_systeme =this.get_date();
		try {
			  ResultSet result = this.connect.createStatement(
			  ResultSet.TYPE_FORWARD_ONLY,
			  ResultSet.CONCUR_READ_ONLY
				).executeQuery("SELECT COUNT(id_loan) "
						+ "FROM loans l,states "
						+ "WHERE client_id='"+id_client+"' AND category_id='"+id_type_loan+"'"
						+ "AND (l.created_at >='"+date_systeme+"'OR l.updated_at >='"+date_systeme+"')"
						+"AND id_loan=loan_id AND label_state='Simulation';");			
				if(result.first())
				{ 
					nb_loan=result.getInt(1);
					return nb_loan;
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return 0;
	}
	
	/**Methode return la date en format annee-mois-jour
	 * Return the date in format year-month-day
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
	
	/**Methode qui recupere les donnees des prets du client pour le type selectionne et les retourne
	 * Retrieve loans data for a client with a specific type and return it
	 * @param data_jtable,id_type_loan,id_client,type_loan
	 * @return data_jtable
	 * @author Sarah
	 * @see 
	 */	
	public JSONArray
	recovery_data_loan(int id_type_loan,int id_client,String type_loan)
	{
		JSONArray array_table_type_loans = new JSONArray();
		JSONObject o = new JSONObject();
		
		int col=0;
		String date_systeme =this.get_date();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT l.created_at,total_duration,amount,total_amount "
					+ "FROM loans l,states "
					+ "WHERE l.client_id='"+id_client+"'AND category_id='"+id_type_loan+"'"
					+ "AND (l.created_at >='"+date_systeme+"'OR l.updated_at >='"+date_systeme+"')"
					+"AND id_loan=loan_id AND client_id=user_id AND label_state='Simulation';");		
			while(result.next())
			{	
				
				String date="a"+result.getString(1)+"a";
				col++;
				o.put(col,date);
				String duration="a"+result.getString(2)+"a";
				col++;
				o.put(col,duration);
				String monthly="a"+result.getString(3)+"a";
				col++;
				o.put(col,monthly);
				String totalmonthly="a"+result.getString(4)+"a";
				col++;
				o.put(col,totalmonthly);			
				
			}	
			array_table_type_loans.add(o);
			
			return array_table_type_loans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
		 * Set the connection
		 * @param connect
		 * @return 
		 * @author Sarah
		 * @see 
		 */	
		public void setConnect(Connection connect) {
			this.connect = connect;
		}
}
