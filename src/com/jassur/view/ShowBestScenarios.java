package com.jassur.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class ShowBestScenarios extends JPanel {

	public ShowBestScenarios(){
	JPanel calcBest = new JPanel();
	//size of the windows
	setBounds(100, 100, 802, 574);		
	setLayout(null);
	
	JLabel lblTest = new JLabel("test");
	lblTest.setBounds(140, 284, 46, 14);
	add(lblTest);

	}
}
