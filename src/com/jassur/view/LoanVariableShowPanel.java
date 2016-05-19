package com.jassur.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.jassur.model.LoanVariable;
import javax.swing.JTextPane;

public class LoanVariableShowPanel extends JPanel {
	
	private LoanVariable loanVariable;
	
	/**
	 * Create the panel.
	 */
	public LoanVariableShowPanel(LoanVariable l) {
		
		this.loanVariable = l;
		
		JTextPane txtpnLoanvariableshoxpanel = new JTextPane();
		txtpnLoanvariableshoxpanel.setText("LoanVariableshoxpanel");
		add(txtpnLoanvariableshoxpanel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		
		tabbedPane.add("Tableau", new LoanVariableShowTablePanel(this.loanVariable));

	}

}
