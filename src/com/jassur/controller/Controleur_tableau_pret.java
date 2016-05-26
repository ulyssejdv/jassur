package com.jassur.controller;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Modele_tableau_pret;

/**Controleur du tableau pret
 * Controler of loans table
 * @param 
 * @return
 * @author Sarah
 * @see Modele_tableau_pret
 */	
public class Controleur_tableau_pret {

	private int id_type_loan;
	private int nb_loan;
	private Object[][] data_jtable ; 
	
	
	/**Methode qui recupere l'id du type pret passe en parametre
	 * Method that retrieve the type loan id that we have in parameter
	 * @param type_loan
	 * @return
	 * @author Sarah
	 * @see Modele_table_loan
	 */
	public void get_recherche_id_pret(String type_pret)
	{
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.ID, "clients/"+type_pret);
		String rep = Message.execRequest(rb.toJSONString());		
		id_type_loan=Integer.parseInt(rep);
		
		
	}
	/**Methode qui renvoie le nombre de prets pour un type de pret 
	 * Method that send the number of loans for a type of loan that we selected
	 * @param id_client,type_loan
	 * @return nb_loan
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public int get_nombre_pret_client(int id_client,String type_loan )
	{
		
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.NB_simulation_pret, "clients/"+id_type_loan+"/"+id_client);
		String rep = Message.execRequest(rb.toJSONString());		
		nb_loan=Integer.parseInt(rep);
		
		
		
		data_jtable= new Object[nb_loan][5];
		this.set_tableau_donnee(type_loan, id_client);
		
		return nb_loan;
	}
	/**Methode qui verifie que le client a au moins un pret du type demander
	 * Verify if the client have a loan (min 1) of the type that he want
	 * @param 
	 * @return boolean
	 * @author Sarah
	 * @see Modele_tableau_loan
	 */
	public boolean verif_tableau_vide()
	{
		if(nb_loan==0)
		{
			return true;
		}
		return false;
		
	}
	/**Methode remplit le tableau de donnees pour le jtable avec les prets du client du type choisi
	 * Method that insert data in the table for the jtable of loans of client (after the type choice)
	 * @param type_loan,id_client
	 * @return 
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public void set_tableau_donnee(String type_loan, int id_client)
	{
		
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.Table, "clients/"+id_type_loan+"/"+id_client+"/"+type_loan);
		String rep = Message.execRequest(rb.toJSONString());		
		
		
		String[] firstSplit =rep.split("[a-z]");
		String []second = new String[firstSplit.length];
		int count=0;
		for(int i=1;i<firstSplit.length;i=i+2)
		{
			second[count]=firstSplit[i];
			count++;
			
			
		}
		count=0;
		for(int line=0 ; line<nb_loan;line++)
		{
			data_jtable[line][0]=type_loan;
			
			for(int column =1 ; column<5 ;column++)
			{
				data_jtable[line][column]=second[count];
				count++;
			}
		}
	}
	/**Method qui retourne a la vue le tableau des prets
	 * Return to the view the table of loans
	 * @param 
	 * @return data_jtable
	 * @author Sarah
	 * @see Modele_tableau_pret
	 */
	public Object[][] get_tableau_donnee()
	{
		
		return data_jtable;
	}
	
}
