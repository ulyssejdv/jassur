/**
 * 
 */
package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

/**
 * @author ulysse
 *
 */
public class Simulation implements Observable {
	
	public static int compteur = 0;
	
	protected int id;

	protected int duree = 0;
	
	protected int montant = 0;
	
	protected double taux = 0.0;
	
	protected double mensualite = 0.0;
	
	protected double total = 0.0;

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public Simulation() {
		
	}
	
	public void calcul() {
		this.total = new Double(this.montant + ((this.montant * this.taux)/100));
		this.mensualite = this.total / this.duree;
		this.notifyObserver();
	}
	
	public void takeId() {
		compteur++;
		id = compteur;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public double getTaux() {
		return taux;
	}
	
	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getMensualite() {
		return mensualite;
	}

	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void reset() {
		// TODO 
	}
	

	@Override
	public String toString() {
		return "Simulation [id=" + id +", duree=" + duree + ", montant=" + montant + ", taux=" + taux + ", mensualite=" + mensualite
				+ ", total=" + total + "]";
	}
	
	public Object[] toRawTable() {
		Object[] o = {12, 13, 1.90};
		return o;
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

}
