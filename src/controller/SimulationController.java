/**
 * 
 */
package controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import message.Message;
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
	}
	
	public String calculAction(String m, String t, String d) {
		
		if (m.length()>0 && t.length()>0 && d.length()>0) {
			Simulation s = new Simulation();
			s.setMontant(Double.parseDouble(m));
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
		
		/* Building of xml REST message */
		Message m = new Message();
		String xmlMsg = m.get("pret");
		
		simulationList.parseXML(xmlMsg);
		
		/* Rending the list view panel */
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
		
		/* Building of xml REST message */
		Message m = new Message();
		String xmlMsg = m.get("pret", idSimulation);
		
		Simulation simulation = new Simulation();
		
		simulation.parseXML(xmlMsg);
		
		ReadSimPanel rsp = new ReadSimPanel(this);
		simulation.addObserver(rsp);
		simulation.notifyObserver();
		
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
	public void deleteAction(int idSimulation) {
		
		Message m = new Message();
		
		String xmlMsg = m.delete("pret", idSimulation);
		
		System.out.println("resultat du delete : "+xmlMsg);
		
		//Simulation tmpSim = new Simulation();
		
		System.out.println("Message envoyé : "+m.toString());
		
		//tmpSim.sendRequestDelete(xmlMsg);
		
		/* Go To the list */
		this.listAction();
	}
	
	/**
	 * Save this Simulation into DB
	 * @param s
	 * @return String message ACK
	 */
	public void saveAction(Simulation s) {
		
		// il faut envoyer une requete POST pour ajouter ce pret
		
		/*s.toString();
		Simulation simulation = s;
		simulation.takeId();
		simulationList.addSimulation(simulation);*/
		
		Message m = new Message();
		
		/* On execute une requete de type post
		 * et on s'attend à avoir une reponse au format XML 
		 * (dans le cas présent si ça s'est bien passé ou non
		 */
		String rsp = m.post("pret", s.toXML());
		
		/*ListSimPanel lsp = new ListSimPanel(this);
		simulationList.addObserver(lsp);
		simulationList.notifyObserver();
		this.mainFrame.render(lsp);*/
		
		this.listAction();
	}

	public void setMainFrame(MainFrame mf) {
		this.mainFrame = mf;
	}

	public void pingAction() {
	
		
		
	}
}
