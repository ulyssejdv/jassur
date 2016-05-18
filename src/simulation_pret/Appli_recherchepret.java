package simulation_pret;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import com.jassur.database.PoolConnection;

//METTRE EN ANGLAIS code/COMMENTAIRES




public class Appli_recherchepret extends JFrame implements ActionListener {
	
	protected Connection connect = null;
	private int id_client;	//id du client
	private String []tab_type_pret  ;//tableau pour stocker les differents type de pret (tableau pour la combobox)
	private Appli_Tab_Pret o =new Appli_Tab_Pret();
	private JButton selection =new JButton("Lancer");
	private Label label_titre = new Label( );
	private Label label_texte = new Label( );
	private JComboBox combo ;	
	
	
	//Constructeur qui prend en parametre l'id du client et affiche les types de pret des simulations du client dans combobox
	public void Appli_recherchepret(int id_client){
			
				
			this.id_client=id_client;
			
			//police, taille et couleur du texte du jlabel 
			Font police = new Font("Arial", Font.BOLD, 12);
			label_titre.setFont(police);
			label_titre.setForeground(Color.BLACK);
			label_texte.setFont(police);
			label_texte.setForeground(Color.BLACK);
			
			//Connexion
			PoolConnection poolConnexion = new PoolConnection();
			this.setConnect(poolConnexion.pop().getConnection());
			
			//ecouteur du btn selection
			selection.addActionListener(this);
			
			/*interface graphique de la fenetre*/
			label_titre.setText("TYPE de prêt");						
			label_texte.setText("Choisir le type de prêt :");	
			
			
			this.setLayout(null);
			this.add(label_titre);
			this.add(label_texte);
			this.add(selection);
			label_titre.setBounds(290, 30, 120, 60);
			label_texte.setBounds(200, 150, 170, 30);
			selection.setBounds(265, 220, 150, 30);
			
			this.setSize(700,450);					
			this.setVisible(true);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Comparateur de simulations");
			 //appel des methodes utilisees dans la classe
			this.Calcul_nb_type_pret();//Recupere le nombre de type de pret en rapport a simulation pour le client
			this.typepret();//Recupere l'id de type de pret selectionner dans la combobox
			
			combo = new JComboBox(tab_type_pret);//creation de la combobox avec taille adapter au nb type pret du client
			this.add(combo);
			combo.setBounds(370, 150, 120, 30);
		}
	
public void Calcul_nb_type_pret(){
		
		int nb_type_pret;
		try {
			ResultSet res = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
					"SELECT COUNT(*) "
					+ "FROM categories ;");
			if(res.first())
			{
				nb_type_pret=res.getInt(1);
				tab_type_pret = new String[nb_type_pret];	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void typepret(){
		
		int cpt=0;//compteur des elements pour mettre dans le tableau
		
		try {//requete qui trouve les types de pret fait par l'utilisateur
			ResultSet res = this.connect.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
							"SELECT DISTINCT label_category "
							+ "FROM categories,loans"
							+ " WHERE category_id = id_category and client_id = '"+id_client+"';");
		while(res.next()){/*tant qu'il y a des resultats boucle*/
			
			tab_type_pret[cpt]= res.getString(1);// l'element est placé dans le tableau de la combobox
			cpt++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//recupere le type pret selectionné
		String selection_combobox = (String) combo.getSelectedItem();
		
		//appelle de l'application d'affichage des prêts en envoyant le type de pret et id du client
		o.Appli_Tab_Pret(selection_combobox,id_client);
		
	}
	
	//Connexion
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}


}
