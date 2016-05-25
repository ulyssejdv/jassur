package com.jassur.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.jassur.model.LoanVariable;
import com.jassur.view.LoanVariableShowCardPanel;
import com.jassur.view.ShowBestScenarios;

// Class controller coordinates interactions between the view and model

public class LoanVariableController{
	

	private LoanVariableShowCardPanel theView;
	private LoanVariable theModel;
	
	public LoanVariableController(LoanVariableShowCardPanel theView, LoanVariable theModel ){
		
		this.theView = theView;
		this.theModel = theModel;
		//tell the view that when ever the calculate button is clicked to execute the actionPerformed method in the CalculateListener inner class
		
		this.theView.addCalculationListener(new CalculateListener());
		
	}
	class CalculateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			double unMontant, uneDuree  ;
			double unTaux ;
			
			
			try {
				unMontant = theView.getUnMontant();
				uneDuree = theView.getUneDuree();
				unTaux = theView.getUnTaux();
				
				theModel.addValues(unMontant, uneDuree, unTaux );
				theView.setCalcSolution(theModel.getCalculationValue());
			}
			catch(NumberFormatException ex){
				
				System.out.println(ex);
				
				theView.displayErrorMessage("Vous devez rentrer des valeurs");
			}
			
		}
		
	}
}
	