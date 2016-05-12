/**
 * 
 */
package com.jassur.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author ulysse
 *
 */
public class Rate implements Model {
	
	
	/*
	 * Attributes
	 */
	private int id = 0;
	private double interestRate;
	private double monthlyPayment;
	private int duration;
	private int loanId;
	
	
	
	
	@Override
	public String toString() {
		return "\n Rate [id=" + id + ", interestRate=" + interestRate + ", monthlyPayment=" + monthlyPayment
				+ ", duration=" + duration + ", loanId=" + loanId + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	public void setMonthlyPayment(double monthlyPayment) {
		this.monthlyPayment = monthlyPayment;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loan_id) {
		this.loanId = loan_id;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("id_rate", this.getId());
		jObj.put("interest_rate", this.getInterestRate());
		jObj.put("duration", this.getDuration());
		jObj.put("monthly_payment", this.getMonthlyPayment());
		
		return jObj;
	}
	@Override
	public void parseJSON(JSONObject jo) {
		this.id = (int)(long)jo.get("id_rate");
		this.duration = (int)(long)jo.get("duration");
		this.interestRate = (double)jo.get("interest_rate");
		this.monthlyPayment = (double)jo.get("monthly_payment");	
	}
	
}
