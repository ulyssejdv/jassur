package com.jassur.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.print.LoanPdfTemplate;

public class FrameNewPDF extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	
	private Client client;
	private Loan loan;

	/**
	 * Create the frame.
	 */
	public FrameNewPDF(Client c, Loan l) {
		
		this.client = c;
		this.loan = l;
		
		setBounds(100, 100, 494, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblPath = new JLabel("Enregistrer sous :");
		contentPane.add(lblPath);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(25);
		
		JButton btnCrer = new JButton("Enregistrer");
		btnCrer.addActionListener(new CreatePdfListener());
		contentPane.add(btnCrer);
	}
	
	
	class CreatePdfListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			LoanPdfTemplate.createPDF(textField.getText(), FrameNewPDF.this.client, FrameNewPDF.this.loan);
			FrameNewPDF.this.dispose();
		}
	}
}
