package com.jassur.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	
	private Date createdAt;
	
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
		
		
		SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yy-MM-dd");
        
        Date date = null;
        date = new Date(this.getCreatedAt().getTime());
        /*
		try {
			date = formater.parse(this.getCreatedAt().toString());
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		
		jObj.put("created_at", formater.format(date));

		if (this.getClient() != null) {
			jObj.put("client_id", this.getClient().toJSON());
		}
		
		
		
		if (this.getCategory() != null) {
			jObj.put("category", this.getCategory().toJSON());
		}

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

		return jObj;
	}

	@Override
	public void parseJSON(JSONObject jo) {
		this.id = (int)(long)jo.get("id_loan");
		this.amount = (int)(long)jo.get("amount");
		this.totalDuration = (int)(long)jo.get("total_duration");
		this.totalAmount = (double)jo.get("total_amount");
		
		
		SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("yy-MM-dd");
        DateFormat df = DateFormat.getDateInstance();
        
        Date date = null;
		try {
			date = formater.parse((String)jo.get("created_at"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		this.createdAt = date;
		
		if (jo.containsKey("states")) {
			JSONArray jarray = (JSONArray) jo.get("states");
			
			for (Object ob : jarray) {
				State s = new State();
				s.parseJSON((JSONObject) ob);
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
	
	
	public String toHTML() {
		String htmlString = "";

		htmlString += "<h2>Parametres</h2>";
		htmlString += "<table>";
		htmlString += "<tr><td>Montant total du pret</td><td>"+this.getAmount()+
				"</td><td>Taux d'interet du pret</td><td>"+this.getRates().get(0).getInterestRate()+"</td></tr>";
		htmlString += "<tr><td>Durée du pret</td><td>"+this.getTotalDuration()+"</td><td>Frais de dossier</td><td>0</td></tr>";
		htmlString += "<tr><td>Echéance assurance comprise</td><td>-</td><td>Cout total du pret</td><td>"+this.getTotalAmount()+
				"</td></tr>";
		htmlString += "<tr><td>Echeance de l'assurance</td><td>-</td><td>Echeance hors assurance</td><td>"+this.getRates().get(0).getMonthlyPayment()+"</td>";
		htmlString += "</tr>";
		htmlString += "</table>";

		htmlString += "<h2>Table de remboursement</h2>";
		
		return htmlString;
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
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public int getClientId() {
		return this.client_id;
	}
	
	public void setClientId(int client_id) {
		this.client_id = client_id;
	}
	
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
