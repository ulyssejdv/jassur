package com.jassur.view;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jassur.model.Client;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public class LoanCardPanel extends JPanel {
	
	/**
	 * Create the panel.
	 * @param mainFrame 
	 */
	public LoanCardPanel(Client c) {
		setBorder(new TitledBorder(null, "Client Card", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	}

}
