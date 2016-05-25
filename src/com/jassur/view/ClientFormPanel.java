package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jassur.controller.ClientController;
import com.jassur.model.Address;
import com.jassur.model.Client;

public class ClientFormPanel extends JPanel {
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfPhone;
	private JTextField tfEmail;
	private JTextField tfStreet;
	private JTextField tfCountry;
	private JTextField tfCity;
	private JTextField tfRegion;
	private JTextField tfZip;

	private Client c;

	/**
	 * Create the panel.
	 * @param c 
	 */
	public ClientFormPanel(Client c) {

		this.c = c;

		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		JPanel infoPanel = new JPanel();
		
		GroupLayout layout = new GroupLayout(infoPanel);
		infoPanel.setLayout(layout);
		infoPanel.setBackground(Color.WHITE);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		JLabel lblFirstName = new JLabel("Prenom :");
		tfFirstName = new JTextField(c.getFirstName());
		tfFirstName.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblFirstName).addComponent(tfFirstName));

		JLabel lblLastName = new JLabel("Nom :");
		tfLastName = new JTextField(c.getLastName());
		tfLastName.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblLastName).addComponent(tfLastName));

		JLabel lblPhone = new JLabel("Telephone : ");
		tfPhone = new JTextField(c.getPhone());
		tfPhone.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblPhone).addComponent(tfPhone));

		JLabel lblEmail = new JLabel("E-mail :");
		tfEmail = new JTextField(c.getEmail());
		tfEmail.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblEmail).addComponent(tfEmail));

		JLabel lblStreet = new JLabel("Rue :");
		tfStreet = new JTextField(c.getAddress().getStreet());
		tfStreet.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblStreet).addComponent(tfStreet));

		JLabel lblCity = new JLabel("Ville :");
		tfCity = new JTextField(c.getAddress().getCity());
		tfCity.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblCity).addComponent(tfCity));

		JLabel lblRegion = new JLabel("Region :");
		tfRegion = new JTextField(c.getAddress().getRegion());
		tfRegion.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblRegion).addComponent(tfRegion));

		JLabel lblZip = new JLabel("Code postal :");
		tfZip = new JTextField(String.valueOf(c.getAddress().getZip()));
		tfZip.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblZip).addComponent(tfZip));

		JLabel lblCountry = new JLabel("Pays :");
		tfCountry = new JTextField(c.getAddress().getCountry());
		tfCountry.setColumns(10);
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblCountry).addComponent(tfCountry));
		
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
				 .addComponent(tfFirstName)
				 .addComponent(tfLastName)
				 .addComponent(tfPhone)
				 .addComponent(tfEmail)
				 .addComponent(tfStreet)
				 .addComponent(tfCity)
				 .addComponent(tfZip)
				 .addComponent(tfRegion)
				 .addComponent(tfCountry)
		);
	   	layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);

		JButton btnAdd = new JButton("Enregistrer");
		btnAdd.addActionListener(new AddListener());
		//infoPanel.add(btnAdd, BorderLayout.SOUTH);

		//JScrollPane jsp = new JScrollPane(infoPanel);
		add(infoPanel, BorderLayout.CENTER);
		add(btnAdd, BorderLayout.SOUTH);

	}

	class AddListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ClientController cc = new ClientController();

			Client c2 = new Client();
			c2.setFirstName(tfFirstName.getText());
			c2.setLastName(tfLastName.getText());
			c2.setEmail(tfEmail.getText());
			c2.setPhone(tfPhone.getText());

			Address a = new Address();
			a.setCity(tfCity.getText());
			a.setCountry(tfCountry.getText());
			a.setRegion(tfRegion.getText());
			a.setStreet(tfStreet.getText());
			a.setZip(Integer.parseInt(tfZip.getText()));

			c2.setAddress(a);

			c2.setId(c.getId());
			if (c.getId() != 0) {
				c2.setId(c.getId());
				cc.updateAction(c2);
			} else {
				cc.createAction(c2);
			}
		}

	}
}
