package simulation_pret_MVC;
 
/**Main du programme 
 * @param 
 * @return 
 * @author Sarah
 * @see Modele_Gestion_id,Controleur_Gestion_id,Vue_Gestion_id
 */
public class Main_MVC {
	
	public static void main(String[] args) {
	    //Instanciation de notre mod�le
		Modele_Gestion_id Modele = new Modele_Gestion_id();
	    //Cr�ation du contr�leur
		Controleur_Gestion_id controler = new Controleur_Gestion_id(Modele);
	    //Cr�ation de notre fen�tre avec le contr�leur en param�tre
	    Vue_Gestion_id Vue = new Vue_Gestion_id(controler);
	    
	  }
}
