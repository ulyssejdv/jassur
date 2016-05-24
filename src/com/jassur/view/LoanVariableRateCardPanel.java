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
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JScrollPane;
 //@ author : aurelie

public class LoanVariableRateCardPanel extends JPanel {
	private JTextField textField;

	private LoanVariable loanVariable;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
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
		txtpnSimulerTauxVariable.setBounds(93, 11, 332, 33);
		txtpnSimulerTauxVariable.setText("Simuler taux variable");
		add(txtpnSimulerTauxVariable);
		
		JTextPane txtpnMontantDuPrt = new JTextPane();
		txtpnMontantDuPrt.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnMontantDuPrt.setText("Montant du pr\u00EAt   ");
		txtpnMontantDuPrt.setBounds(39, 92, 82, 30);
		add(txtpnMontantDuPrt);
		
		JTextPane txtpnDureDuPrt = new JTextPane();
		txtpnDureDuPrt.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnDureDuPrt.setText("Dur\u00E9e du pr\u00EAt");
		txtpnDureDuPrt.setBounds(39, 131, 82, 20);
		add(txtpnDureDuPrt);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Ann\u00E9e(s)");
		rdbtnNewRadioButton.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnNewRadioButton.setBounds(141, 153, 69, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Mois");
		rdbtnNewRadioButton_1.setBackground(SystemColor.inactiveCaptionBorder);
		rdbtnNewRadioButton_1.setBounds(239, 153, 53, 23);
		add(rdbtnNewRadioButton_1);
		
		JTextPane txtpnTauxRvisable = new JTextPane();
		txtpnTauxRvisable.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnTauxRvisable.setText("Taux r\u00E9visable");
		txtpnTauxRvisable.setBounds(39, 180, 82, 20);
		add(txtpnTauxRvisable);
		
		JTextPane txtpnTauxAssurance = new JTextPane();
		txtpnTauxAssurance.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnTauxAssurance.setText("Baisse Maximale");
		txtpnTauxAssurance.setBounds(406, 131, 89, 20);
		add(txtpnTauxAssurance);
		
		textField = new JTextField();
		textField.setBounds(131, 94, 232, 28);
		add(textField);
		textField.setColumns(10);
		
		JButton btnValider = new JButton("Calculer");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnValider.setBounds(49, 311, 89, 23);
		add(btnValider);
		
		JButton btnEffacer = new JButton("Effacer");
		btnEffacer.setBounds(375, 311, 89, 23);
		add(btnEffacer);
		
		JButton btnAfficherScnario = new JButton("Afficher Sc\u00E9nario");
		btnAfficherScnario.setBounds(180, 311, 136, 23);
		add(btnAfficherScnario);
		
		JTextPane txtpnMontantTotal = new JTextPane();
		txtpnMontantTotal.setText("Montant Total");
		txtpnMontantTotal.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnMontantTotal.setBounds(416, 170, 82, 20);
		add(txtpnMontantTotal);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Taux assurance");
		textPane.setBackground(SystemColor.inactiveCaptionBorder);
		textPane.setBounds(39, 210, 82, 20);
		add(textPane);
		
		JTextPane txtpnHausseMaximale = new JTextPane();
		txtpnHausseMaximale.setText("Hausse Maximale");
		txtpnHausseMaximale.setBackground(SystemColor.inactiveCaptionBorder);
		txtpnHausseMaximale.setBounds(406, 92, 99, 20);
		add(txtpnHausseMaximale);
		
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(131, 125, 232, 28);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(131, 178, 232, 28);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(131, 210, 232, 28);
		add(textField_3);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0.5", "1", "1.5", "2"}));
		comboBox.setBounds(515, 92, 105, 22);
		add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"-0.5", "-1", "-1.5", "-2"}));
		comboBox_1.setBounds(515, 129, 105, 22);
		add(comboBox_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(515, 162, 232, 28);
		add(textField_4);

		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		lblNewJgoodiesLabel.setIcon(new ImageIcon("C:\\Users\\Aur\u00E9lie\\workspace\\jassur\\src\\images\\gestion\\fondModule.png"));
		lblNewJgoodiesLabel.setBounds(10, 11, 758, 400);
		add(lblNewJgoodiesLabel);
		
		JLabel label = new JLabel("Retour au menu principal");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		label.setBounds(503, 299, 210, 47);
		add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 355, 741, 143);
		add(scrollPane);
		
		JLabel label_1 = DefaultComponentFactory.getInstance().createLabel("New JGoodies label");
		label_1.setBackground(SystemColor.infoText);
		label_1.setBounds(297, 273, 92, 14);
		add(label_1);
		
		JLabel JLab_Ajouter = DefaultComponentFactory.getInstance().createLabel("Ajouter");
		JLab_Ajouter.setIcon(new ImageIcon("C:\\Users\\Aur\u00E9lie\\workspace\\jassur\\src\\images\\gestion\\ajouter.png"));
		JLab_Ajouter.setBounds(93, 509, 92, 39);
		add(JLab_Ajouter);
		
	
	

	}
}
