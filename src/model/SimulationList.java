/**
 * 
 */
package model;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

/**
 * @author ulysse
 *
 */
public class SimulationList implements Observable {
	
	private ArrayList<Simulation> listSimulation = new ArrayList<Simulation>();
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private int compteur = 0;
	
	public void addSimulation(Simulation s) {
		listSimulation.add(s);
		notifyObserver();
	}
	
	public void removeSimulation(Simulation s) {
		listSimulation.remove(s);
		notifyObserver();
	}
	
	public int countSimulation() {
		return listSimulation.size();
	}
	
	
	public ArrayList<Simulation> getListSimulation() {
		return listSimulation;
	}

	public void setListSimulation(ArrayList<Simulation> listSimulation) {
		this.listSimulation = listSimulation;
	}

	public void printSimulations() {
		for	(Simulation s : listSimulation) {
			System.out.println(s.toString());
		}
	}
	
	@Override
	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		listObserver.remove(obs);
	}

	@Override
	public void notifyObserver() {
		for	(Observer obs : listObserver) {
			obs.update(this);
		}
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}


}
