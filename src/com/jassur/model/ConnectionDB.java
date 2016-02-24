package com.jassur.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class ConnectionDB {
	

	ArrayList<TypeClient> typeClients;
	ArrayList<TypePret> typePrets;
	ArrayList<StatutPret> statutPrets;
	ArrayList<TypeUtilisateur> typeUtilisateurs;
	ArrayList<Utilisateur> utilisateurs;
	ArrayList<Client> clients;
	ArrayList<PersonneMorale> personne_morales;
	ArrayList<PersonnePhysique> personne_physiques;
	ArrayList<Pret> prets;
	
	public ArrayList<Pret> getPrets() {
		return prets;
	}

	public void setPrets(ArrayList<Pret> prets) {
		this.prets = prets;
	}

	Connection conn;
	
	public ConnectionDB(Connection conn) {

		this.typeClients = TypeClient.selectTypeClient(conn);
		this.typePrets = TypePret.selectTypePret(conn);
		this.statutPrets = StatutPret.selectStatutPret(conn);
		this.typeUtilisateurs = TypeUtilisateur.selectTypeUtilisateur(conn);
		this.utilisateurs = Utilisateur.selectUtilisateur(conn,this);
		this.clients = Client.selectClient(conn,this);
		this.personne_morales = PersonneMorale.selectPersonneMorale(conn,this);
		this.personne_physiques = PersonnePhysique.selectPersonnePhysique(conn,this);
		this.prets = Pret.selectPret(conn,this);
		this.conn=conn;
	}
	
	private void afficheTableTypeClients(){
		for(int i=0;i<typeClients.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+typeClients.get(i).toString());
	}
	
	private void afficheTableTypePrets(){
		for(int i=0;i<typePrets.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+typePrets.get(i).toString());
	}
	
	private void afficheTableStatutPrets(){
		for(int i=0;i<statutPrets.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+statutPrets.get(i).toString());
	}
	
	private void afficheTableTypeUtilisateurs(){
		for(int i=0;i<typeUtilisateurs.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+typeUtilisateurs.get(i).toString());
	}
	
	private void afficheTableUtilisateurs(){
		for(int i=0;i<utilisateurs.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+utilisateurs.get(i).toString());
	}
	
	private void afficheTableClient(){
		for(int i=0;i<clients.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+clients.get(i).toString());
	}
	
	private void afficheTablePersonneMorales(){
		for(int i=0;i<personne_morales.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+personne_morales.get(i).toString());
	}
	
	private void afficheTablePersonnePhysiques(){
		for(int i=0;i<personne_physiques.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+personne_physiques.get(i).toString());
	}
	
	private void afficheTablePrets(){
		for(int i=0;i<prets.size();i++) System.out.println("Ligne "+Integer.toString(i+1)+": "+prets.get(i).toString());
	}
	
	public TypeUtilisateur getTypeUtilisateur(int idTypeUtilisateur){
		for(int i=0;i<typeUtilisateurs.size();i++)
			if(typeUtilisateurs.get(i).getIdTypeUtilisateur()==idTypeUtilisateur) return typeUtilisateurs.get(i);
		return null;
	}
	
	public TypeClient getTypeClient(int idTypeClient){
		for(int i=0;i<typeUtilisateurs.size();i++)
			if(typeClients.get(i).getIdTypeClient()==idTypeClient) return typeClients.get(i);
		return null;
	}
	
	public TypePret getTypePret(int idTypePret){
		for(int i=0;i<typePrets.size();i++)
			if(typePrets.get(i).getIdTypePret()==idTypePret) return typePrets.get(i);
		return null;
	}
	
	public StatutPret getStatutPret(int idStatutPret){
		for(int i=0;i<statutPrets.size();i++)
			if(statutPrets.get(i).getid_statut_pret()==idStatutPret) return statutPrets.get(i);
		return null;
	}
	
	public Client getClient(int idClient){
		for(int i=0;i<clients.size();i++)
			if(clients.get(i).getIdClient()==idClient) return clients.get(i);
		return null;
	}
	public Utilisateur getUtilisateur(int idUtilisateur){
		for(int i=0;i<clients.size();i++)
			if(utilisateurs.get(i).getIdUtilisateur()==idUtilisateur) return utilisateurs.get(i);
		return null;
	}
	
	private void supprimerPersonnePhysique(){
		System.out.println("Quelle ligne souhaitez vous supprimer ?\n");
		afficheTablePersonnePhysiques();
		System.out.println("\n0. Retourner au menu");
		Scanner sc= new Scanner(System.in);
		int fin=0;
		while(fin==0){
			try{
				int choix=sc.nextInt();
				if(choix<0) System.out.println("Choix non disponible");
				if(choix==0)fin=1;
				else{
					this.personne_physiques.get(choix-1).deletePersonnePhysique(conn);
					this.personne_physiques.remove(choix-1);
					fin=1;
				}
				
				}catch(Exception e){
				System.out.println("Veuillez saisir un choix possible 2");
				sc.nextLine();
				}
		}
	}
	
	private void modifierPersonnePhysique(){
		System.out.println("Quelle ligne souhaitez vous modifier ?\n");
		afficheTablePersonnePhysiques();
		System.out.println("\n0. Retourner au menu");
		Scanner sc= new Scanner(System.in);
		int fin=0;
		while(fin==0){
			try{
				int choix=sc.nextInt();
				if(choix<0) System.out.println("Choix non disponible");
				if(choix==0)fin=1;
				else{
					this.personne_physiques.get(choix-1).updatePersonnePhysique(conn);
					this.reloadPersonnePhysique();
					fin=1;
				}
				
				}catch(Exception e){
				System.out.println("Veuillez saisir un choix possible 2");
				sc.nextLine();
				}
		}
	}
	
	private void insererPersonnePhysique() {
		PersonnePhysique.insertPersonnePhysique(conn, this);
		reloadClient();
		reloadPersonnePhysique();
		
	}
	
	private void reloadPersonnePhysique(){
		this.personne_physiques = PersonnePhysique.selectPersonnePhysique(conn,this);
	}
	
	private void reloadClient(){
		this.clients = Client.selectClient(conn,this);

	}
	
	private void afficherContenuTable(){
		System.out.println("\nListe des actions possibles :");
		System.out.println("1. type_client");
		System.out.println("2. type_pret");
		System.out.println("3. statut_pret");
		System.out.println("4. type_utilisateur");
		System.out.println("5. utilisateur");
		System.out.println("6. client");
		System.out.println("7. personne_morale");
		System.out.println("8. personne_physiques");
		System.out.println("9. prets");
		System.out.println("\nQuelle table souhaitez vous afficher ?");
		Scanner sc= new Scanner(System.in);
		int fin=0;
		while(fin==0){
			try{
				int choix=sc.nextInt();
				switch(choix){
					case 1:afficheTableTypeClients();break;
					case 2:afficheTableTypePrets();break;
					case 3:afficheTableStatutPrets();break;
					case 4:afficheTableTypeUtilisateurs();break;
					case 5:afficheTableUtilisateurs();break;
					case 6:afficheTableClient();break;
					case 7:afficheTablePersonneMorales();break;
					case 8:afficheTablePersonnePhysiques();break;
					case 0:afficheTablePrets();break;
					default: System.out.println("Action inexistante");break;
					}
				fin=1;
				}catch(Exception e){
				System.out.println("Veuillez saisir un choix possible");
				sc.nextLine();
				}
		}
	}
	
	public static Connection connect(String url, String utilisateur, String motDePasse){
		Connection connexion = null;
		
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			System.out.println("erreur "+e.getMessage());
		}
	
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connexion;
	}
	
	/*public static void main(String[] args) {
		Connection conn = ConnectionDB.connect("jdbc:mysql://localhost:8889/mydb","root","root");
		ConnectionDB database= new ConnectionDB(conn);
		
		int fin=0;
		System.out.println("-----------------------------------------------------");
		System.out.println("-                                                   -");
		System.out.println("-                  SERVEUR JASSUR                   -");
		System.out.println("-                                                   -");
		System.out.println("-----------------------------------------------------");
		while(fin==0){
			System.out.println("\nListe des actions possibles :");
			System.out.println("1. Afficher le contenu d'une table");
			System.out.println("2. Modifier le contenu de la table personne physique");
			System.out.println("3. Supprimer du contenu de la table personne physique");
			System.out.println("4. Ajouter du contenu à la table personne physique");
			System.out.println("\n0. Quitter le programme");
			System.out.println("\n Que souhaitez vous faire ?");

			Scanner sc= new Scanner(System.in);
			int boucle=0;
			while(boucle==0){
				try{
					int choix=sc.nextInt();
					switch(choix){
						case 0:fin=1;break;
						case 1:database.afficherContenuTable();break;
						case 2:database.modifierPersonnePhysique();break;
						case 3:database.supprimerPersonnePhysique();break;
						case 4:database.insererPersonnePhysique();break;
						default: System.out.println("Action inexistante");break;
						}
					boucle=1;
					}catch(Exception e){
					System.out.println("Veuillez saisir un choix possible");
					sc.nextLine();
					}
			}
		}
	}*/

}



