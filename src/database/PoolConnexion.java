package database;

import java.util.ArrayList;

import model.ConnectionDB;

public class PoolConnexion {
	
	public final static int MAX_CONNEXION = 15;
	
	private int size = 0;
	
	private ArrayList<Connexion> listConnexion;
	
	public PoolConnexion() {
		this.listConnexion = new ArrayList<Connexion>();
	}
	
	/* Add new connexion */
	public void push(Connexion cdb) {
		this.listConnexion.add(cdb);
		this.size += 1;
	}
	
	/* Remove connexion */
	public Connexion pop() {
		Connexion c = null;
		if (this.size > 0) {
			c = this.listConnexion.remove(0);
			this.size -= 1;
		}
		return c;
	}
	
}
