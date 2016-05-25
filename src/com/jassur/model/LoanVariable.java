package com.jassur.model;

// Data and methods used to work with it
public class LoanVariable {
	
	// calcule le montant constant

	private int calculationValue;
	
	public void addValues (int unMontant, int uneDuree, int unTaux){
		calculationValue = (unMontant * unTaux) / uneDuree ;
	}
	
	public int getCalculationValue(){
		
		return calculationValue;
	}
}