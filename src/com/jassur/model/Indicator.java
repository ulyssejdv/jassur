package com.jassur.model;

import java.util.ArrayList;

import org.json.simple.JSONObject;


public class Indicator implements Model {
	
	private int id = 0;
	
	/*
	 * Has one
	 */
	private Loan loan;
	private int loanId;
	
	
	/*
	 * Has many
	 */
	private ArrayList<Loan> loans;
	
	/*
	 * Attributes
	 */
	private int NbLoans;
	private int AvgDurationLoan;
	private int SumInterest;
	private int AvgLoansAmount;
	private int NbSimulation;
	private int NbEnCours;
	

	
	
	
	/*
	 * Constructors
	 */
	public Indicator() {
		// code ..
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	public Loan getLoan() {
		return loan;
	}



	public void setLoan(Loan loan) {
		this.loan = loan;
	}



	public int getLoanId() {
		return loanId;
	}



	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}



	public ArrayList<Loan> getLoans() {
		return loans;
	}



	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}



	public int getNbLoans() {
		return NbLoans;
	}



	public void setNbLoans(int nbLoans) {
		NbLoans = nbLoans;
	}



	public int getAvgDurationLoan() {
		return AvgDurationLoan;
	}



	public void setAvgDurationLoan(int avgDurationLoan) {
		AvgDurationLoan = avgDurationLoan;
	}



	public int getSumInterest() {
		return SumInterest;
	}



	public void setSumInterest(int sumInterest) {
		SumInterest = sumInterest;
	}



	public int getAvgLoansAmount() {
		return AvgLoansAmount;
	}



	public void setAvgLoansAmount(int avgLoansAmount) {
		AvgLoansAmount = avgLoansAmount;
	}



	public int getNbSimulation() {
		return NbSimulation;
	}



	public void setNbSimulation(int nbSimulation) {
		NbSimulation = nbSimulation;
	}

	


	public int getNbEnCours() {
		return NbEnCours;
	}



	public void setNbEnCours(int nbEnCours) {
		NbEnCours = nbEnCours;
	}



	@Override
	public String toString() {
		return "Indicator [id=" + id + ", loan=" + loan + ", loanId=" + loanId + ", loans=" + loans + ", NbLoans="
				+ NbLoans + ", AvgDurationLoan=" + AvgDurationLoan + ", SumInterest=" + SumInterest
				+ ", AvgLoansAmount=" + AvgLoansAmount + ", NbSimulation=" + NbSimulation + ", NbEnCours=" + NbEnCours
				+ "]";
	}



	@Override
	public JSONObject toJSON() {
JSONObject jObj = new JSONObject();
		
		jObj.put("loanId", this.id);
		jObj.put("nb_Loan", this.NbLoans);
		jObj.put("avg_Duration_Loan", this.AvgDurationLoan);
		jObj.put("sum_Interest", this.SumInterest);
		jObj.put("avg_Loan_Amount", this.AvgLoansAmount);
		jObj.put("nb_Simulation", this.NbSimulation);
		jObj.put("nb_EnCours", this.NbEnCours);
		if (this.loan != null) {
			jObj.put("loan", this.loan.toJSON());
			
	}
		return jObj;
	}



	@Override
	public void parseJSON(JSONObject jo) {
		
		this.id = (int) (long)jo.get("loanId");
		this.NbLoans = (int)jo.get("nb_Loans");
		this.AvgDurationLoan = (int)jo.get("avg_Duration_Loan");
		this.SumInterest = (int)jo.get("sum_Interest");
		this.AvgLoansAmount = (int)jo.get("avg_Loan_Amount");
		this.NbSimulation = (int)jo.get("nb_Simulation");
		this.NbEnCours = (int)jo.get("nb_EnCours");

		
		if (jo.containsKey("loan")) {
			Loan l = new Loan();
			l.parseJSON((JSONObject) jo.get("loan"));
			this.loan = l;
		}
		
	}



}
	

