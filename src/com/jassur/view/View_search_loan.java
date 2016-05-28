package com.jassur.view;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.jassur.controller.Controller_search_loan;
/** la vue pour la recherche des type pret
 * View for the loan type research
 * @param 
 * @return
 * @author Sarah
 * @see Controleur_recherche_pret,Vue_tableau_pret
 */	
public class View_search_loan extends JFrame implements ActionListener {
	
	
	private Controller_search_loan controler = new Controller_search_loan();
	private View_table_loan View_table_loan;	
	private JButton selection =new JButton("Lancer");
	private Label label_title = new Label( );
	private Label label_text = new Label( );
	private JComboBox combo ;
	private int id_client;
	private String []tab_type_pret_view;
	
	/** Constructeur de la vue pour la recherche des types pret
	 * Constructor of the view for the type loan search
	 * @param id_client
	 * @return
	 * @author Sarah
	 * @see Controleur_recherche_pret
	 */	
	public View_search_loan(int id_client)
	{
		this.id_client=id_client;
		
		
		
		int nb_type_loan=controler.get_nb_loan_client(id_client);
		if(nb_type_loan==0)
		{
			JOptionPane.showMessageDialog(this,"Ce client n'a pas effectue de pret en simulation.",
					"Avertissement Pret",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Font police = new Font("Arial", Font.BOLD, 12);
			label_title.setFont(police);
			label_title.setForeground(Color.BLACK);
			label_text.setFont(police);
			label_text.setForeground(Color.BLACK);
			
			selection.addActionListener(this);
			
			
			label_title.setText("TYPE de pret");						
			label_text.setText("Choisir le type de pret :");	
			
			
			this.setLayout(null);
			this.add(label_title);
			this.add(label_text);
			this.add(selection);
			label_title.setBounds(290, 30, 120, 60);
			label_text.setBounds(200, 150, 150, 30);
			selection.setBounds(265, 220, 150, 30);
			
			this.setSize(700,370);					
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			this.setTitle("Comparateur de simulations");
			tab_type_pret_view = new String[nb_type_loan]; 
			controler.set_tab_loan_client(id_client);
			tab_type_pret_view=controler.get_tab_loan_client();
			
			combo = new JComboBox(tab_type_pret_view);
			this.add(combo);
			combo.setBounds(360, 150, 150, 30);
		}
	
	}	
	/** Methode qui permet l'action du bouton selection
	 * Action on the selection button 
	 * @param arg0
	 * @return
	 * @author Sarah
	 * @see Vue_tableau_pret
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		String selection_combobox = (String) combo.getSelectedItem();
		View_table_loan = new View_table_loan( selection_combobox, id_client);
		
	}
}
