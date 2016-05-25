package com.jassur.test;

import com.jassur.controller.LoanVariableController;
import com.jassur.model.LoanVariable;
import com.jassur.view.LoanVariableShowCardPanel;
import com.jassur.view.ShowBestScenarios;

public class MVCLoanVariable {
	
	public static void main(String[] args){
		LoanVariableShowCardPanel theView = new LoanVariableShowCardPanel();
		
		LoanVariable theModel = new LoanVariable();
		
		LoanVariableController theController = new LoanVariableController( theView, theModel);
		
		// create the link for the new windows
		ShowBestScenarios theBestS = new ShowBestScenarios();
		theBestS.setVisible(true);
		theView.setVisible(true);
		
	}

}
