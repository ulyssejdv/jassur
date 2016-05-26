package com.jassur.controller;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Modele_tableau_pret;

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
	
	
	/**Methode qui recupere l'id du type pret passe en parametre
	 * @param type_pret
	 * @return
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public void get_recherche_id_pret(String type_pret)
	{
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.ID, "clients/"+type_pret);
		String rep = Message.execRequest(rb.toJSONString());		
		id_type_pret=Integer.parseInt(rep);
		
		
	}
	/**Methode qui renvoie le nombre de prets pour un type de pret 
	 * @param id_client,type_pret
	 * @return nb_pret
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public int get_nombre_pret_client(int id_client,String type_pret )
	{
		System.out.print("Dans nb pret client \n");
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.NB_simulation_pret, "clients/"+id_type_pret+"/"+id_client);
		String rep = Message.execRequest(rb.toJSONString());		
		nb_pret=Integer.parseInt(rep);
		System.out.print("reponse serv nb pret du client "+nb_pret+"\n");
		
		
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
		System.out.print("Dans set tableau donnee \n");
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.Table, "clients/"+id_type_pret+"/"+id_client+"/"+type_pret);
		String rep = Message.execRequest(rb.toJSONString());		
		nb_pret=Integer.parseInt(rep);
		System.out.print("reponse serv set table \n");
		
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
