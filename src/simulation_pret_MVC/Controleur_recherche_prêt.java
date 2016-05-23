package simulation_pret_MVC;

/**Controleur de la recherche de pret
 * @param 
 * @return
 * @author Sarah
 * @see Modele_recherche_pret
 */	
public class Controleur_recherche_prêt {

	Modele_recherche_prêt mod;
	private int nb_type_pret;
	private String []tab_type_pret  ;
	private int id_du_client;
	
	/**Constructeur du controleur recherche pret
	 * @param m
	 * @return
	 * @author Sarah
	 * @see Modele_recherche_pret
	 */	
	public Controleur_recherche_prêt(Modele_recherche_prêt m )
	{
		mod=m;		
		
	}
	/**methode qui recupere le nom de type de pret d'un client
	 * @param id_client
	 * @return nb_type_pret
	 * @author Sarah
	 * @see Modele_recherche_pret
	 */	
	public int get_nb_pret_client(int id_client )
	{
		
		id_du_client=id_client;
		nb_type_pret=mod.Calcul_nb_type_pret(id_du_client);
		
		tab_type_pret = new String[nb_type_pret]; 
		return nb_type_pret;
	}
	/**Methode qui rempli le tableau des types de pret (tableau combobox)
	 * @param 
	 * @return
	 * @author Sarah
	 * @see Modele_recherche_pr�t
	 */	
	public void set_tab_pret_client()
	{
		
		tab_type_pret=mod.typepret(id_du_client, tab_type_pret) ;
	}
	/**Methode qui retourne le tableau des types de pret pour un client
	 * @param 
	 * @return tab_type_pret
	 * @author Sarah
	 * @see 
	 */	
	public String [] get_tab_pret_client()
	{
		
		return tab_type_pret;
	}
	
}
