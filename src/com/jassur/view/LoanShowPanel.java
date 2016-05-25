package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
		Dimension d = new Dimension(BaseGUI.MAIN_FRAME.getWidth(), BaseGUI.MAIN_FRAME.getHeight());
		tabbedPane.setPreferredSize(d);

		add(tabbedPane);
		
		setBackground(Color.WHITE);
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jp.setBackground(Color.WHITE);
		
		
		
		jp.add(new LoanShowParamsPanel(this.loan), BorderLayout.NORTH);
		//jp.add(new LoanShowChartPanel(this.loan), BorderLayout.CENTER);
		
		JScrollPane jsp = new JScrollPane(jp);
		
		tabbedPane.add("Paramètres", jsp);
		tabbedPane.add("Graphique", new LoanShowChartPanel(this.loan));
		tabbedPane.add("Tableau", new LoanShowTablePanel(this.loan));
	}

}
