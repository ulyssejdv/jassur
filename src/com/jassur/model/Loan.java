package com.jassur.model;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Loan implements Model {
	
	/*
	 * Has one
	 */
	private Client client;
	private int client_id;
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

	private int category_id;
	private double insurance;
	
	
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
		//ClientController cc=new ClientController();
		//this.client_id=(int)jo.get("client_id");
		jObj.put("client_id", this.getClient().toJSON());
		System.out.println("Loan 1");
		if (this.getCategory() != null) {
			jObj.put("category", this.getCategory().toJSON());
		}
		System.out.println("Loan 2");

		if (this.getRates() != null) {
			JSONArray ratesJson = new JSONArray();
			for (Rate rate : this.getRates()) {
				ratesJson.add(rate.toJSON());
			}
			jObj.put("rates", ratesJson);
		}

		if (this.getStates() != null) {
			JSONArray statesJson = new JSONArray();
			for (State state : this.getStates()) {
				statesJson.add(state.toJSON());
			}
			jObj.put("states", statesJson);
		}
		System.out.println("Loan 3");
		System.out.println(jObj.toString());
		System.out.println("Loan 4");

		return jObj;
	}

	@Override
	public void parseJSON(JSONObject jo) {
		this.id = (int)(long)jo.get("id_loan");
		this.amount = (int)(long)jo.get("amount");
		this.totalDuration = (int)(long)jo.get("total_duration");
		this.totalAmount = (double)jo.get("total_amount");
		if (jo.containsKey("states")) {
			JSONArray jarray = (JSONArray) jo.get("states");
			
			for (Object ob : jarray) {
				State s = new State();
				s.parseJSON((JSONObject) ob);
				System.out.println("Loan 1"+s.toString());
				addState(s);
			}
		}	
		if (jo.containsKey("category")) {
			Category c=new Category();
			JSONObject jobject = (JSONObject) jo.get("category");
			c.parseJSON(jobject);
			setCategory(c);

		}
		if (jo.containsKey("client_id")) {
			Client c=new Client();
			JSONObject jobject = (JSONObject) jo.get("client_id");
			c.parseJSON(jobject);
			setClient(c);
		}
		if (jo.containsKey("rates")) {
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
	
	/**
	 * Get the periodic rate for this loan
	 * @return 
	 */
	public double periodicRate() {
		return this.getRates().get(0).getInterestRate()/12;
	}
	
	/**
	 * Get the amount of insurance per month
	 * @return
	 */
	public double insurancePerMonth() {
		return (this.getAmount()*(this.getInsurance()/100))/this.getTotalDuration();
	}
	
	public double remainingCapital(double rc, double i, double mp) {
		return rc-(mp-i);
	}
	
	public double interestPerMonth(double rc) {
		return (rc*this.periodicRate())/100;
	}
	
	public double principal(double m, double i) {
		return m-i;
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
	
	public double getInsurance() {
		return insurance;
	}
	
	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Loan [client=" + client + ", client_id=" + client_id + ", category=" + category + ", rates=" + rates
				+ ", states=" + states + ", amount=" + amount + ", totalDuration=" + totalDuration + ", totalAmount="
				+ totalAmount + ", category_id=" + category_id + ", id=" + id + "]";
	}

}
