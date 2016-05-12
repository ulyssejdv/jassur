package com.jassur.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public HomePanel() {
		
		JLabel lblBienvenuSurJassur = new JLabel("Bienvenue sur JASSUR");
		lblBienvenuSurJassur.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		add(lblBienvenuSurJassur);
		
	}
	
	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("conf/meme-home.jpg"));
			//g.drawImage(img, 0, 0, this);
			//Pour une image de fond
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}                
	} 

}
