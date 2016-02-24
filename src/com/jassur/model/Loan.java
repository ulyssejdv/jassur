package com.jassur.model;


import java.util.ArrayList;


public class Loan {
	
	/*
	 * Has one
	 */
	private Client client;
	private Category category;
	
	
	
	/*
	 * Has many
	 */
	private ArrayList<Rate> rates;
	private ArrayList<State> states;
	
	
	
	/*
	 * Attributes
	 */
	private int amount;
	private int totalDuration;
	private double totalAmount;
	
	
	private int id = 0;
	
	
	
	/*
	 * Constructors
	 */
	public Loan() {
		this.rates = new ArrayList<Rate>();
		this.states = new ArrayList<State>();
	}
	
	public Loan(int id) {

	}
	
	
	/*
	 * JSON
	 */

	/*public JSONObject toJSON() {
		
		JSONObject jPret = new JSONObject();
		
		jPret.put("id", this.getIdPret());
		jPret.put("montant", this.getMontant());
		jPret.put("mensualite", this.getMontantEcheance());
		jPret.put("nbmensualite", this.getNombreEcheance());
		jPret.put("taux", this.getTaux());
		jPret.put("total", this.getTotal());
		
		return jPret;
	}*/


	/*public void parseJSON(String json) {
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject obj = (JSONObject) parser.parse(json);
			
			obj.get("resource");
			
			this.setResource((String) obj.get("resource")); 
			this.setMethod((String) obj.get("method"));
			
			this.set
			
			if (obj.get("body") != null) {
				this.setBody((String) obj.get("body").toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}*/
	
	
	
	/*
	 * List manipulation
	 * -------------------------------------------------
	 */
	
	public void addRate(Rate r) {
		boolean test = true;
		for (Rate rate : rates) {
			if (rate.getId() == r.getId()) {
				test = false;
			}
		}
		if (test) {
			this.rates.add(r);
		}
	}
	
	public void removeRate(Rate r) {
		this.rates.remove(r);
	}
	
	public void addState(State s) {
		
		boolean test = true;
		for (State state : states) {
			if (state.getId() == s.getId()) {
				test = false;
			}
		}
		if (test) {
			this.states.add(s);
		}
	}
	
	public void removeState(State s) {
		this.states.remove(s);
	}
	
	
	
	/*
	 * Getters & Setters
	 * -------------------------------------------------
	 */
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ArrayList<Rate> getRates() {
		return rates;
	}

	public void setRates(ArrayList<Rate> rates) {
		this.rates = rates;
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public void setStates(ArrayList<State> states) {
		this.states = states;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(int totalDuration) {
		this.totalDuration = totalDuration;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Loan [category=" + category + ", \n rates=" + rates + ", \n states=" + states + ", \n amount=" + amount
				+ ", \n totalDuration=" + totalDuration + ", \n totalAmount=" + totalAmount + ", \n id=" + id + "]";
	}
	
	
	
	
}
