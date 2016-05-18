package com.jassur.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jassur.model.Loan;

public class LoanShowPanel extends JPanel {
	
	private Loan loan;
	
	/**
	 * Create the panel.
	 */
	public LoanShowPanel(Loan l) {
		
		this.loan = l;
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		Dimension d = new Dimension();
		d.setSize(1024, 600);
		tabbedPane.setPreferredSize(d);
		add(tabbedPane);
		
		tabbedPane.add("Paramètres", new LoanShowParamsPanel(this.loan));
		
		tabbedPane.add("Graphiques", new LoanShowChartPanel(this.loan));
		
		tabbedPane.add("Tableau", new LoanShowTablePanel(this.loan));
	}

}
