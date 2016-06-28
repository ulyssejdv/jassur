package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import com.jassur.controller.LoanController;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;
import com.jassur.model.User;

public class LoanFixedRateSimulationPanel extends JPanel {
	
	private JTextField textFieldMontantTotal;
	private JTextField textDureDuPrt;
	private JTextField textFieldFraisDeDossier;
	private JTextField textFieldEcheanceAssur;
	
	
	
	private Client client;
	private Loan loan;
	
	private double tauxGen;
	
	/**
	* Create the panel.
	 * @wbp.parser.constructor
	*/
	public LoanFixedRateSimulationPanel(ArrayList<Category> categories,Client client, double tauxGen) {
		setBackground(Color.WHITE);
		
		this.tauxGen = tauxGen;

		this.client = client;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel Container = new JPanel();
		Container.setBackground(Color.WHITE);
		add(Container);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		Container.add(panelCenter);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panelCenter.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTypePret = new JLabel("Type de pret :");
		lblTypePret.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblTypePret);
		
		JComboBox comboBoxCategories = new JComboBox();
		panel_4.add(comboBoxCategories);
		for (Category object: categories){
			comboBoxCategories.addItem(object.getLabelCategory());
		}
		
		JPanel panel_a = new JPanel();
		panel_a.setBackground(Color.WHITE);
		panelCenter.add(panel_a);
		panel_a.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lblMontantTotalDu = new JLabel("Montant total du prêt : ");
		panel_a.add(lblMontantTotalDu);
		lblMontantTotalDu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantTotalDu.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
		
		textFieldMontantTotal = new JTextField();
		panel_a.add(textFieldMontantTotal);
		textFieldMontantTotal.setColumns(10);
		
		JPanel panel_b = new JPanel();
		panel_b.setBackground(Color.WHITE);
		panelCenter.add(panel_b);
		panel_b.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDureDuPrt = new JLabel("Durée du prêt : ");
		panel_b.add(lblDureDuPrt);
		lblDureDuPrt.setHorizontalAlignment(SwingConstants.CENTER);
		
		textDureDuPrt = new JTextField();
		panel_b.add(textDureDuPrt);
		textDureDuPrt.setColumns(10);
		
		JPanel panel_c = new JPanel();
		panel_c.setBackground(Color.WHITE);
		panelCenter.add(panel_c);
		panel_c.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEcheanceAssur = new JLabel("Echéance de l'assurance :");
		panel_c.add(lblEcheanceAssur);
		lblEcheanceAssur.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldEcheanceAssur = new JTextField();
		panel_c.add(textFieldEcheanceAssur);
		textFieldEcheanceAssur.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panelCenter.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblTauxDintrtDu = new JLabel("Taux d'intérêt du prêt :      ");
		panel.add(lblTauxDintrtDu);
		lblTauxDintrtDu.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox comboBoxTauxDintrtDu = new JComboBox();

		String[] taux={String.valueOf(this.tauxGen)};
		comboBoxTauxDintrtDu.addItem(taux[0]);
		//comboBoxTauxDintrtDu.addItem(taux[1]);
		//comboBoxTauxDintrtDu.addItem(taux[2]);

		/*for (Rate object: rates){
			comboBoxTauxDintrtDu.addItem(object.getInterestRate());*/
		panel.add(comboBoxTauxDintrtDu);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panelCenter.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblFraisDeDossier = new JLabel("Frais de dossier : ");
		panel_1.add(lblFraisDeDossier);
		lblFraisDeDossier.setHorizontalAlignment(SwingConstants.CENTER);
		
