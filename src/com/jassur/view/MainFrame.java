package com.jassur.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jassur.controller.SimulationController;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	private SimulationController simulationController;

	/**
	 * Create the frame.
	 */
	public MainFrame(SimulationController sc) {
		
		/* Give the controller who refer to */
		this.simulationController = sc;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.setVisible(true);
	}
	
	public void render(JPanel p) {
		this.setContentPane(p);
		this.setVisible(true);
	}

}
