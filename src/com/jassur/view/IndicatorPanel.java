package com.jassur.view;
import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.jassur.model.Indicator;

public class IndicatorPanel extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public IndicatorPanel(Indicator indicator) {
		
		
		JLabel lblTitle = new JLabel("Indicators");
		System.out.println("Indicators");

		JLabel lblNbPretCon = new JLabel("Nombre de pr\u00EAt(s) contract\u00E9(s): "+Integer.toString(indicator.getNbEnCours()));
		add(lblNbPretCon);
		System.out.println("Nombre de pr\u00EAt(s) contract\u00E9(s): "+indicator.getNbEnCours());

		JLabel lblNbPretSim = new JLabel("Nombre de pr\u00EAt(s) simul\u00E9(s): "+Integer.toString(indicator.getNbSimulation()));
		add(lblNbPretSim);
		System.out.println("Nombre de pr\u00EAt(s) simul\u00E9(s): "+indicator.getNbSimulation());

		JLabel lblDureeMoyPret = new JLabel("Dur\u00E9e moyenne des pr\u00EAts: "+Integer.toString(indicator.getAvgDurationLoan()));
		add(lblDureeMoyPret);
		System.out.println("Dur\u00E9e moyenne des pr\u00EAts: "+indicator.getAvgDurationLoan());

		JLabel lblSumInter = new JLabel("Somme totale des int\u00E9r\u00EAts: "+Integer.toString(indicator.getSumInterest()));
		add(lblSumInter);
		System.out.println("Somme totale des int\u00E9r\u00EAts: "+indicator.getSumInterest());

		JLabel lblAvgAmntLoan = new JLabel("Montant moyen des pr\u00EAts: "+Integer.toString(indicator.getAvgLoansAmount()));
		add(lblAvgAmntLoan);
		System.out.println("Montant moyen des pr\u00EAts: "+indicator.getAvgLoansAmount());

		JLabel lblNbTotalLoan = new JLabel("Nombre total de pr\u00EAts: "+Integer.toString(indicator.getNbLoans()));
		add(lblNbTotalLoan);
		System.out.println("Nombre total de pr\u00EAts: "+indicator.getNbLoans());
	}
}
