package simulation_pret_MVC;
 
/**Controleur de la tableau pret
 * @param 
 * @return
 * @author Sarah
 * @see Modele_tableau_pret
 */	
public class Controleur_tableau_pret {

	private Modele_tableau_pret mod;
	private int id_type_pret;
	private int nb_pret;
	private Object[][] donne_jtable ;
	
	/**Constructeur du controleur tableau pret
	 * @param m
	 * @return
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */	
	public Controleur_tableau_pret(Modele_tableau_pret m )
	{
		mod=m;		
		
	}
	/**Methode qui recupere l'id du type pret passe en parametre
	 * @param type_pret
	 * @return
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public void get_recherche_id_prêt(String type_pret)
	{
		id_type_pret=mod.recherche_id_pret(type_pret);
		
	}
	/**Methode qui renvoie le nombre de prets pour un type de pret 
	 * @param id_client,type_pret
	 * @return nb_pret
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public int get_nombre_pret_client(int id_client,String type_pret )
	{
		nb_pret=mod.nombre_pret_client(id_type_pret, id_client);
		
		donne_jtable= new Object[nb_pret][5];
		this.set_tableau_donnee(type_pret, id_client);
		
		return nb_pret;
	}
	/**Methode qui verifie que le client a au moins un pret du type demander
	 * @param 
	 * @return boolean
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public boolean verif_tableau_vide()
	{
		if(nb_pret==0)
		{
			return true;
		}
		return false;
		
	}
	/**Methode remplit le tableau de donnees pour le jtable avec les prets du client du type choisi
	 * @param type_pret,id_client
	 * @return 
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public void set_tableau_donnee(String type_pret, int id_client)
	{
		donne_jtable=mod.recuperation_donne_pret(donne_jtable,id_type_pret,id_client ,type_pret);
	}
	/**Method qui retourne a la vue le tableau des prets
	 * @param 
	 * @return donne_jtable
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public Object[][] get_tableau_donnee()
	{
		
		return donne_jtable;
	}
	
}
