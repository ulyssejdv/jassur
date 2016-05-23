package simulation_pret_MVC;

/**Main du programme 
 * @param 
 * @return 
 * @author Sarah
 * @see Modele_Gestion_id,Controleur_Gestion_id,Vue_Gestion_id
 */
public class Main_MVC {
	
	public static void main(String[] args) {
	    //Instanciation de notre modèle
		Modele_Gestion_id Modele = new Modele_Gestion_id();
	    //Création du contrôleur
		Controleur_Gestion_id controler = new Controleur_Gestion_id(Modele);
	    //Création de notre fenêtre avec le contrôleur en paramètre
	    Vue_Gestion_id Vue = new Vue_Gestion_id(controler);
	    
	  }
}
