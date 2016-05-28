package com.jassur.controller;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;


/**Controleur de la gestion des id  
 * controller of the id management
 * @param 
 * @return
 * @author Sarah
 */	
public class Controller_Manage_id  {
	
	private int id_client_retrieve;

	/**Method qui recupere l'id du client 
	 * function that return the client id 
	 * @param lastName_jtf firstName_jtf
	 * @return id_client_retrieve
	 * @author Sarah
	 */	
	public int  get_id_client (String lastName_jtf ,String firstName_jtf)
	{
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.ID, "clients/"+lastName_jtf+"/"+firstName_jtf);
		String rep = Message.execRequest(rb.toJSONString());		
		id_client_retrieve=Integer.parseInt(rep);
		return id_client_retrieve;
	}
}
