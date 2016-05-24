package com.jassur.test;
 
 import com.jassur.model.Modele_Gestion_id;
 import com.jassur.controller.Controleur_Gestion_id;
 import com.jassur.view.Vue_Gestion_id;

/**Main du programme 
 * @param 
 * @return 
 * @author Sarah
 * @see Modele_Gestion_id,Controleur_Gestion_id,Vue_Gestion_id
 */
public class Main_MVC {
	
	public static void main(String[] args) {
	    //Instanciation de notre modele
		Modele_Gestion_id Modele = new Modele_Gestion_id();
	    //Creation du controleur
		Controleur_Gestion_id controler = new Controleur_Gestion_id(Modele);
	    //Creation de notre fenetre avec le controleur en parametre
	    Vue_Gestion_id Vue = new Vue_Gestion_id(controler);
	    
	  }
}
