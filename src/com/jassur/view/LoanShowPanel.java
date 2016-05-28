package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.print.LoanPdfTemplate;

public class LoanShowPanel extends JPanel {
	
	private Loan loan;
	private Client client;
	
	/**
	 * Create the panel.
	 */
	public LoanShowPanel(Loan l, Client c) {
		
		setLayout(new BorderLayout());
		
		this.loan = l;
		this.client = c;
		
		JButton btnPrint = new JButton("Imprimer");
		btnPrint.addActionListener(new PrintLoanListener(l, c));
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
	
	class PrintLoanListener implements ActionListener {
		private Client client;
		private Loan loan;
		
		public PrintLoanListener(Loan l, Client c) {
			this.loan = l;
			this.client = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(client.toHTML());
			System.out.println(loan.toHTML());
			
			FrameNewPDF fnp = new FrameNewPDF(this.client, this.loan);
			fnp.setVisible(true);
		}
		
	}

}
