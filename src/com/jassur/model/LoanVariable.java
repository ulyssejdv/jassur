package com.jassur.model;

import com.jassur.utils.Util;


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
	    System.out.println("unMontant="+unMontant+" | uneDuree = "+uneDuree+" | unTaux="+unTaux);
		calculationValue = Util.round(((unMontant * (unTaux/100))/ 12 ) / (1 - (1/(Math.pow( (1 + ((unTaux/100)/12)),(uneDuree*12))))),2);

	}

	//good scenario
	public void addBestValues (double unMontant, int uneDuree, double unTaux ){
		calculationBestValue = calculationValue ;
		System.out.println("calculationValue="+calculationValue);

	}
	
	public void addWorstValues (double unMontant, int uneDuree, double unTaux ){
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


	
	
	
