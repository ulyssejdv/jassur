package com.jassur.view;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;

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
	private JButton calculateBadButton = new JButton ("Afficer les sc\u00E9narios d\u00E9favorables");
	private JTextField calcSolution = new JTextField(10);
	private final JLabel JLab_Montant = new JLabel("Le Montant");
	private final JLabel JLab_Duree = new JLabel("La Dur\u00E9e");
	private final JLabel JLab_Taux = new JLabel("Le Taux");
	
	public LoanVariableShowCardPanel (){
		// sets up the view and adds the components
		JPanel calcPanel = new JPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//title
		this.setTitle("Simulation de pr\u00EAts - JASSUR");
		//size of the windows
		setBounds(100, 100, 802, 574);		
		calcPanel.setLayout(null);
		unMontant.setBounds(203, 123, 86, 20);
		
		calcPanel.add(unMontant);
		uneDuree.setBounds(203, 164, 86, 20);
		calcPanel.add(uneDuree);
		unTaux.setBounds(203, 199, 86, 20);
		calcPanel.add(unTaux);
		
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		calculateBestButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((JButton)e.getSource()== calculateBestButton)
				{
					new ShowBestScenarios().setVisible(true);
				//	this.setVisible(false);
				}
				
			}
		});
		
		
		//create button for calculate the total
		calculateButton.setBounds(90, 309, 71, 23);
		calcPanel.add(calculateButton);
		calcSolution.setBounds(203, 310, 86, 20);
		calcPanel.add(calcSolution);
		
		// create 2 buttons for best and bad scenarios
		calculateBestButton.setBounds(76,358,193,92);
		calcPanel.add(calculateBestButton);
		
		calculateBadButton.setBounds(311,354,200,92);
		calcPanel.add(calculateBadButton);
		
		getContentPane().add(calcPanel);
		JLab_Montant.setBounds(91, 126, 86, 14);
		
		calcPanel.add(JLab_Montant);
		JLab_Duree.setBounds(91, 167, 46, 14);
		
		calcPanel.add(JLab_Duree);
		JLab_Taux.setBounds(91, 202, 46, 14);
		
		calcPanel.add(JLab_Taux);
		

		
		
	}
	public int getUnMontant(){
		return Integer.parseInt(unMontant.getText());
	}
	
	public int getUneDuree(){
		return Integer.parseInt(uneDuree.getText());
	}
	public int getUnTaux(){
		return Integer.parseInt(unTaux.getText());
	}

	public int getCalcSolution(){
		return Integer.parseInt(calcSolution.getText());
	}
	
	public void setCalcSolution(int solution){
		calcSolution.setText(Integer.toString(solution));
		
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
