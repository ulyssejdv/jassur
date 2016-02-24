package com.jassur.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.jassur.controller.SimulationController;
import com.jassur.model.Simulation;
import com.jassur.observer.Observable;
import com.jassur.observer.Observer;

public class ReadSimPanel extends JPanel implements Observer {
	
	private SimulationController sc;
	
	private Simulation s;
	
	JLabel lblMontant;
	JLabel lblTaux;
	JLabel lblMensualite;
	JLabel lblDuree;
	JLabel lblTotal;

	/**
	 * Create the panel.
	 */
	public ReadSimPanel(SimulationController sc) {
		this.sc = sc;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		
		lblMontant = new JLabel("Montant : ");
		panel.add(lblMontant);
		
		lblTaux = new JLabel("Taux :");
		panel.add(lblTaux);
		
		lblMensualite = new JLabel("Mensualité :");
		panel.add(lblMensualite);
		
		lblDuree = new JLabel("Durée :");
		panel.add(lblDuree);
		
		lblTotal = new JLabel("Total");
		panel.add(lblTotal);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new BackListener());
		panel_1.add(btnBack);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new DeleteListener());
		panel_1.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new UpdateListener());
		panel_1.add(btnModifier);
	}

	@Override
	public void update(Observable obs) {
		if (obs instanceof Simulation) {
			s = (Simulation) obs;
			lblMensualite.setText("Mensualitée : "+s.getMensualite()+"€");
			lblTotal.setText("Total : "+s.getTotal()+"€");
			lblMontant.setText("Montant : "+s.getMontant()+"€");
			lblTaux.setText("Taux : "+s.getTaux()+"%");
			lblDuree.setText("Durée : "+s.getDuree()+" mois");
		}
	}
	
	class DeleteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.deleteAction(s.getId());
		}
	}
	
	class UpdateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.editAction(s);
		}
	}
	
	class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.listAction();
			//lblMessage.setText(res);
			//btnSauvegarder.setEnabled(true);
		}	
	}

}
