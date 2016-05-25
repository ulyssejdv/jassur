package com.jassur.view;
 
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import com.jassur.controller.Controleur_tableau_pret;
import com.jassur.model.Modele_tableau_pret;
/** la vue pour le tableau des pret
 * @param 
 * @return
 * @author Sarah
 * @see Modele_tableau_pret,Controleur_tableau_pret
 */	
public class Vue_tableau_pret extends JFrame implements ActionListener {

	private Modele_tableau_pret modele= new Modele_tableau_pret();
	private Controleur_tableau_pret controler = new Controleur_tableau_pret(modele);
	private int nb_pret;
	private JTable jtableau_pret ;
	private JButton retour =new JButton("Retour");
	private boolean tableau_vide;	
	private String  titre_jtable[] = {"Type Pret (choix)", "Creation simulation", "Duree de l'emprunt", "Mensualite"
				, "Montant de l'emprunt"};
	private Object[][] donne_jtable ;
	
	
	/** Constructor for the loans table of the view
	 * constructeur de la vue tableau des prets
	 * @param type_de_pret,id_client
	 * @return
	 * @author Sarah
	 * @see Controleur_tableau_pret
	 */		
	public Vue_tableau_pret(String type_de_pret,int id_client)
		{
			
		    
		    controler.get_recherche_id_pret(type_de_pret);
		    nb_pret=controler.get_nombre_pret_client(id_client,type_de_pret);
		    tableau_vide=controler.verif_tableau_vide();
		    
		    if(tableau_vide)
		    {
		    	JOptionPane.showMessageDialog(this,"Ce client n'a pas effectué de simulation de pret "+type_de_pret+" ces 2 dernieres annees ",
						"Avertissement Pret",JOptionPane.ERROR_MESSAGE);
		    	
		    }
		    else
		    {
		    					
				retour.addActionListener(this);				
				
				this.setSize(800,500);		
				this.setVisible(true);
				this.setLocationRelativeTo(null);
			    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    this.setTitle("Comparateur de simulations");
			    
			  
			    this.getContentPane().add(retour,BorderLayout.SOUTH);
		    	donne_jtable= new Object[nb_pret][5];			    	    		
			    donne_jtable=controler.get_tableau_donnee();		    
			  
				jtableau_pret = new JTable(donne_jtable, titre_jtable);			    
			    this.getContentPane().add(jtableau_pret.getTableHeader(), BorderLayout.NORTH);		   
			    this.getContentPane().add(jtableau_pret, BorderLayout.CENTER);
		    }
		    
		}
		
	/** Methode qui permet l'action du bouton retour
	 * this method permit the button action "return"
	 * @param arg0
	 * @return
	 * @author Sarah
	 * @see 
	 */	
		public void actionPerformed(ActionEvent arg0) {
			
			if(arg0.getSource() == retour )
			{		
				this.getContentPane().removeAll();
				this.dispose();	
				
			}	
		}
}
