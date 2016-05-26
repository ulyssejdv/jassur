package com.jassur.controller;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Modele_Gestion_id;

/**Controleur de la gestion des id  
 * controller of the id management
 * @param 
 * @return
 * @author Sarah
 * @see Modele_Gestion_id 
 */	
public class Controleur_Gestion_id  {
	
	private int id_client_retrieve;

	/**Method qui recupere l'id du client 
	 * function that return the client id 
	 * @param nom_jtf prenom_jtf
	 * @return id_client_recuperer
	 * @author Sarah
	 * @see Modele_Gestion_id 
	 */	
	public int  get_id_client (String nom_jtf ,String prenom_jtf)
	{
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.ID, "clients/"+nom_jtf+"/"+prenom_jtf);
		String rep = Message.execRequest(rb.toJSONString());		
		id_client_retrieve=Integer.parseInt(rep);
		return id_client_retrieve;
	}
}
