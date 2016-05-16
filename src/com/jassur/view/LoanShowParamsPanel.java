package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.jassur.model.Loan;
import com.jassur.model.Rate;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

public class LoanShowParamsPanel extends JPanel {
	
	private Loan loan;
	
	
	private JLabel textFieldAmount;
	private JLabel textFieldDuration;
	private JLabel textFieldMonthWithA;
	private JLabel textFieldMonthA;
	private JLabel textFieldRate;
	private JLabel textFieldFolderCost;
	private JLabel textFieldTotalAmount;
	private JLabel textFieldMonthWithoutA;
	
	
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
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelLeft = new JPanel();
		add(panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
						
						JPanel panel_4 = new JPanel();
						panelLeft.add(panel_4);
						JLabel lblMontantTotalDu = new JLabel("Montant total du prêt : ");
						panel_4.add(lblMontantTotalDu);
						lblMontantTotalDu.setHorizontalAlignment(SwingConstants.RIGHT);
						lblMontantTotalDu.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
						textFieldAmount = new JLabel(String.valueOf(this.loan.getAmount()));
						panel_4.add(textFieldAmount);
						
						JPanel panel_5 = new JPanel();
						panelLeft.add(panel_5);
						
						JLabel lblDureDuPrt = new JLabel("Durée du prêt : ");
						panel_5.add(lblDureDuPrt);
						lblDureDuPrt.setHorizontalAlignment(SwingConstants.RIGHT);
						
						textFieldDuration = new JLabel(String.valueOf(this.loan.getTotalDuration()));
						panel_5.add(textFieldDuration);
						
						JPanel panel_6 = new JPanel();
						panelLeft.add(panel_6);
						
								JLabel lblEchanceAssuranceComprise = new JLabel("Echéance assurance comprise : ");
								panel_6.add(lblEchanceAssuranceComprise);
								lblEchanceAssuranceComprise.setHorizontalAlignment(SwingConstants.RIGHT);
								
								textFieldMonthWithA = new JLabel();
								panel_6.add(textFieldMonthWithA);
								
								JPanel panel_7 = new JPanel();
								panelLeft.add(panel_7);
								
										JLabel lblEchanceDeLassurance = new JLabel("Echéance de l'assurance : ");
										panel_7.add(lblEchanceDeLassurance);
										lblEchanceDeLassurance.setHorizontalAlignment(SwingConstants.RIGHT);
										
										textFieldMonthA = new JLabel();
										panel_7.add(textFieldMonthA);
		
		JPanel panelRight = new JPanel();
		add(panelRight);
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
																
																JPanel panel = new JPanel();
																panelRight.add(panel);
																
																		JLabel lblTauxDintrtDu = new JLabel("Taux d'intérêt du prêt : ");
																		panel.add(lblTauxDintrtDu);
																		lblTauxDintrtDu.setHorizontalAlignment(SwingConstants.RIGHT);
																		
																		
																		textFieldRate = new JLabel(String.valueOf(rate.getInterestRate()));
																		panel.add(textFieldRate);
																		
																		JPanel panel_1 = new JPanel();
																		panelRight.add(panel_1);
																		
																				JLabel lblFraisDeDossier = new JLabel("Frais de dossier : ");
																				panel_1.add(lblFraisDeDossier);
																				lblFraisDeDossier.setHorizontalAlignment(SwingConstants.RIGHT);
																				
																				textFieldFolderCost = new JLabel();
																				panel_1.add(textFieldFolderCost);
																				
																				JPanel panel_2 = new JPanel();
																				panelRight.add(panel_2);
																				
																						JLabel lblNewLabel_1 = new JLabel("Coût total du prêt : ");
																						panel_2.add(lblNewLabel_1);
																						lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
																						
																						textFieldTotalAmount = new JLabel(String.valueOf(this.loan.getTotalAmount()));
																						panel_2.add(textFieldTotalAmount);
																						
																						JPanel panel_3 = new JPanel();
																						panelRight.add(panel_3);
																						
																								JLabel lblNewLabel = new JLabel("Echéance hors assurance : ");
																								panel_3.add(lblNewLabel);
																								lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
																								
																								textFieldMonthWithoutA = new JLabel(String.valueOf(rate.getMonthlyPayment()));
																								panel_3.add(textFieldMonthWithoutA);
		
	}

}
