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

	
	private int nb_type_pret;
	private String []tab_type_pret  ;
	private int id_du_client;
	
	
	/**methode qui recupere le nom de type de pret d'un client
	 * @param id_client
	 * @return nb_type_pret
	 * @author Sarah
	 * @see Modele_recherche_pret
	 */	
	public int get_nb_pret_client(int id_client )
	{
		System.out.print("Dans nb pret client"+id_client+"\n");
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.NB_simulation_pret, "clients/"+id_client);
		String rep = Message.execRequest(rb.toJSONString());		
		nb_type_pret=Integer.parseInt(rep);
		System.out.print("reponse serv nb type pret "+nb_type_pret+"\n");
		tab_type_pret = new String[nb_type_pret]; 
		return nb_type_pret;
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
		RequestBuilder rb = new RequestBuilder(RequestBuilder.Table, "clients/"+id_client+"/"+nb_type_pret);
		String rep = Message.execRequest(rb.toJSONString());
		
		String[] firstSplit =rep.split("\\d+");
		String secondSplit[] = new String[firstSplit.length];
		int count=0;
		for(int i=2;i<firstSplit.length;i=i+3)
		{
			tab_type_pret[count]=firstSplit[i];
			count++;
			
		}
		for(int j=0;j<tab_type_pret.length;j++)
		{
			System.out.print("apres split ,,,"+tab_type_pret[j]+"\n");
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
		
		return tab_type_pret;
	}
	
}
