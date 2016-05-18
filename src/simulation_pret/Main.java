package simulation_pret;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//METTRE EN ANGLAIS code/COMMENTAIRES
//test


public class Main extends JFrame implements ActionListener {

	//declaration des variables
	private JButton recherche =new JButton("chercher client");
	private JButton annule =new JButton("annuler");
	private gestion_id gestion_id= new gestion_id();
	private JLabel label =new JLabel();
	
	
	public Main()
	{
		//police, taille et couleur du texte du jlabel 
		Font police = new Font("Arial", Font.BOLD, 12);
		label.setFont(police);
		label.setForeground(Color.BLACK);
		
		//on ecoute les boutons
		recherche.addActionListener(this);
		annule.addActionListener(this);
		
		/*interface graphique de la fenetre*/		
		label.setText("Bienvenu sur l'application de simulation de pret ");
		
		this.setLayout(null);
		this.add(recherche);
		this.add(annule);
		this.add(label);
		//position des boutons et du label
		label.setBounds(250, 30, 400, 60);
		recherche.setBounds(200, 220, 200, 30);
		annule.setBounds(450, 220, 200, 30);
		
		this.setTitle("Comparateur de simulations");
		this.setSize(new Dimension(800,500));	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
		this.setLocationRelativeTo(null);
		this.setVisible(true);		
	}
	
	//methode qui gère le clic des boutons
	public void actionPerformed(ActionEvent arg0) {
				
			
		//Clic du bouton recherche
		if(arg0.getSource() == recherche )
		{	//appel de l'application id pour saisir nom prenom 
			gestion_id.id();
			this.dispose();
			
		}
		if(arg0.getSource() == annule )
		{
			this.dispose();
		}
		
	}
	
	//executable du programme qui cree le main
	public static void main(String[] args){  
		   
		Main j=new Main();
		
    
	}
}
