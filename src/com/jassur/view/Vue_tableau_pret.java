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
 * @see Controleur_tableau_pret
 */	
public class Vue_tableau_pret extends JFrame implements ActionListener {

	
	private Controleur_tableau_pret controler = new Controleur_tableau_pret();
	private int nb_loan;
	private JTable jtable_loan ;
	private JButton retour =new JButton("Retour");
	private boolean table_empty;	
	private String  titre_jtable[] = {"Type Pret (choix)", "Creation simulation", "Duree de l'emprunt", "Mensualite"
				, "Montant de l'emprunt"};
	private Object[][] data_jtable ;
	
	
	/** Constructor for the loans table of the view
	 * constructeur de la vue tableau des prets
	 * @param type_of_loan,id_client
	 * @return
	 * @author Sarah
	 * @see Controleur_tableau_pret
	 */		
	public Vue_tableau_pret(String type_of_loan,int id_client)
		{
			
		    
		    controler.get_search_id_loan(type_of_loan);
		    nb_loan=controler.get_number_loan_client(id_client,type_of_loan);
		    table_empty=controler.verif_table_empty();
		    
		    if(table_empty)
		    {
		    	JOptionPane.showMessageDialog(this,"Ce client n'a pas effectué de simulation de pret "+type_of_loan+" ces 2 dernieres annees ",
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
		    	data_jtable= new Object[nb_loan][5];			    	    		
			    data_jtable=controler.get_table_data();		    
			  
				jtable_loan = new JTable(data_jtable, titre_jtable);			    
			    this.getContentPane().add(jtable_loan.getTableHeader(), BorderLayout.NORTH);		   
			    this.getContentPane().add(jtable_loan, BorderLayout.CENTER);
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
