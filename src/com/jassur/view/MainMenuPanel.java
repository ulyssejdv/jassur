package com.jassur.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jassur.controller.SimulationController;

public class MainMenuPanel extends JPanel {
	
	private SimulationController sc;
	
	/**
	 * Create the panel.
	 */
	public MainMenuPanel(SimulationController sc) {
		
		this.sc = sc;
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewSim = new JButton("Nouvelle simulation");
		btnNewSim.addActionListener(new CreateListener());
		add(btnNewSim);
		
		JButton btnShowAll = new JButton("Voir les simulations");
		btnShowAll.addActionListener(new ListListener());
		add(btnShowAll);
		
		JButton btnPing = new JButton("ping");
		btnPing.addActionListener(new PingListener());
		add(btnPing);

	}
	
	class CreateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.createAction();
		}
	}
	
	class ListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.listAction();
		}
	}
	
	class PingListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sc.pingAction();
		}
	}

}
