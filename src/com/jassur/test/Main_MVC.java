package com.jassur.test;
 
 import com.jassur.model.Model_Manage_id;
 import com.jassur.controller.Controller_Manage_id;
 import com.jassur.view.View_Manage_id;

/**Main du programme 
 * @param 
 * @return 
 * @author Sarah
 * @see Modele_Gestion_id,Controleur_Gestion_id,Vue_Gestion_id
 */
public class Main_MVC {
	
	public Main_MVC()
	{
		 //Instanciation de notre modele
		Model_Manage_id Modele = new Model_Manage_id();
	    //Creation du controleur
		Controller_Manage_id controler = new Controller_Manage_id();
	    //Creation de notre fenetre avec le controleur en parametre
		View_Manage_id Vue = new View_Manage_id(controler);
	}
	
	public static void main(String[] args) {
	   
		Main_MVC main = new  Main_MVC();
	  }
}
