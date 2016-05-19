package com.jassur.view;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.jassur.model.LoanVariable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



import com.jassur.model.LoanVariable;
 //@ author : aurelie

public class LoanVariableRateCardPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextField textField_6;

	private LoanVariable loanVariable;
	
	/**
	 * Create the panel.
	 */
	public LoanVariableRateCardPanel(LoanVariable lV) {
		this.loanVariable = lV;

		
		setBounds(100, 100, 802, 574);

		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		JTextPane txtpnSimulerTauxVariable = new JTextPane();
		txtpnSimulerTauxVariable.setFont(new Font("Tahoma", Font.PLAIN, 21));
		txtpnSimulerTauxVariable.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnSimulerTauxVariable.setBounds(10, 11, 332, 33);
		txtpnSimulerTauxVariable.setText("Simuler taux variable");
		add(txtpnSimulerTauxVariable);
		
		JTextPane txtpnMontantDuPrt = new JTextPane();
		txtpnMontantDuPrt.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnMontantDuPrt.setText("Montant du pr\u00EAt   ");
		txtpnMontantDuPrt.setBounds(39, 81, 82, 30);
		add(txtpnMontantDuPrt);
		
		JTextPane txtpnDureDuPrt = new JTextPane();
		txtpnDureDuPrt.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnDureDuPrt.setText("Dur\u00E9e du pr\u00EAt");
		txtpnDureDuPrt.setBounds(39, 114, 82, 20);
		add(txtpnDureDuPrt);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ann\u00E9e(s)");
		rdbtnNewRadioButton.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnNewRadioButton.setBounds(143, 141, 69, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Mois");
		rdbtnNewRadioButton_1.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnNewRadioButton_1.setBounds(214, 141, 53, 23);
		add(rdbtnNewRadioButton_1);
		
		JTextPane txtpnTauxRvisable = new JTextPane();
		txtpnTauxRvisable.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnTauxRvisable.setText("Taux r\u00E9visable");
		txtpnTauxRvisable.setBounds(39, 171, 82, 20);
		add(txtpnTauxRvisable);
		
		JTextPane txtpnTauxAssurance = new JTextPane();
		txtpnTauxAssurance.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnTauxAssurance.setText("Baisse Maximale");
		txtpnTauxAssurance.setBounds(39, 308, 89, 20);
		add(txtpnTauxAssurance);
		
		textField = new JTextField();
		textField.setBounds(143, 81, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 114, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 171, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnValider = new JButton("Calculer");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(39, 440, 89, 23);
		add(btnValider);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setBounds(372, 440, 89, 23);
		add(btnEffacer);
		
		JButton btnAfficherScnario = new JButton("Afficher Sc\u00E9nario");
		btnAfficherScnario.setBounds(156, 440, 136, 23);
		add(btnAfficherScnario);
		
		JTextPane txtpnMontantTotal = new JTextPane();
		txtpnMontantTotal.setText("Montant Total");
		txtpnMontantTotal.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnMontantTotal.setBounds(39, 353, 82, 20);
		add(txtpnMontantTotal);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(143, 220, 86, 20);
		add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(143, 353, 86, 20);
		add(textField_6);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Taux assurance");
		textPane.setBackground(SystemColor.inactiveCaptionBorder);
		textPane.setBounds(39, 220, 82, 20);
		add(textPane);
		
		JTextPane txtpnHausseMaximale = new JTextPane();
		txtpnHausseMaximale.setText("Hausse Maximale");
		txtpnHausseMaximale.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnHausseMaximale.setBounds(39, 277, 99, 20);
		add(txtpnHausseMaximale);
		
	
	

	}
}
