package com.jassur.view;
 
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.jassur.controller.Controleur_recherche_pret;
import com.jassur.model.Modele_recherche_pret;
/** la vue pour la recherche des type pret
 * @param 
 * @return
 * @author Sarah
 * @see Modele_recherche_pret,Controleur_recherche_pret,Vue_tableau_pret
 */	
public class Vue_recherche_pret extends JFrame implements ActionListener {
	
	
	private Controleur_recherche_pret controler = new Controleur_recherche_pret();
	private Vue_tableau_pret Vue_tableau_pret;	
	private JButton selection =new JButton("Lancer");
	private Label label_titre = new Label( );
	private Label label_texte = new Label( );
	private JComboBox combo ;
	private int id_client;
	private String []tab_type_pret_vue;
	
	/** Constructeur de la vue pour la recherche des types pret
	 * @param id_client
	 * @return
	 * @author Sarah
	 * @see Controleur_recherche_pret
	 */	
	public Vue_recherche_pret(int id_client)
	{
		this.id_client=id_client;
		
		Font police = new Font("Arial", Font.BOLD, 12);
		label_titre.setFont(police);
		label_titre.setForeground(Color.BLACK);
		label_texte.setFont(police);
		label_texte.setForeground(Color.BLACK);
		
		selection.addActionListener(this);
		
		
		label_titre.setText("TYPE de pret");						
		label_texte.setText("Choisir le type de pret :");	
		
		
		this.setLayout(null);
		this.add(label_titre);
		this.add(label_texte);
		this.add(selection);
		label_titre.setBounds(290, 30, 120, 60);
		label_texte.setBounds(200, 150, 130, 30);
		selection.setBounds(265, 220, 150, 30);
		
		this.setSize(800,500);					
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Comparateur de simulations");
		
		int nb_type_pret=controler.get_nb_pret_client(id_client);

		tab_type_pret_vue = new String[nb_type_pret]; 
		controler.set_tab_pret_client(id_client);
		tab_type_pret_vue=controler.get_tab_pret_client();
		
		combo = new JComboBox(tab_type_pret_vue);
		this.add(combo);
		combo.setBounds(340, 150, 100, 30);
	}
	
	/** Methode qui permet l'action du bouton selection
	 * @param arg0
	 * @return
	 * @author Sarah
	 * @see Vue_tableau_pret
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		String selection_combobox = (String) combo.getSelectedItem();
		Vue_tableau_pret = new Vue_tableau_pret( selection_combobox, id_client);
		
	}
}
