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
	
	private JTextField jtfLastName = new JTextField("");
	private JTextField jtfFirstName = new JTextField("");
	private Label label_title = new Label( );
	private Label label_LastName = new Label( );
	private Label label_FirstName = new Label( );
	private JButton id =new JButton("rechercher");
	private Controleur_Gestion_id controler;
	private int id_client;
	private Vue_recherche_pret view_loan ;
	
	/** Constructeur de la vue pour la gestion du client et son id
	 * Constructor of the view: for the management of id client
	 * @param controler
	 * @return
	 * @author Sarah
	 * @see Controleur_Gestion_id
	 */	
	public Vue_Gestion_id(Controleur_Gestion_id controler)
	{
		this.controler=controler;
		
		Font police = new Font("Arial", Font.BOLD, 12);
		jtfLastName.setFont(police);
		jtfLastName.setPreferredSize(new Dimension(150, 30));
		jtfLastName.setForeground(Color.BLACK);
		jtfFirstName.setFont(police);
		jtfFirstName.setPreferredSize(new Dimension(150, 30));
		jtfFirstName.setForeground(Color.BLACK);
		
		
		label_title.setFont(police);
		label_title.setForeground(Color.BLACK);
		
		label_LastName.setFont(police);
		label_LastName.setForeground(Color.BLACK);
		
		label_FirstName.setFont(police);
		label_FirstName.setForeground(Color.BLACK);
		
	    
	    id.addActionListener(this);
	    
		
	    label_title.setText("Informations du client");
	    label_LastName.setText("Entrez le nom : ");
	    label_FirstName.setText("Prenom : ");	
		
		this.setTitle("Comparateur de simulations");
		this.setLayout(null);
		this.add(id);
		this.add(label_title);
		this.add(label_LastName);
		this.add(label_FirstName);
		this.add(jtfLastName);
		this.add(jtfFirstName);
		label_title.setBounds(290, 30, 125, 60);
		label_LastName.setBounds(120, 150, 90, 30);
		jtfLastName.setBounds(215, 150, 130, 30);
		label_FirstName.setBounds(350, 150, 60, 30);
		jtfFirstName.setBounds(415, 150, 150, 30);
		id.setBounds(280, 220, 200, 30);
		
		
		this.setSize(700,370);	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/** Methode qui permet l'action du bouton id
	 * Action on the id button 
	 * @param arg0
	 * @return
	 * @author Sarah
	 * @see vue_pret
	 */
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == id )
		{	
			String lastName=jtfLastName.getText();
			String firstName=jtfFirstName.getText();
			
			id_client=controler.get_id_client(lastName,firstName);
			if(id_client==0)
			{
				JOptionPane.showMessageDialog(this,"Le client "+lastName+" "+firstName+" n'existe pas ",
						"Erreur Client",JOptionPane.ERROR_MESSAGE);
				jtfFirstName.setText("");
				jtfLastName.setText("");
			}
			else
			{
				view_loan = new Vue_recherche_pret(id_client);
				this.dispose();
			}			
			
			
		}
}
}
