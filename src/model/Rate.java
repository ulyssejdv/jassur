/**
 * 
 */
package model;

/**
 * @author ulysse
 *
 */
public class Rate {
	
	
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
	
}