		textFieldFraisDeDossier = new JTextField();
		panel_1.add(textFieldFraisDeDossier);
		textFieldFraisDeDossier.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panelCenter.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCoutTotal = new JLabel("Coût total du prêt :");
		panel_2.add(lblCoutTotal);
		lblCoutTotal.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblResultatCoutTotal = new JLabel("");
		lblResultatCoutTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblResultatCoutTotal);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panelCenter.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEcheanceHorsAss = new JLabel("Echéance hors assurance : ");
		panel_3.add(lblEcheanceHorsAss);
		lblEcheanceHorsAss.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblResultatEcheanceHorsAss = new JLabel("");
		lblResultatEcheanceHorsAss.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblResultatEcheanceHorsAss);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		panelCenter.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEchanceAssuranceComprise = new JLabel("Echéance assurance comprise : ");
		lblEchanceAssuranceComprise.setBackground(Color.WHITE);
		panel_7.add(lblEchanceAssuranceComprise);
		lblEchanceAssuranceComprise.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JLabel lblResEcheanceAssuranceComprise = new JLabel("");
		lblResEcheanceAssuranceComprise.setBackground(Color.WHITE);
		lblResEcheanceAssuranceComprise.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblResEcheanceAssuranceComprise);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.setBackground(Color.WHITE);
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double resCoutTotal=0;
				resCoutTotal+=Double.parseDouble(textFieldMontantTotal.getText()) *(1+(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+(Integer.parseInt(textDureDuPrt.getText())*Double.parseDouble(textFieldEcheanceAssur.getText()))
						+Double.parseDouble(textFieldFraisDeDossier.getText());
				lblResultatCoutTotal.setText(Double.toString(resCoutTotal));
				
				double resEcheanceAssuranceComprise=0;
				resEcheanceAssuranceComprise+=(Double.parseDouble(textFieldMontantTotal.getText()) *(1+(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+(Integer.parseInt(textDureDuPrt.getText())*Double.parseDouble(textFieldEcheanceAssur.getText()))
						+Double.parseDouble(textFieldFraisDeDossier.getText()))/Integer.parseInt(textDureDuPrt.getText());
				lblResEcheanceAssuranceComprise.setText(Double.toString(resEcheanceAssuranceComprise));

				double resultatEcheanceHorsAss =0;
				resultatEcheanceHorsAss+=(Double.parseDouble(textFieldMontantTotal.getText()) *(1+(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+Double.parseDouble(textFieldFraisDeDossier.getText()))/Integer.parseInt(textDureDuPrt.getText());
				lblResultatEcheanceHorsAss.setText(Double.toString(resultatEcheanceHorsAss));
				
				lblResultatCoutTotal.updateUI();
				lblResEcheanceAssuranceComprise.updateUI();
				lblResultatEcheanceHorsAss.updateUI();
			}
		});
		panelCenter.add(btnCalculer);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.WHITE);
		add(panelSouth, BorderLayout.SOUTH);
		
		JButton btnEnregistrerLaSimulation = new JButton("Enregistrer la simulation");
		btnEnregistrerLaSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loan l=new Loan();
				l.setCreatedAt(new Date(new java.util.Date().getTime()));
				l.setCategory(categories.get(comboBoxCategories.getSelectedIndex()));
				l.setAmount(Integer.parseInt(textFieldMontantTotal.getText()));
				l.setTotalDuration(Integer.parseInt(textDureDuPrt.getText()));
				double resCoutTotal=0;
				/*resCoutTotal+=Double.parseDouble(textFieldMontantTotal.getText())*(1+(2*Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+(Integer.parseInt(textDureDuPrt.getText())*Double.parseDouble(textFieldEcheanceAssur.getText()))
						+Double.parseDouble(textFieldFraisDeDossier.getText());
				lblResultatCoutTotal.setText(Double.toString(resCoutTotal));
				l.setTotalAmount(resCoutTotal);*/
				resCoutTotal=Double.parseDouble(lblResultatCoutTotal.getText());
				l.setTotalAmount(resCoutTotal);
				Rate r=new Rate();
				r.setDuration(Integer.parseInt(textDureDuPrt.getText()));
				r.setInterestRate(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()]));
				r.setMonthlyPayment(resCoutTotal/r.getDuration());
				l.addRate(r);
				
				User u=new User();
				
				State s=new State();
				s.setLabelState("Simulation");
				s.setUserId(1);
				
				
				l.addState(s);

				
				l.setClient(LoanFixedRateSimulationPanel.this.client);
			
				LoanController lc=new LoanController();
				lc.createSimulation(l);
				
				
			}
		});

		panelSouth.add(btnEnregistrerLaSimulation);
		
	}
	
	public LoanFixedRateSimulationPanel(ArrayList<Category> categories,Client client,Loan loan) {

		this.client = client;
		this.loan=loan;
		this.setBackground(Color.WHITE);
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setLayout(new BorderLayout(0, 0));
		
		JPanel Container = new JPanel();
		add(Container);
		Container.setBackground(Color.WHITE);
		
		JPanel panelCenter = new JPanel();
		Container.add(panelCenter);
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelCenter.setBackground(Color.WHITE);
		
		JPanel panel_4 = new JPanel();
		panelCenter.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		panel_4.setBackground(Color.WHITE);
		
		JLabel lblTypePret = new JLabel("Type de pret :");
		lblTypePret.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypePret.setBackground(Color.WHITE);
		panel_4.add(lblTypePret);
		
		JComboBox comboBoxCategories = new JComboBox();
		panel_4.add(comboBoxCategories);
		for (Category object: categories){
			comboBoxCategories.addItem(object.getLabelCategory());
		}
		comboBoxCategories.setSelectedIndex(loan.getCategory().getId()-1);
		
		JPanel panel_a = new JPanel();
		panelCenter.add(panel_a);
		panel_a.setLayout(new GridLayout(0, 2, 0, 0));
		panel_a.setBackground(Color.WHITE);
		JLabel lblMontantTotalDu = new JLabel("Montant total du prêt : ");
		lblMontantTotalDu.setBackground(Color.WHITE);
		panel_a.add(lblMontantTotalDu);
		lblMontantTotalDu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantTotalDu.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
		
		
		textFieldMontantTotal = new JTextField();
		panel_a.add(textFieldMontantTotal);
		textFieldMontantTotal.setColumns(10);
		textFieldMontantTotal.setText(Integer.toString(loan.getAmount()));
		textFieldMontantTotal.setBackground(Color.WHITE);
		
		JPanel panel_b = new JPanel();
		panelCenter.add(panel_b);
		panel_b.setLayout(new GridLayout(0, 2, 0, 0));
		panel_b.setBackground(Color.WHITE);
		
		JLabel lblDureDuPrt = new JLabel("Durée du prêt : ");
		panel_b.add(lblDureDuPrt);
		lblDureDuPrt.setHorizontalAlignment(SwingConstants.CENTER);
		lblDureDuPrt.setBackground(Color.WHITE);
		
		textDureDuPrt = new JTextField();
		panel_b.add(textDureDuPrt);
		textDureDuPrt.setColumns(10);
		textDureDuPrt.setText(Integer.toString(loan.getTotalDuration()));
		textDureDuPrt.setBackground(Color.WHITE);
		
		JPanel panel_c = new JPanel();
		panelCenter.add(panel_c);
		panel_c.setLayout(new GridLayout(0, 2, 0, 0));
		panel_c.setBackground(Color.WHITE);
		
		JLabel lblEcheanceAssur = new JLabel("Echéance de l'assurance :");
		panel_c.add(lblEcheanceAssur);
		lblEcheanceAssur.setHorizontalAlignment(SwingConstants.CENTER);
		lblEcheanceAssur.setBackground(Color.WHITE);
		
		textFieldEcheanceAssur = new JTextField();
		panel_c.add(textFieldEcheanceAssur);
		textFieldEcheanceAssur.setColumns(10);
		textFieldEcheanceAssur.setText("0");
		textFieldEcheanceAssur.setBackground(Color.WHITE);
		
		JPanel panel = new JPanel();
		panelCenter.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		panel.setBackground(Color.WHITE);
		
		JLabel lblTauxDintrtDu = new JLabel("Taux d'intérêt du prêt :      ");
		panel.add(lblTauxDintrtDu);
		lblTauxDintrtDu.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTauxDintrtDu.setBackground(Color.WHITE);
		
		JComboBox comboBoxTauxDintrtDu = new JComboBox();

		String[] taux={"1.2","1.5","2"};
		comboBoxTauxDintrtDu.addItem(taux[0]);
		comboBoxTauxDintrtDu.addItem(taux[1]);
		comboBoxTauxDintrtDu.addItem(taux[2]);

		/*for (Rate object: rates){
			comboBoxTauxDintrtDu.addItem(object.getInterestRate());*/
		panel.add(comboBoxTauxDintrtDu);
		
		JPanel panel_1 = new JPanel();
		panelCenter.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		panel_1.setBackground(Color.WHITE);
		
		JLabel lblFraisDeDossier = new JLabel("Frais de dossier : ");
		panel_1.add(lblFraisDeDossier);
		lblFraisDeDossier.setHorizontalAlignment(SwingConstants.CENTER);
		lblFraisDeDossier.setBackground(Color.WHITE);
		
		textFieldFraisDeDossier = new JTextField();
		panel_1.add(textFieldFraisDeDossier);
		textFieldFraisDeDossier.setColumns(10);
		textFieldFraisDeDossier.setText("0");
		textFieldFraisDeDossier.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panelCenter.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		panel_2.setBackground(Color.WHITE);
		
		JLabel lblCoutTotal = new JLabel("Coût total du prêt :");
		panel_2.add(lblCoutTotal);
		lblCoutTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoutTotal.setBackground(Color.WHITE);
		
		JLabel lblResultatCoutTotal = new JLabel("");
		lblResultatCoutTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblResultatCoutTotal);
		lblResultatCoutTotal.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		panelCenter.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		panel_3.setBackground(Color.WHITE);
		
		JLabel lblEcheanceHorsAss = new JLabel("Echéance hors assurance : ");
		lblEcheanceHorsAss.setBackground(Color.WHITE);
		panel_3.add(lblEcheanceHorsAss);
		lblEcheanceHorsAss.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblResultatEcheanceHorsAss = new JLabel("");
		lblResultatEcheanceHorsAss.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblResultatEcheanceHorsAss);
		lblResultatEcheanceHorsAss.setBackground(Color.WHITE);
		
		JPanel panel_7 = new JPanel();
		panelCenter.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		panel_7.setBackground(Color.WHITE);
		
		JLabel lblEchanceAssuranceComprise = new JLabel("Echéance assurance comprise : ");
		panel_7.add(lblEchanceAssuranceComprise);
		lblEchanceAssuranceComprise.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEchanceAssuranceComprise.setBackground(Color.WHITE);
		
		JLabel lblResEcheanceAssuranceComprise = new JLabel("");
		lblResEcheanceAssuranceComprise.setHorizontalAlignment(SwingConstants.CENTER);
		panel_7.add(lblResEcheanceAssuranceComprise);
		lblResEcheanceAssuranceComprise.setBackground(Color.WHITE);
		
		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double resCoutTotal=0;
				resCoutTotal+=Double.parseDouble(textFieldMontantTotal.getText()) *(1+(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+(Integer.parseInt(textDureDuPrt.getText())*Double.parseDouble(textFieldEcheanceAssur.getText()))
						+Double.parseDouble(textFieldFraisDeDossier.getText());
				lblResultatCoutTotal.setText(Double.toString(resCoutTotal));
				
				double resEcheanceAssuranceComprise=0;
				resEcheanceAssuranceComprise+=(Double.parseDouble(textFieldMontantTotal.getText()) *(1+(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+(Integer.parseInt(textDureDuPrt.getText())*Double.parseDouble(textFieldEcheanceAssur.getText()))
						+Double.parseDouble(textFieldFraisDeDossier.getText()))/Integer.parseInt(textDureDuPrt.getText());
				lblResEcheanceAssuranceComprise.setText(Double.toString(resEcheanceAssuranceComprise));

				double resultatEcheanceHorsAss =0;
				resultatEcheanceHorsAss+=(Double.parseDouble(textFieldMontantTotal.getText()) *(1+(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+Double.parseDouble(textFieldFraisDeDossier.getText()))/Integer.parseInt(textDureDuPrt.getText());
				lblResultatEcheanceHorsAss.setText(Double.toString(resultatEcheanceHorsAss));
				
				lblResultatCoutTotal.updateUI();
				lblResEcheanceAssuranceComprise.updateUI();
				lblResultatEcheanceHorsAss.updateUI();
			}
		});
		panelCenter.add(btnCalculer);
		panelCenter.setBackground(Color.WHITE);
		JPanel panelSouth = new JPanel();
		add(panelSouth, BorderLayout.SOUTH);
		panelSouth.setBackground(Color.WHITE);
		
		JButton btnEnregistrerLaSimulation = new JButton("Modifier la simulation");
		btnEnregistrerLaSimulation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loan l=new Loan();
				l.setCategory(categories.get(comboBoxCategories.getSelectedIndex()));
				l.setAmount(Integer.parseInt(textFieldMontantTotal.getText()));
				l.setTotalDuration(Integer.parseInt(textDureDuPrt.getText()));
				double resCoutTotal=0;
				/*resCoutTotal+=Double.parseDouble(textFieldMontantTotal.getText())*(1+(2*Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()])/100))
						+(Integer.parseInt(textDureDuPrt.getText())*Double.parseDouble(textFieldEcheanceAssur.getText()))
						+Double.parseDouble(textFieldFraisDeDossier.getText());
				lblResultatCoutTotal.setText(Double.toString(resCoutTotal));
				l.setTotalAmount(resCoutTotal);*/
				resCoutTotal=Double.parseDouble(lblResultatCoutTotal.getText());
				l.setTotalAmount(resCoutTotal);
				Rate r=new Rate();
				r.setDuration(Integer.parseInt(textDureDuPrt.getText()));
				r.setInterestRate(Double.parseDouble(taux[comboBoxTauxDintrtDu.getSelectedIndex()]));
				r.setMonthlyPayment(resCoutTotal/r.getDuration());
				r.setId(loan.getRates().get(0).getId());
				l.addRate(r);
				
				
				User u=new User();
				
				State s=new State();
				s.setLabelState("Simulation");
				s.setUserId(1);
				s.setId(loan.getStates().get(0).getId());
				s.setLoanId(loan.getId());
				
				l.addState(s);

				l.setId(loan.getId());
				l.setClient(LoanFixedRateSimulationPanel.this.client);
			
				LoanController lc=new LoanController();
				lc.updateSimulation(l,client);
				
				
			}
		});

		panelSouth.add(btnEnregistrerLaSimulation);
		
	}
	
	public static void main(String[] args) {
	

		ArrayList<Category> categories = new ArrayList<Category>();
		Category cat1=new Category(1,"Test1");
		categories.add(cat1);
		Category cat2=new Category(1,"Test2");
		categories.add(cat2);
		Category cat3=new Category(1,"Test3");
		categories.add(cat3);
		int id_client=1;
		
		JFrame fenetre = new JFrame();
        
	    //Définit un titre pour notre fenêtre
	    fenetre.setTitle("Test LoanFixedRateSimulation");
	    //Définit sa taille : 400 pixels de large et 100 pixels de haut
	    fenetre.setSize(1000, 1000);
	    //Nous demandons maintenant à notre objet de se positionner au centre
	    fenetre.setLocationRelativeTo(null);
	    Client client=new Client();
	    client.setId(11);
	    //Termine le processus lorsqu'on clique sur la croix rouge
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //LoanFixedRateSimulationPanel test =new LoanFixedRateSimulationPanel(categories,client);
		//test.setVisible(true);
		//fenetre.setContentPane(test);
	    //Et enfin, la rendre visible        
	    fenetre.setVisible(true);
	    
		
	}
	
}

