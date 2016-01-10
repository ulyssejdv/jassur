/**
 * 
 */
package controller;

import model.Simulation;
import model.SimulationList;
import view.CreateSimPanel;
import view.ListSimPanel;
import view.MainFrame;
import view.MainMenuPanel;
import view.ReadSimPanel;
import view.ResSimPanel;

/**
 * @author ulysse
 *
 */
public class SimulationController {
	
	private Simulation simulation;
	
	private SimulationList simulationList;

	private MainFrame mainFrame;
		
	public SimulationController(Simulation s, SimulationList sl) {
		this.simulation = s;
		this.simulationList = sl;
		//this.simulationList.addSimulation(sim);
	}
	
	public String calculAction(String m, String t, String d) {
		
		/*
		 * Hydratation of the model with the user's input data
		 */
		/*if (m.length()>0 && t.length()>0 && d.length()>0) {
			simulation.setMontant(Integer.parseInt(m));
			simulation.setTaux(Double.parseDouble(t));
			simulation.setDuree(Integer.parseInt(d));
			simulation.calcul();
			
			return "Calcul OK !";
		} else {
			return "error from input";
		}*/
		
		if (m.length()>0 && t.length()>0 && d.length()>0) {
			Simulation s = new Simulation();
			s.setMontant(Integer.parseInt(m));
			s.setTaux(Double.parseDouble(t));
			s.setDuree(Integer.parseInt(d));
			s.calcul();
			
			ResSimPanel rsp = new ResSimPanel(this);
			s.addObserver(rsp);
			s.notifyObserver();
			this.mainFrame.render(rsp);
			
			return "Calcul OK !";
		} else {
			return "error from input";
		}
	}
	
	public void indexAction() {
		this.mainFrame.render(new MainMenuPanel(this));
	}
	
	public void createAction() {
		CreateSimPanel csp = new CreateSimPanel(this);
		Simulation s = new Simulation();
		s.addObserver(csp);
		s.notifyObserver();
		this.mainFrame.render(csp);
	}
	
	public void resetAction() {
		this.simulation.setMontant(0);
		this.simulation.setTaux(0);
		this.simulation.setDuree(0);
	}
	
	public void listAction() {
		ListSimPanel lsp = new ListSimPanel(this);
		simulationList.addObserver(lsp);
		simulationList.notifyObserver();
		this.mainFrame.render(lsp);
	}
	
	/**
	 * Display this Simulation
	 * @param idSimulation
	 */
	public void readAction(int idSimulation) {
		ReadSimPanel rsp = new ReadSimPanel(this);
		Simulation tmpSim = new Simulation();
		
		for	(Simulation s : simulationList.getListSimulation()) {
			if (s.getId() == idSimulation) {
				tmpSim = s;
			}
		}
		
		tmpSim.addObserver(rsp);
		tmpSim.notifyObserver();
		
		this.mainFrame.render(rsp);
	}
	
	/**
	 * Update this Simulation
	 * @param idSimulation
	 * @param montant
	 * @param taux
	 * @param duree
	 */
	public void updateAction(int idSimulation, String m, String t, String d) {
		// TODO
	}
	
	
	public void editAction(Simulation s) {
		CreateSimPanel csp = new CreateSimPanel(this);
		s.addObserver(csp);
		s.notifyObserver();
		this.mainFrame.render(csp);
	}
	/**
	 * Delete this simulation
	 * @param idSimulation
	 */
	public String deleteAction(int idSimulation) {
		
		String msg = new String();
		
		Simulation tmpSim = new Simulation();
		
		for	(Simulation s : simulationList.getListSimulation()) {
			if (s.getId() == idSimulation) {
				tmpSim = s;
				msg = "Simulation deleted";
			}
		}
		
		simulationList.removeSimulation(tmpSim);
		
		ListSimPanel lsp = new ListSimPanel(this);
		simulationList.addObserver(lsp);
		simulationList.notifyObserver();
		this.mainFrame.render(lsp);
		
		return msg;
	}
	
	/**
	 * Save this Simulation into DB
	 * @param s
	 * @return String message ACK
	 */
	public String saveAction(Simulation s) {
		
		Simulation simulation = s;
		simulation.takeId();
		simulationList.addSimulation(simulation);
		
		ListSimPanel lsp = new ListSimPanel(this);
		simulationList.addObserver(lsp);
		simulationList.notifyObserver();
		this.mainFrame.render(lsp);
		return "Simulation added.";
	}

	public void setMainFrame(MainFrame mf) {
		this.mainFrame = mf;
	}
}
