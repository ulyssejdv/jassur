package simulation_pret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jassur.database.PoolConnection;

public class gestion_id extends JFrame implements ActionListener  {
	
	//METTRE EN ANGLAIS code/COMMENTAIRES
	//test
	
	//declaration des variables pour l'interface et d'utilisation
	protected Connection connect = null;
	private int id_client;
	private String nom;
	private String prenom;
	private Appli_recherchepret o =new Appli_recherchepret();
	private JTextField jtfnom = new JTextField("");
	private JTextField jtfprenom = new JTextField("");
	private JPanel panel=new JPanel();
	private Label label_titre = new Label( );
	private Label label_nom = new Label( );
	private Label label_prenom = new Label( );
	private JButton id =new JButton("rechercher");
	
	
	
	
	
	//la methode id est appeler lorsque le conseillé cliquera sur le bouton recherche de classe main
	public void id()
	{	//Deux lignes qui gere la connexion
		PoolConnection poolConnexion = new PoolConnection();
		this.setConnect(poolConnexion.pop().getConnection());
		
		//on definit le style de la police et on position les jTextField de nom et prenom
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
		//on ecoute les bouton annule et id
	    
	    id.addActionListener(this);
		
		
		/*interface graphique de la fenetre*/
		
	    label_titre.setText("Informations du client");
	    label_nom.setText("Entrez le nom: ");
	    label_prenom.setText("Prenom: ");	
		//tout ce qui gere la jframe (fenetre) avec le titre,la taille etc
		this.setTitle("Comparateur de simulation");
		this.setLayout(null);//ligne qui permet de placer les boutons
		this.add(id);
		this.add(label_titre);
		this.add(label_nom);
		this.add(label_prenom);
		this.add(jtfnom);
		this.add(jtfprenom);
		label_titre.setBounds(290, 30, 125, 60);
		label_nom.setBounds(120, 150, 100, 30);
		jtfnom.setBounds(222, 150, 150, 30);
		label_prenom.setBounds(378, 150, 80, 30);
		jtfprenom.setBounds(460, 150, 150, 30);
		id.setBounds(280, 220, 200, 30);
		
		
		this.setSize(700,500);	
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	//methode qui execute la requete qui permet d'obtenir l'id du client en fonction de nom et prenom saisi
		public void recherche_id()
		{
			
			nom=jtfnom.getText();
			prenom=jtfprenom.getText();
			
			try {
				ResultSet result = this.connect.createStatement(
						ResultSet.TYPE_FORWARD_ONLY,
						ResultSet.CONCUR_READ_ONLY
				).executeQuery(
								"SELECT id_client"
								+ " From clients "
								+ "where last_name='"+nom+"'and first_name='"+prenom+"';");
				if(result.first())
				{
					
					//recuperation de l'id + affichage pour tester
					id_client=result.getInt(1);					
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	//recuperation du click
	public void actionPerformed(ActionEvent arg0) {
		
		
		// bouton recherche : appel de l'application rechercheid qui recherche l'id en fonction du nom
		if(arg0.getSource() == id )
		{	
			this.recherche_id();
			
			//appel du constructeur recherche pret avec parametre id du client
			o.Appli_recherchepret(id_client);
			this.dispose();
		}
		
		
		
	}
	//recup connexion
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}
	
}
