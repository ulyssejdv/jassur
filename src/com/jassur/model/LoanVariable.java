package com.jassur.model;


// Data and methods used to work with it
public class LoanVariable {
	
	// calcule le montant constant

	private double calculationValue;
	private double calculationBestValue;
	private double calculationWorstValue;
	
	
	
	
	// calculate constant loan
	public void addValues (double unMontant, double uneDuree, double unTaux ){
		// Math pow = puissance
		// 12 calculate per month
		calculationValue = ((unMontant * unTaux)/ 12 ) / (1 - (1/(Math.pow( (1 + (unTaux/12)),(uneDuree*12)))));

	}

	//good scenario
	public void addBestValues (double unMontant, double uneDuree, double unTaux ){
		calculationBestValue = (((unMontant * unTaux)/ 12 ) / (1 - (1/(Math.pow( (1 + (unTaux/12)),(uneDuree*12))))))* 0.99995 ;
		
	}
	
	public void addWorstValues (double unMontant, double uneDuree, double unTaux ){
		calculationWorstValue = (((unMontant * unTaux)/ 12 ) / (1 - (1/(Math.pow( (1 + (unTaux/12)),(uneDuree*12))))))* 1.0009 ;
		
	}
	
	// calculate constant loan
	public double getCalculationValue(){
		
		return calculationValue;
	}
	//good scenario

	public double getBestCalculationValue(){
		
		return calculationBestValue;
	}
	public double getWorstCalculationValue(){
		
		return calculationWorstValue;
	}
	
}


	
	
	
