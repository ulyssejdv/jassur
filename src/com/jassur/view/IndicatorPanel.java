package com.jassur.view;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.Container;
import javax.swing.SwingConstants;

import com.jassur.model.Indicator;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IndicatorPanel extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public IndicatorPanel(Indicator indicator) {
		setLayout(null);
		JPanel container = new JPanel();
		container.setVisible(true);
		
		
		JLabel lblTitle = new JLabel("Indicators");
		lblTitle.setBounds(0, 0, 426, 46);
		lblTitle.setVisible(true);
		System.out.println("Indicators");

		JLabel lblNbPretCon = new JLabel("Nombre de pr\u00EAt(s) contract\u00E9(s): "+Integer.toString(indicator.getNbEnCours()));
		lblNbPretCon.setHorizontalAlignment(SwingConstants.CENTER);
		lblNbPretCon.setBounds(180, 70, 61, 16);
		add(lblNbPretCon);
		System.out.println("Nombre de pr\u00EAt(s) contract\u00E9(s): "+indicator.getNbEnCours());

		JLabel lblNbPretSim = new JLabel("Nombre de pr\u00EAt(s) simul\u00E9(s): "+Integer.toString(indicator.getNbSimulation()));
		lblNbPretSim.setBounds(180, 100, 61, 16);
		add(lblNbPretSim);
		System.out.println("Nombre de pr\u00EAt(s) simul\u00E9(s): "+indicator.getNbSimulation());

		JLabel lblDureeMoyPret = new JLabel("Dur\u00E9e moyenne des pr\u00EAts: "+Integer.toString(indicator.getAvgDurationLoan()));
		lblDureeMoyPret.setBounds(180, 131, 61, 16);
		add(lblDureeMoyPret);
		System.out.println("Dur\u00E9e moyenne des pr\u00EAts: "+indicator.getAvgDurationLoan());

		JLabel lblSumInter = new JLabel("Somme totale des int\u00E9r\u00EAts: "+Integer.toString(indicator.getSumInterest()));
		lblSumInter.setBounds(180, 162, 61, 16);
		add(lblSumInter);
		System.out.println("Somme totale des int\u00E9r\u00EAts: "+indicator.getSumInterest());

		JLabel lblAvgAmntLoan = new JLabel("Montant moyen des pr\u00EAts: "+Integer.toString(indicator.getAvgLoansAmount()));
		lblAvgAmntLoan.setBounds(180, 193, 61, 16);
		add(lblAvgAmntLoan);
		System.out.println("Montant moyen des pr\u00EAts: "+indicator.getAvgLoansAmount());

		JLabel lblNbTotalLoan = new JLabel("Nombre total de pr\u00EAts: "+Integer.toString(indicator.getNbLoans()));
		lblNbTotalLoan.setBounds(180, 224, 61, 16);
		add(lblNbTotalLoan);
		System.out.println("Nombre total de pr\u00EAts: "+indicator.getNbLoans());

		setVisible(true);
	}
}
