package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OldThing {
	/**
	 * DEPRECATED
	 * 
	 * @param pret It's the pret that we want to add in our table
	 * @param conn conn is our connection
	 * @return
	 */
	public static int insertPret(Pret pret,java.sql.Connection conn)
	{
		String sql = "INSERT INTO PRET (id_client, id_utilisateur, taux_variable, date_creation,id_type_pret,id_statut_pret) "
				+ "VALUES (?, ?, ?, ?,?,?)";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			//catch the id_client and put it into the sql statement
			statement.setString(1,Integer.toString(pret.getClient().getIdClient()));
			statement.setString(2,Integer.toString(pret.getUtilisateur().getIdUtilisateur()));
			if(pret.getTauxVariable()) 	statement.setString(3,"0");
			else statement.setString(3,"1");
			statement.setString(4,"1992/07/23");
			statement.setString(5,Integer.toString(pret.getTypePret().getIdTypePret()));
			statement.setString(6,Integer.toString(pret.getStatutPret().getid_statut_pret()));
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				// confirm message
			    System.out.println("Pret inséré");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * DEPRECATED
	 * 
	 * @param conn
	 * @param id
	 * @return
	 */
	public static int deletePret(Connection conn, int id)
	{
		String sql = "DELETE FROM pret WHERE id_pret="+id;
		Statement statement;
		int rowsDeleted = 0;
		try {
			statement = conn.createStatement();
			rowsDeleted = statement.executeUpdate(sql);
			if (rowsDeleted > 0) {
			    System.out.println("Pret Supprimé");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowsDeleted;	
	}
	
	
	public static ArrayList<Pret> selectPret(Connection conn,ConnectionDB database){
		Statement statement = null;
		ResultSet resultat= null;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
		ArrayList<Pret> prets=new ArrayList<Pret>();
		try {
			statement = conn.createStatement();

	        /* Exécution d'une requète de lecture */
	        resultat = statement.executeQuery( "select * from pret" );
	 
	        /* Récupération des données du résultat de la requète de lecture */
	        while ( resultat.next() ) {
	        	Date date_crea;
	        	Date date_fin;
				try {
					date_crea = formatter.parse(resultat.getString(7));
					date_fin = formatter.parse(resultat.getString(11));
					Boolean bool;
					if(resultat.getString(6).contains("0"))bool=false;
					else bool=true;
					Pret p = new Pret(Integer.parseInt(resultat.getString(1)),database.getClient(Integer.parseInt(resultat.getString(2))),database.getUtilisateur(Integer.parseInt(resultat.getString(3))),database.getTypePret(Integer.parseInt(resultat.getString(4))),database.getStatutPret(Integer.parseInt(resultat.getString(5))),bool,date_crea,Integer.parseInt(resultat.getString(8)),Integer.parseInt(resultat.getString(9)),Double.parseDouble(resultat.getString(10)),date_fin,Double.parseDouble(resultat.getString(12)));
		        	p.setTaux(Double.parseDouble(resultat.getString(13)));
		        	p.setTotal(Double.parseDouble(resultat.getString(14)));
					prets.add(p);
		        	System.out.println("Ajout d'un pret");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return prets;
	}
	
	
	public static Pret get(Connection conn,ConnectionDB database, int id) {
		
		Statement statement = null;
		ResultSet resultat= null;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
		
		Pret pret = null;
		
		try {
			statement = conn.createStatement();

	        /* Exécution d'une requète de lecture */
	        resultat = statement.executeQuery( "select * from pret WHERE id_pret = "+id);
	 
	        /* Récupération des données du résultat de la requète de lecture */
	        while ( resultat.next() ) {
	        	Date date_crea;
	        	Date date_fin;
				try {
					date_crea = formatter.parse(resultat.getString(7));
					date_fin = formatter.parse(resultat.getString(11));
					Boolean bool;
					if(resultat.getString(6).contains("0"))bool=false;
					else bool=true;
		        	pret = new Pret(Integer.parseInt(resultat.getString(1)),database.getClient(Integer.parseInt(resultat.getString(2))),database.getUtilisateur(Integer.parseInt(resultat.getString(3))),database.getTypePret(Integer.parseInt(resultat.getString(4))),database.getStatutPret(Integer.parseInt(resultat.getString(5))),bool,date_crea,Integer.parseInt(resultat.getString(8)),Integer.parseInt(resultat.getString(9)),Double.parseDouble(resultat.getString(10)),date_fin,Double.parseDouble(resultat.getString(12)));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return pret;
	}
}
