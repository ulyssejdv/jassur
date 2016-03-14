package com.jassur.view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jassur.model.Client;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class ClientCardPanel extends JPanel {
	
	/**
	 * Create the panel.
	 * @param mainFrame 
	 */
	public ClientCardPanel(Client c) {
		setBorder(new TitledBorder(null, "Client Card", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(null, "Personnal Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelInfo);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		
		JLabel lblFirstName = new JLabel("First Name : "+c.getFirstName());
		panelInfo.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name : "+c.getLastName());
		panelInfo.add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone : "+c.getPhone());
		panelInfo.add(lblPhone);
		
		JLabel lblEmail = new JLabel("E-mail : "+c.getEmail());
		panelInfo.add(lblEmail);
		
		JPanel panelAddress = new JPanel();
		panelAddress.setBorder(new TitledBorder(null, "Address", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panelAddress);
		panelAddress.setLayout(new BoxLayout(panelAddress, BoxLayout.Y_AXIS));
		
		JLabel lblStreet = new JLabel("Street : "+c.getAddress().getStreet());
		panelAddress.add(lblStreet);
		
		JLabel lblCity = new JLabel("City : "+c.getAddress().getCity());
		panelAddress.add(lblCity);
		
		JLabel lblRegion = new JLabel("Region : "+c.getAddress().getRegion());
		panelAddress.add(lblRegion);
		
		JLabel lblZip = new JLabel("Zip : "+c.getAddress().getZip());
		panelAddress.add(lblZip);
		
		JLabel lblCountry = new JLabel("Country : "+c.getAddress().getCountry());
		panelAddress.add(lblCountry);
		
		
	}

}
