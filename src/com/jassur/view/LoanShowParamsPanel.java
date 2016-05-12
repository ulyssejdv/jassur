package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jassur.model.Loan;
import com.jassur.model.Rate;

public class LoanShowParamsPanel extends JPanel {
	
	private Loan loan;
	
	
	private JTextField textFieldAmount;
	private JTextField textFieldDuration;
	private JTextField textFieldMonthWithA;
	private JTextField textFieldMonthA;
	private JTextField textFieldRate;
	private JTextField textFieldFolderCost;
	private JTextField textFieldTotalAmount;
	private JTextField textFieldMonthWithoutA;
	
	
	/**
	 * Create the panel.
	 */
	public LoanShowParamsPanel(Loan l) {
		this.loan = l;
		
		
		/* Rate */
		Rate rate = new Rate();
		rate.setDuration(0);
		rate.setInterestRate(5);
		
		for (Rate r : this.loan.getRates()) {
			rate = r;
		}
		
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		
		this.setLayout(new GridLayout(0, 4, 0, 0));
		JLabel lblMontantTotalDu = new JLabel("Montant total du prêt :");
		this.add(lblMontantTotalDu);
		lblMontantTotalDu.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
		textFieldAmount = new JTextField(String.valueOf(this.loan.getAmount()));
		textFieldAmount.setEditable(false);
		this.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		JLabel lblDureDuPrt = new JLabel("Durée du prêt :");
		this.add(lblDureDuPrt);
		
		textFieldDuration = new JTextField(String.valueOf(this.loan.getTotalDuration()));
		textFieldDuration.setEditable(false);
		this.add(textFieldDuration);
		textFieldDuration.setColumns(10);
		JLabel lblEchanceAssuranceComprise = new JLabel("Echéance assurance comprise :");
		this.add(lblEchanceAssuranceComprise);
		
		textFieldMonthWithA = new JTextField();
		textFieldMonthWithA.setEditable(false);
		this.add(textFieldMonthWithA);
		textFieldMonthWithA.setColumns(10);
		JLabel lblEchanceDeLassurance = new JLabel("Echéance de l'assurance :");
		this.add(lblEchanceDeLassurance);
		
		textFieldMonthA = new JTextField();
		textFieldMonthA.setEditable(false);
		this.add(textFieldMonthA);
		textFieldMonthA.setColumns(10);
		JLabel lblTauxDintrtDu = new JLabel("Taux d'intérêt du prêt :");
		this.add(lblTauxDintrtDu);
		
		
		textFieldRate = new JTextField(String.valueOf(rate.getInterestRate()));
		textFieldRate.setEditable(false);
		this.add(textFieldRate);
		textFieldRate.setColumns(10);
		JLabel lblFraisDeDossier = new JLabel("Frais de dossier :");
		this.add(lblFraisDeDossier);
		
		textFieldFolderCost = new JTextField();
		textFieldFolderCost.setEditable(false);
		this.add(textFieldFolderCost);
		textFieldFolderCost.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Coût total du prêt :");
		this.add(lblNewLabel_1);
		
		textFieldTotalAmount = new JTextField(String.valueOf(this.loan.getTotalAmount()));
		textFieldTotalAmount.setEditable(false);
		this.add(textFieldTotalAmount);
		textFieldTotalAmount.setColumns(10);
		JLabel lblNewLabel = new JLabel("Echéance hors assurance :");
		this.add(lblNewLabel);
		
		textFieldMonthWithoutA = new JTextField(String.valueOf(rate.getMonthlyPayment()));
		textFieldMonthWithoutA.setEditable(false);
		this.add(textFieldMonthWithoutA);
		textFieldMonthWithoutA.setColumns(10);
		
	}

}
