package model;
// Generated 6 janv. 2016 11:04:49 by Hibernate Tools 4.3.1.Final

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * PersonnePhysique generated by hbm2java
 */
public class PersonnePhysique implements java.io.Serializable {

	private Client client;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private String adresse;

	public PersonnePhysique() {
	}

	public PersonnePhysique(Client client) {
		this.client = client;
	}

	public PersonnePhysique(Client client, String nom, String prenom, Date dateDeNaissance, String adresse) {
		this.client = client;
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.adresse = adresse;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateDeNaissance() {
		return this.dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return client.getIdClient() + " " + nom + " " + prenom + " "
				+ dateDeNaissance + " " + adresse;
	}

	public static ArrayList<PersonnePhysique> selectPersonnePhysique(Connection conn,ConnectionDB database){
		Statement statement = null;
		ResultSet resultat= null;
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
		ArrayList<PersonnePhysique> personne_physiques=new ArrayList<PersonnePhysique>();
		try {
			statement = conn.createStatement();

	        /* Exécution d'une requète de lecture */
	        resultat = statement.executeQuery( "select * from personne_physique" );
	 
	        /* Récupération des données du résultat de la requète de lecture */
	        while ( resultat.next() ) {
	        	Date date;
				try {
					date = formatter.parse(resultat.getString(4));
		        	personne_physiques.add(new PersonnePhysique(database.getClient(Integer.parseInt(resultat.getString(1))),resultat.getString(2),resultat.getString(3),date,resultat.getString(5)));

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return personne_physiques;
	}
	
	public void deletePersonnePhysique(Connection conn){
		String sql = "DELETE FROM client WHERE id_client="+Integer.toString(this.getClient().getIdClient());
		Statement statement;
		try {
			statement = conn.createStatement();
			int rowsDeleted = statement.executeUpdate(sql);
			if (rowsDeleted > 0) {
			    System.out.println("Personne physique Supprimée");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updatePersonnePhysique(Connection conn) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
		String sql = "UPDATE personne_physique SET nom=?,prenom=?,date_de_naissance=?,adresse=? WHERE id_client="+Integer.toString(this.getClient().getIdClient());
		Scanner sc = new Scanner(System.in);
		String reponse;
		String nnom;
		String nprenom;
		String ndate;
		String nadresse;
		System.out.println("Souhaitez vous modifier le nom ? (Y/N)");
		reponse=sc.nextLine();
		System.out.println(reponse);
		if(reponse.toUpperCase().contains("Y")){
			System.out.println("Quel nom souhaitez vous mettre ?");
			nnom=sc.nextLine();
		}else nnom=nom;
		System.out.println("Souhaitez vous modifier le prénom ? (Y/N)");
		reponse=sc.next();
		if(reponse.toUpperCase().contains("Y")){
			System.out.println("Quel prénom souhaitez vous mettre ?");
			nprenom=sc.nextLine();
		}else nprenom=prenom;
		System.out.println("Souhaitez vous modifier la date de naissance ? (Y/N)");
		reponse=sc.nextLine();
		if(reponse.toUpperCase().contains("Y")){
			System.out.println("Quel date de naissance souhaitez vous mettre ? (format \"aaaa-mm-jj\"");
			ndate=sc.nextLine();
		}else ndate=formatter.format(dateDeNaissance);
		System.out.println("Souhaitez vous modifier l'adresse ? (Y/N)");
		reponse=sc.nextLine();
		if(reponse.toUpperCase().contains("Y")){
System.out.println("Quel adresse souhaitez vous mettre ?");
			nadresse=sc.nextLine();
		}else nadresse=adresse;
		
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1,nnom);
			statement.setString(2,nprenom);
			statement.setString(3,ndate);
			statement.setString(4,nadresse);
			 
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
			    System.out.println("Personne physique mise a jour !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void insertPersonnePhysique(Connection conn,ConnectionDB database) {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-mm-dd");
		Scanner sc = new Scanner(System.in);
		String reponse;
		String nnom;
		String nprenom;
		String ndate;
		String nadresse;
		
		System.out.println("Quel nom souhaitez vous mettre ?");
		nnom=sc.nextLine();
		System.out.println("Quel prénom souhaitez vous mettre ?");
		nprenom=sc.nextLine();
		System.out.println("Quel date de naissance souhaitez vous mettre ? (format \"aaaa-mm-jj\"");
		ndate=sc.nextLine();
		System.out.println("Quel adresse souhaitez vous mettre ?");
		nadresse=sc.nextLine();
				
		try {
			
			String sql="INSERT into client(type_client_id_type_client) values (2)";
			Statement statement = conn.prepareStatement(sql);
			String sql2 = "SELECT max(id_client) FROM client";
			Statement statement2 = conn.createStatement();
			ResultSet result = statement2.executeQuery(sql2);
			result.next();
			int rowsUpdated = statement.executeUpdate(sql);
			sql = "INSERT into personne_physique (id_client,nom,prenom,date_de_naissance,adresse) values("
					+result.getString(1)+",\'"+nnom+"\',\'"+nprenom+"\',\'"+ndate+"\',\'"+nadresse+"\')";
			statement = conn.prepareStatement(sql);
			rowsUpdated = statement.executeUpdate(sql);
			if (rowsUpdated > 0) {
			    System.out.println("Personne physique ajoutée !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}