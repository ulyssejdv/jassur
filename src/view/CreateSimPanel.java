package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.SimulationController;
import model.Simulation;
import observer.Observable;
import observer.Observer;

public class CreateSimPanel extends JPanel implements Observer{
	
	private SimulationController simulationController;
	private Simulation simulation;
	
	private JTextField tfMontant;
	private JTextField tfTaux;
	private JTextField tfDuree;
	
	/**
	 * Create the panel.
	 */
	public CreateSimPanel(SimulationController sc) {
		this.simulationController = sc;
		this.simulation = new Simulation();
		this.initComponent();
	}
	
	private void initComponent() {
		setLayout(new BorderLayout(0, 0));
				
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		
		JLabel lblMontant = new JLabel("Montant");
		panel.add(lblMontant);
		
		tfMontant = new JTextField();
		tfMontant.setText(String.valueOf(simulation.getMontant()));
		panel.add(tfMontant);
		tfMontant.setColumns(5);
		
		JLabel lblTaux = new JLabel("Taux");
		panel.add(lblTaux);
		
		tfTaux = new JTextField();
		tfTaux.setText(String.valueOf(simulation.getTaux()));
		panel.add(tfTaux);
		tfTaux.setColumns(3);
		
		JLabel lblDuree = new JLabel("Durée");
		panel.add(lblDuree);
		
		tfDuree = new JTextField();
		tfDuree.setText(String.valueOf(simulation.getDuree()));
		panel.add(tfDuree);
		tfDuree.setColumns(3);
		
		JButton btnCalculer = new JButton("Calculer");
		panel.add(btnCalculer);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new BackListener());
		panel_1.add(btnBack);
		btnCalculer.addActionListener(new CalculeListener());
	}
	
	
	@Override
	public void update(Observable obs) {
		if (obs instanceof Simulation) {
			this.simulation = (Simulation) obs;
			this.initComponent();
		}
	}
	
	
	class CalculeListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String res = simulationController.calculAction(tfMontant.getText(), tfTaux.getText(), tfDuree.getText());
		}	
	}
	
	class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			simulationController.indexAction();
		}	
	}

	
}
