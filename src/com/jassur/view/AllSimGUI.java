package com.jassur.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jassur.controller.SimulationController;
import com.jassur.model.Simulation;
import com.jassur.model.SimulationList;
import com.jassur.observer.Observable;
import com.jassur.observer.Observer;

@SuppressWarnings("serial")
public class AllSimGUI extends JFrame implements Observer {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Create the frame.
	 */
	public AllSimGUI(SimulationController sc) {
		setBounds(100, 100, 336, 368);
		initContent();
		setVisible(true);
	}
	
	private void initContent() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		//Les données du tableau
	    Object[][] data = {};
	    
	    //Les titres des colonnes
	    String  title[] = {"Id", "Montant", "Taux", "Durée", "Mensualité", "Total"};
	    
	    table = new JTable(new DefaultTableModel(data, title));
		
		panel.add(new JScrollPane(table), BorderLayout.CENTER);
		
		//panel.add(table, BorderLayout.CENTER);
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

} // end AllSimGUI
