package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.jassur.model.Loan;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class LoanShowPanel extends JPanel {
	
	private Loan loan;
	
	/**
	 * Create the panel.
	 */
	public LoanShowPanel(Loan l) {
		
		setLayout(new BorderLayout());
		
		this.loan = l;
		
		JButton btnPrint = new JButton("Imprimer");
		JPanel jpb = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpb.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		jpb.setBackground(Color.WHITE);
		jpb.add(btnPrint);
		add(jpb, BorderLayout.NORTH);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		Dimension d = new Dimension(BaseGUI.MAIN_FRAME.getWidth(), BaseGUI.MAIN_FRAME.getHeight());
		tabbedPane.setPreferredSize(d);
		
		add(tabbedPane, BorderLayout.CENTER);
		
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
