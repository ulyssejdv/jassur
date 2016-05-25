package com.jassur.controller;
import com.jassur.model.Modele_Gestion_id;

/**Controleur de la gestion des id  
 * controller of the id management
 * @param 
 * @return
 * @author Sarah
 * @see Modele_Gestion_id 
 */	
public class Controleur_Gestion_id  {
	
	Modele_Gestion_id mod;
	private int id_client_recuperer;

	
	/**constructeur de la classe qui met les composants en place dans un panel 
	 * constructor of the class that input components in panel
	 * @param m v
	 * @return
	 * @author Sarah
	 * @see Modele_Gestion_id 
	 */	
	public Controleur_Gestion_id(Modele_Gestion_id m )
	{
		mod=m;		
		
	}	
	/**Method qui recupere l'id du client 
	 * function that return the client id 
	 * @param nom_jtf prenom_jtf
	 * @return id_client_recuperer
	 * @author Sarah
	 * @see Modele_Gestion_id 
	 */	
	public int  get_id_client (String nom_jtf ,String prenom_jtf)
	{
		id_client_recuperer=mod.recherche_id(nom_jtf, prenom_jtf);
		return id_client_recuperer;
	}

}