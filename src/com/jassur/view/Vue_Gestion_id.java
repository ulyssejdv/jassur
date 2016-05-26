package com.jassur.view;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jassur.controller.Controleur_Gestion_id;

/** la vue pour la gestion du client et sont id
 * @param 
 * @return
 * @author Sarah
 * @see Controleur_Gestion_id,Vue_recherche_prêt
 */	
public class Vue_Gestion_id extends JFrame implements ActionListener{
	
	private JTextField jtfnom = new JTextField("");
	private JTextField jtfprenom = new JTextField("");
	private Label label_titre = new Label( );
	private Label label_nom = new Label( );
	private Label label_prenom = new Label( );
	private JButton id =new JButton("rechercher");
	private Controleur_Gestion_id controler;
	private int id_client;
	private Vue_recherche_pret vue_pret ;
	
	/** Constructeur de la vue pour la gestion du client et son id
	 * @param controler
	 * @return
	 * @author Sarah
	 * @see Controleur_Gestion_id
	 */	
	public Vue_Gestion_id(Controleur_Gestion_id controler)
	{
		this.controler=controler;
		
		Font police = new Font("Arial", Font.BOLD, 12);
		jtfnom.setFont(police);
		jtfnom.setPreferredSize(new Dimension(150, 30));
		jtfnom.setForeground(Color.BLACK);
		jtfprenom.setFont(police);
		jtfprenom.setPreferredSize(new Dimension(150, 30));
		jtfprenom.setForeground(Color.BLACK);
		
		
		label_titre.setFont(police);
		label_titre.setForeground(Color.BLACK);
		
		label_nom.setFont(police);
		label_nom.setForeground(Color.BLACK);
		
		label_prenom.setFont(police);
		label_prenom.setForeground(Color.BLACK);
		
	    
	    id.addActionListener(this);
	    
		
	    label_titre.setText("Informations du client");
	    label_nom.setText("Entrez le nom : ");
	    label_prenom.setText("Prenom : ");	
		
		this.setTitle("Comparateur de simulations");
		this.setLayout(null);
		this.add(id);
		this.add(label_titre);
		this.add(label_nom);
		this.add(label_prenom);
		this.add(jtfnom);
		this.add(jtfprenom);
		label_titre.setBounds(290, 30, 125, 60);
		label_nom.setBounds(120, 150, 90, 30);
		jtfnom.setBounds(215, 150, 130, 30);
		label_prenom.setBounds(350, 150, 60, 30);
		jtfprenom.setBounds(415, 150, 150, 30);
		id.setBounds(280, 220, 200, 30);
		
		
		this.setSize(700,370);	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/** Methode qui permet l'action du bouton id
	 * @param arg0
	 * @return
	 * @author Sarah
	 * @see vue_pret
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == id )
		{	
			String nom=jtfnom.getText();
			String prenom=jtfprenom.getText();
			
			id_client=controler.get_id_client(nom,prenom);
			if(id_client==0)
			{
				JOptionPane.showMessageDialog(this,"Le client "+nom+" "+prenom+" n'existe pas ",
						"Erreur Client",JOptionPane.ERROR_MESSAGE);
				jtfprenom.setText("");
				jtfnom.setText("");
			}
			else
			{
				vue_pret = new Vue_recherche_pret(id_client);
				this.dispose();
			}			
			
			
		}
}
}
