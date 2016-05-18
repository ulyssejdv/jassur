package com.jassur.view;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SimulateCardPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public SimulateCardPanel() {
		setBounds(100, 100, 802, 574);

		setBackground(SystemColor.inactiveCaptionBorder);
		setLayout(null);
		
		JTextPane txtpnSimulerTauxVariable = new JTextPane();
		txtpnSimulerTauxVariable.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
		txtpnTauxAssurance.setText("Taux assurance");
		txtpnTauxAssurance.setBounds(39, 220, 82, 20);
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(143, 220, 86, 20);
		add(textField_3);
		
		JButton btnValider = new JButton("Calculer");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(143, 342, 89, 23);
		add(btnValider);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setBounds(280, 342, 89, 23);
		add(btnEffacer);

	}
}
