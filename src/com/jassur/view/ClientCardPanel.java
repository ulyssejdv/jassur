package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jassur.controller.LoanController;
import com.jassur.model.Client;

public class ClientCardPanel extends JPanel {
	
	/**
	 * Create the panel.
	 * @param mainFrame 
	 */
	public ClientCardPanel(Client c) {

		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		JPanel panelInfo = new JPanel();

		GroupLayout layout = new GroupLayout(panelInfo);
		panelInfo.setLayout(layout);
		panelInfo.setBackground(Color.WHITE);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		JLabel lblFirstName = new JLabel("Prenom : ");
		JLabel lblFirstNameIn = new JLabel(c.getFirstName());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblFirstName)
				.addComponent(lblFirstNameIn));

		JLabel lblLastName = new JLabel("Nom : ");
		JLabel lblLastNameIn = new JLabel(c.getLastName());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblLastName)
				.addComponent(lblLastNameIn));

		JLabel lblPhone = new JLabel("Telephone : ");
		JLabel lblPhoneIn = new JLabel(c.getPhone());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblPhone)
				.addComponent(lblPhoneIn));

		JLabel lblEmail = new JLabel("E-mail : ");
		JLabel lblEmailIn = new JLabel(c.getEmail());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblEmail)
				.addComponent(lblEmailIn));

		JLabel lblStreet = new JLabel("Rue : ");
		JLabel lblStreetIn = new JLabel(c.getAddress().getStreet());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblStreet)
				.addComponent(lblStreetIn));

		JLabel lblCity = new JLabel("Ville : ");
		JLabel lblCityIn = new JLabel(c.getAddress().getCity());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblCity)
				.addComponent(lblCityIn));

		JLabel lblRegion = new JLabel("Region : ");
		JLabel lblRegionIn = new JLabel(c.getAddress().getRegion());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblRegion)
				.addComponent(lblRegionIn));

		JLabel lblZip = new JLabel("Code postal : ");
		JLabel lblZipIn = new JLabel(String.valueOf(c.getAddress().getZip()));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblZip)
				.addComponent(lblZipIn));

		JLabel lblCountry = new JLabel("Pays : ");
		JLabel lblCountryIn = new JLabel(c.getAddress().getCountry());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblCountry)
				.addComponent(lblCountryIn));

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lblFirstName)
				.addComponent(lblLastName)
				.addComponent(lblPhone)
				.addComponent(lblEmail)
				.addComponent(lblStreet)
				.addComponent(lblCity)
				.addComponent(lblZip)
				.addComponent(lblRegion)
				.addComponent(lblCountry)
				);
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lblFirstNameIn)
				.addComponent(lblLastNameIn)
				.addComponent(lblPhoneIn)
				.addComponent(lblEmailIn)
				.addComponent(lblStreetIn)
				.addComponent(lblCityIn)
				.addComponent(lblZipIn)
				.addComponent(lblRegionIn)
				.addComponent(lblCountryIn)
				);
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		add(panelInfo, BorderLayout.CENTER);
		
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		
		JButton btnCreateLoan = new JButton("Nouvelle simulation");
		btnCreateLoan.addActionListener(new GotoNewLoanListener(c));
		header.add(btnCreateLoan);
		
		add(header, BorderLayout.NORTH);

	}
	
	class GotoNewLoanListener implements ActionListener {
		private Client client;
		public GotoNewLoanListener(Client c) {
			this.client = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			LoanController lc = new LoanController();
			lc.newAction(this.client);
		}
		
	}

}
