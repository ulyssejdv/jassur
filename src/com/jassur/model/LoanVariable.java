package com.jassur.model;

// Data and methods used to work with it
public class LoanVariable {
	
	// calcule le montant constant

	private double calculationValue;
	
	public void addValues (double unMontant, double uneDuree, double unTaux ){
		calculationValue = ((unMontant * unTaux)/ 12 ) / (1 - (1/(Math.pow( (1 + (unTaux/12)),(uneDuree*12)))));

	}
	
	public double getCalculationValue(){
		
		return calculationValue;
	}
}