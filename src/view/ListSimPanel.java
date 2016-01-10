package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.SimulationController;
import model.Simulation;
import model.SimulationList;
import observer.Observable;
import observer.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListSimPanel extends JPanel implements Observer {
	
	private SimulationController sc;
	
	private JTable table;
	private JTextField tdIdSim;
	
	/**
	 * Create the panel.
	 */
	public ListSimPanel(SimulationController sc) {
		this.sc = sc;
		
		//Les données du tableau
	    Object[][] data = {};
	    
	    //Les titres des colonnes
	    String  title[] = {"Id", "Montant", "Taux", "Durée", "Mensualité", "Total"};
	    setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);
		
		JButton btnBack = new JButton("back");
		btnBack.addActionListener(new BackListener());
		panel.add(btnBack);
		
		JLabel lblId = new JLabel("Id :");
		panel.add(lblId);
		
		tdIdSim = new JTextField();
		panel.add(tdIdSim);
		tdIdSim.setColumns(10);
		
		JButton btnVoir = new JButton("voir");
		btnVoir.addActionListener(new ShowListener());
		panel.add(btnVoir);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.NORTH);
		
		table = new JTable(new DefaultTableModel(data, title));
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);
	}

	@Override
	public void update(Observable obs) {
		
		Object[] obj;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		 if (obs instanceof SimulationList) {
			
			SimulationList sl = (SimulationList) obs;
			
			while(model.getRowCount() > 0) {
				model.removeRow(0);
			}
			
			for (Simulation s: sl.getListSimulation()) {
				obj = new Object[]{s.getId(), s.getMontant(), s.getTaux(), s.getDuree(), s.getMensualite(), s.getTotal()};
			    model.addRow(obj);
			}
		}	
	}
	
	class BackListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.indexAction();
			//lblMessage.setText(res);
			//btnSauvegarder.setEnabled(true);
		}	
	}
	
	class ShowListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			sc.readAction(Integer.parseInt(tdIdSim.getText()));
		}	
	}

}
