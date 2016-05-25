package com.jassur.view;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.CardLayout;

import java.awt.Dimension;


// the interface. What the user sees

public class LoanVariableShowCardPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField unMontant = new JTextField(10);
	private JTextField uneDuree = new JTextField(10);
	private JTextField unTaux = new JTextField(10);
	private JButton calculateButton = new JButton("Calculer");
	//Button for best scenarios
	private JButton calculateBestButton = new JButton ("Afficher les meilleurs sc\u00E9narios");
	private JButton calculateBadButton = new JButton ("Afficer les s\u00E9cnarios d\u00E9favorables");
	private JTextField calcSolution = new JTextField(10);
	private final JLabel JLab_Montant = new JLabel("Le Montant");
	private final JLabel JLab_Duree = new JLabel("La Dur\u00E9e");
	private final JLabel JLab_Taux = new JLabel("Le Taux");
	private final JTextPane textPane = new JTextPane();
	private JTable table;
	private JTable table_1;



	
	public LoanVariableShowCardPanel (){
		// sets up the view and adds the components
		JPanel calcPanel = new JPanel();
	    String[] title = new String[]{"Annee","Montant", "Taux", "Mensuel"};
	    String[] title2 = new String[]{"Annee","Montant", "Taux", "Mensuel"};
	    Object[][] data = {
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	    
	    	    };
	    Object[][] data2 = {
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	      {"Cysboy", "28222 ans", "1.80 m","102220"},
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	      {"Cysboy", "28 ans", "1.80 m","100"},
	    	    
	    	    };
	    
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//title
		this.setTitle("Simulation de pr\u00EAts  - JASSUR");
		//size of the windows
		setBounds(100, 100, 802, 574);	
		
		calcPanel.setLayout(null);
		unMontant.setBounds(276, 74, 86, 20);
		
		calcPanel.add(unMontant);
		uneDuree.setBounds(276, 95, 86, 20);
		calcPanel.add(uneDuree);
		unTaux.setBounds(276, 117, 86, 20);
		calcPanel.add(unTaux);
		
		
	
		// Calculate constant scenario per month
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		// Calculate best scenario
/*		calculateBestButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((JButton)e.getSource()== calculateBestButton)
				{
					new ShowBestScenarios().setVisible(true);
				//	this.setVisible(false);
				}
				
			}
		});
		*/
		 
		//create button for calculate the total
		calculateButton.setBounds(162, 146, 104, 23);
		calcPanel.add(calculateButton);
		calcSolution.setBounds(276, 147, 86, 20);
		calcPanel.add(calcSolution);
		
		
		// create 2 buttons for best and bad scenarios
		calculateBestButton.setBounds(115,180,193,52);
		calcPanel.add(calculateBestButton);
		
		calculateBadButton.setBounds(327,180,200,52);
		calcPanel.add(calculateBadButton);
		
		getContentPane().add(calcPanel);
		JLab_Montant.setBounds(200, 77, 66, 14);
		
		calcPanel.add(JLab_Montant);
		JLab_Duree.setBounds(210, 102, 56, 14);
		
		calcPanel.add(JLab_Duree);
		JLab_Taux.setBounds(220, 120, 46, 14);
		
		calcPanel.add(JLab_Taux);
		textPane.setText("Simuler taux variable");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(50, 11, 332, 33);
		
		calcPanel.add(textPane);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(66, 256, 664, 246);
		calcPanel.add(panelCentral);
		CardLayout cl=new CardLayout(0, 0);
		panelCentral.setLayout(cl);
		
		//create panel best scenario
		JPanel BestScenario = new JPanel();
		BestScenario.setBackground(SystemColor.menu);
		panelCentral.add(BestScenario, "name_626830455542738");
		table = new JTable(data, title);
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setMaximumSize(new Dimension(660, 250));
	    scrollPane.setMinimumSize(new Dimension(660, 250));
	    scrollPane.setPreferredSize(new Dimension(660, 250));
	    BestScenario.add(scrollPane);
		

		
		//create panel worst scenario
		JPanel WorstScenario = new JPanel();
		WorstScenario.setBackground(SystemColor.menu);
		panelCentral.add(WorstScenario, "name_626834466493671");
		table_1 = new JTable(data2, title2);
	    JScrollPane scrollPane_1 = new JScrollPane(table_1);
	    scrollPane_1.setPreferredSize(new Dimension(650, 250));
	    scrollPane_1.setMaximumSize(new Dimension(660, 250));
	    scrollPane_1.setMinimumSize(new Dimension(660, 250));
	    scrollPane_1.setPreferredSize(new Dimension(660, 250));
		WorstScenario.add(scrollPane_1);
		
		JLabel label = new JLabel(" Montant mensuel");
		label.setBounds(372, 150, 116, 14);
		calcPanel.add(label);
		
		
		// link between button and panel Best Scenario
		calculateBestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCentral,"name_626830455542738");
			}
		});
		// link between button and panel worstScenario
		calculateBadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(panelCentral,"name_626834466493671");
			}
		});
	
	}
	public double getUnMontant(){
		return Double.parseDouble(unMontant.getText());
	}
	
	public double getUneDuree(){
		return Double.parseDouble(uneDuree.getText());
	}
	public double getUnTaux(){
		return Double.parseDouble(unTaux.getText());
	}

	public double getCalcSolution(){
		return Double.parseDouble(calcSolution.getText());
	}
	
	public void setCalcSolution(double solution){
		calcSolution.setText(Double.toString(solution));
		
	}
	public void addCalculationListener(ActionListener listenerForCalcButton){
		
		calculateButton.addActionListener(listenerForCalcButton);
	}
	public void addCalculationBestListener(ActionListener listenerForCalcBestButton){
		
		calculateButton.addActionListener(listenerForCalcBestButton);
	}
	
	//if the calculateButton is clicked execute a method in the controller named actionPerformed

	
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
}
