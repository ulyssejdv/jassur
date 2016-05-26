package com.jassur.controller;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Modele_recherche_pret;
/**Controleur de la recherche de pret
 * @param 
 * @return
 * @author Sarah
 * @see Modele_recherche_pret
 */	
public class Controleur_recherche_pret {

	
	private int nb_type_loan;
	private String []tab_type_loan  ;
	private int id_of_client;
	
	
	/**methode qui recupere le nom de type de pret d'un client
	 * @param id_client
	 * @return nb_type_pret
	 * @author Sarah
	 * @see Modele_recherche_pret
	 */	
	public int get_nb_pret_client(int id_client )
	{
		
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.NB_simulation_pret, "clients/"+id_client);
		String rep = Message.execRequest(rb.toJSONString());		
		nb_type_loan=Integer.parseInt(rep);
		if(nb_type_loan==0){
			return 0;
		}
		else
		{
			tab_type_loan = new String[nb_type_loan]; 
			return nb_type_loan;
		}
		
	}
	/**Methode qui rempli le tableau des types de pret (tableau combobox)
	 * @param 
	 * @return
	 * @author Sarah
	 * @see Modele_recherche_pret
	 */	
	public void set_tab_pret_client(int id_client ) 
	{
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.Table, "clients/"+id_client+"/"+nb_type_loan);
		String rep = Message.execRequest(rb.toJSONString());
		
		String[] firstSplit =rep.split("\\d+");
		
		int count=0;
		for(int i=2;i<firstSplit.length;i=i+3)
		{
			tab_type_loan[count]=firstSplit[i];
			count++;
			
		}
		
		
		
		
	}
	/**Methode qui retourne le tableau des types de pret pour un client
	 * @param 
	 * @return tab_type_pret
	 * @author Sarah
	 * @see 
	 */	
	public String [] get_tab_pret_client()
	{
		
		return tab_type_loan;
	}
	
}
