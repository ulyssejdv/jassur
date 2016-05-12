package com.jassur.model;


import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Loan implements Model {
	
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
	
	@Override
	public JSONObject toJSON() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("id_loan", this.id);
		jObj.put("amount", this.getAmount());
		jObj.put("total_duration", this.getTotalDuration());
		jObj.put("total_amount", this.getTotalAmount());
		
		/*if (this.getCategory() != null) {
			jObj.put("category", this.getCategory().toJSON());
		}*/
		
		if (this.getRates() != null) {
			JSONArray ratesJson = new JSONArray();
			for (Rate rate : this.getRates()) {
				ratesJson.add(rate.toJSON());
			}
			jObj.put("rates", ratesJson);
		}
		
		return jObj;
	}

	@Override
	public void parseJSON(JSONObject jo) {
		this.id = (int)(long)jo.get("id_loan");
		this.amount = (int)(long)jo.get("amount");
		this.totalDuration = (int)(long)jo.get("total_duration");
		this.totalAmount = (double)jo.get("total_amount");
		
		if (jo.containsKey("rates")) {
			Address ad = new Address();
			JSONArray jarray = (JSONArray) jo.get("rates");
			
			for (Object ob : jarray) {
				Rate r = new Rate();
				r.parseJSON((JSONObject) ob);
				addRate(r);
			}
		}	
	}
	
	
	
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
