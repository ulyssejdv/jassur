package simulation_pret;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;

import com.jassur.database.PoolConnection;
//METTRE EN ANGLAIS code & COMMENTAIRES
public class Appli_Tab_Pret extends JFrame implements ActionListener {
	
	
	protected Connection connect = null;
	private int id;
	private String type_pret;
	private int id_type_pret;
	private int nb_pret;
	private Object[][] donne_jtable ;//tableau qui contient les donnees extraites
	private JTable jtableau_pret ; 
	private JButton retour =new JButton("Retour");	
	
	//titres des colonnes du tableau
	private String  titre_jtable[] = {"Type Prêt (choix)", "Création de simulation", "Durée de l'emprunt", "Mensualité"
			, "Montant de l'emprunt"};
	
public void Appli_Tab_Pret(String type_de_pret,int id_client){
		//recuperation des arguments dans variables
		type_pret=type_de_pret;
		id=id_client;
		
		//lignes de connexion
		PoolConnection poolConnexion = new PoolConnection();
		this.setConnect(poolConnexion.pop().getConnection());
		
		//on ecoute le bouton retour
		retour.addActionListener(this);
		
		this.setSize(800,500);		
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("Comparateur de simulations");
	    
	    //appel des methodes de la classe
	    this.recherche_id_pret();//recherche l'id du type de pret choisi dans bd
	    this.nombre_pret_client();//calcul le nombre de simulations de pret du type choisi
		this.recuperation_donne_pret();//recuperation des informations des prets
		
		
	    
	    this.getContentPane().add(retour,BorderLayout.SOUTH);
	    //en-tête doit au nord
	    this.getContentPane().add(jtableau_pret.getTableHeader(), BorderLayout.NORTH);
	    //corps au centre 
	    this.getContentPane().add(jtableau_pret, BorderLayout.CENTER);
	}

//Procédure qui permet de récuperer l'id du type de pret selectionné 
public void recherche_id_pret()
{
	
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
					
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}
//methode qui recupere les donnees des pret pour le client et le type donnee
public void recuperation_donne_pret()
{
	//variables tempo pour gerer les informations retournees par la requete
	int col=0,ligne=0;
	String date;
	String durer;
	String mensualite;
	String totalmensualite;
	
	try {
		ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT created_at,total_duration,amount,total_amount"
				+ " FROM loans WHERE client_id='"+id+"' AND category_id='"+id_type_pret+"';");		
		while(result.next())//next car plusieurs resultats
		{	//insert les informations dans le tableau de donnees
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
		//jtable récupere en-tete et donnees 
		jtableau_pret = new JTable(donne_jtable, titre_jtable);	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
}
//Procédure pour compter le nombre de pret du type choisi pour cree la taille du tableau de donnee
public void nombre_pret_client()
{
	
	
	try {
		ResultSet result = this.connect.createStatement(
				ResultSet.TYPE_FORWARD_ONLY,
				ResultSet.CONCUR_READ_ONLY
		).executeQuery("SELECT COUNT(*) "
				+ "FROM loans"
				+ " WHERE client_id='"+id+"' AND category_id='"+id_type_pret+"';");		
		if(result.first())//si on a une reponse on recupere le nombre de pret d'un type donne
		{ 
			nb_pret=result.getInt(1);
			//avec le nombre de pret on cree le tableau de donnees avec pour taille le nombre de pret
			//evite de faire des lignes de tableau vide
			donne_jtable= new Object[nb_pret][5];	
		}		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
}

@Override
public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	if(arg0.getSource() == retour )
	{		//Nettoie la fenetre et la ferme quand on appuie sur le bouton retour
		this.getContentPane().removeAll();
		this.dispose();	
		
	}	
}

//Gere la connexion
public Connection getConnect() {
	return connect;
}

public void setConnect(Connection connect) {
	this.connect = connect;
}

}
