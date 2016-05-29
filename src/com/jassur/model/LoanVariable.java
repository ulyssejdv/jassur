package com.jassur.model;

import com.jassur.utils.Util;


// Data and methods used to work with it
public class LoanVariable {
	
	// calcule le montant constant

	private double calculationValue;
	private double calculationBestValue;
	private double calculationWorstValue;
	
	
	
	
	// calculate constant loan
	public void addValues (double amount, double nbYears, double loanV ){
		// Math pow = puissance
		// 12 calculate per month
	    System.out.println("amount="+amount+" | nbYears = "+nbYears+" | loanV="+loanV);
		calculationValue = Util.round(((amount * (loanV/100))/ 12 ) / (1 - (1/(Math.pow( (1 + ((loanV/100)/12)),(nbYears*12))))),2);

	}

	//good scenario
	public void addBestValues (double amount, int nbYears, double loanV ){
		calculationBestValue = calculationValue ;
		System.out.println("calculationValue="+calculationValue);

	}
	
	public void addWorstValues (double amount, int nbYears, double loanV ){
		calculationWorstValue = calculationValue * 1.0009 ;
		
	}
	
	// calculate constant loan
	public double getCalculationValue(){
		
		return calculationValue;
	}
	//good scenario

	public double getCalculationBestValue(){
		
		return calculationBestValue;
	}
	public double getCalculationWorstValue(){
		
		return calculationWorstValue;
	}
	
	
}


	
	
	
