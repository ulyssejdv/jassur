package com.jassur.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		

		this.setBackground(Color.WHITE);
		
		JLabel lblBienvenuSurJassur = new JLabel("Bienvenue sur JASSUR");
		lblBienvenuSurJassur.setFont(new Font("Lucida Grande", Font.BOLD, 22));
		add(lblBienvenuSurJassur);
		
		JLabel lblSim = new JLabel("simulateur de pret");
		lblSim.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		add(lblSim);
		
	}
	
	/*public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("conf/meme-home.jpg"));
			//g.drawImage(img, 0, 0, this);
			//Pour une image de fond
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}                
	}*/

}
