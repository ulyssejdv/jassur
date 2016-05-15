package com.jassur.view;

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
		add(tabbedPane);
		
		tabbedPane.add("Paramètres", new LoanShowParamsPanel(this.loan));
		
		tabbedPane.add("Graphiques", new JPanel());
		
		tabbedPane.add("Tableau", new LoanShowTablePanel(this.loan));

	}

}
