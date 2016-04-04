package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

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
		setBorder(new TitledBorder(null, "Client Form", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelPersonnal = new JPanel();
		panelPersonnal.setBorder(new TitledBorder(null, "Personnal information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelPersonnal);
		panelPersonnal.setLayout(new BoxLayout(panelPersonnal, BoxLayout.Y_AXIS));
		
		/*JPanel panelFirstName = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFirstName.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPersonnal.add(panelFirstName);*/
		
		JLabel lblFirstName = new JLabel("First Name :");
		panelPersonnal.add(lblFirstName);
		
		tfFirstName = new JTextField(c.getFirstName());
		panelPersonnal.add(tfFirstName);
		tfFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name :");
		panelPersonnal.add(lblLastName);
		
		tfLastName = new JTextField(c.getLastName());
		panelPersonnal.add(tfLastName);
		tfLastName.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone : ");
		panelPersonnal.add(lblPhone);
		
		tfPhone = new JTextField(c.getPhone());
		panelPersonnal.add(tfPhone);
		tfPhone.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail :");
		panelPersonnal.add(lblEmail);
		
		tfEmail = new JTextField(c.getEmail());
		panelPersonnal.add(tfEmail);
		tfEmail.setColumns(10);
		
		JPanel panelAddress = new JPanel();
		panelAddress.setBorder(new TitledBorder(null, "Address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panelAddress);
		panelAddress.setLayout(new BoxLayout(panelAddress, BoxLayout.Y_AXIS));
		
		JLabel lblStreet = new JLabel("Street :");
		panelAddress.add(lblStreet);
		
		tfStreet = new JTextField(c.getAddress().getStreet());
		panelAddress.add(tfStreet);
		tfStreet.setColumns(10);
		
		JLabel lblCity = new JLabel("City :");
		panelAddress.add(lblCity);
		
		tfCity = new JTextField(c.getAddress().getCity());
		panelAddress.add(tfCity);
		tfCity.setColumns(10);
		
		JLabel lblRegion = new JLabel("Region :");
		panelAddress.add(lblRegion);
		
		tfRegion = new JTextField(c.getAddress().getRegion());
		panelAddress.add(tfRegion);
		tfRegion.setColumns(10);
		
		JLabel lblZip = new JLabel("Zip :");
		panelAddress.add(lblZip);
		
		tfZip = new JTextField(String.valueOf(c.getAddress().getZip()));
		panelAddress.add(tfZip);
		tfZip.setColumns(10);
		
		JLabel lblCountry = new JLabel("Country :");
		panelAddress.add(lblCountry);
		
		tfCountry = new JTextField(c.getAddress().getCountry());
		panelAddress.add(tfCountry);
		tfCountry.setColumns(10);
		
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		add(panel_2, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Save");
		btnAdd.addActionListener(new AddListener());
		panel_2.add(btnAdd);

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
			
			if (c.getId() != 0) {
				cc.update(c2);
			} else {
				cc.create(c2);
			}
			
			c2.setId(c.getId());
			if (c.getId() != 0) {
				c2.setId(c.getId());
				cc.update(c2);
			} else {
				cc.create(c2);
			}
		}
		
	}

}
