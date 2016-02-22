package database;

import java.util.ArrayList;

public class PoolConnexion {
	
	public final static int MAX_CONNEXION = 15;
	
	private int size = 0;
	
	private ArrayList<Connexion> listConnexion;
	
	private ConfigurationDB conf = null;
	
	public PoolConnexion() {
		
		/* Loading DB configuration */
		this.conf = new ConfigurationDB();
		
		/* Initialize connection list */
		this.listConnexion = new ArrayList<Connexion>();
		this.initPool();
	}
	
	private void initPool() {
		System.out.println("Connection pool initialisation ...");
		/* Create the connection pool */
		for (int i = 0; i < PoolConnexion.MAX_CONNEXION; i++) {
			System.out.println("Connexion "+i+" OK");
			this.push(new Connexion(conf));
		}
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
