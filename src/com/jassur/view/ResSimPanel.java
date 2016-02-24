package com.jassur.view;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jassur.controller.SimulationController;
import com.jassur.model.Simulation;
import com.jassur.observer.Observable;
import com.jassur.observer.Observer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ResSimPanel extends JPanel implements Observer {
	
	private SimulationController sc;
	
	private Simulation s;
	
	private JLabel lblTotal;
	private JLabel lblMensualite;
	private JLabel lblMessage;
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnBack;
	private JButton btnSave;
	private JButton btnUpdate;
	
	/**
	 * Create the panel.
	 */
	public ResSimPanel(SimulationController sc) {
		
		this.sc = sc;
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		lblTotal = new JLabel("Total : 0.0€");
		panel.add(lblTotal);
		
		lblMensualite = new JLabel("Mensualité : 0.0€");
		panel.add(lblMensualite);
		
		lblMessage = new JLabel("");
		panel.add(lblMessage);
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new CreateListener());
		panel_1.add(btnBack);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveListener());
		panel_1.add(btnSave);
	
	}

	@Override
	public void update(Observable obs) {
		if (obs instanceof Simulation) {
			s = (Simulation) obs;
			lblMensualite.setText("Mensualitée : "+s.getMensualite()+"€");
			lblTotal.setText("Total : "+s.getTotal()+"€");
		}
	}
	
	class CreateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.createAction();
		}
	}
	
	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			sc.updateAction(s);
		}
	}
	
	class SaveListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int idSimulation = 1;
			sc.saveAction(s);
		}
	}
}
