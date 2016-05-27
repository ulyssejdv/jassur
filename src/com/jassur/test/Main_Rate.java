package com.jassur.test;

import com.jassur.model.CalculateRate;
import com.jassur.model.Model_Manage_id;
import com.jassur.controller.Controller_Manage_id;
import com.jassur.controller.RateController;
import com.jassur.view.FixedRatePanel;
import com.jassur.view.View_Manage_id;

public class Main_Rate {
	
	
	public Main_Rate()
	{
		//Implement model
		CalculateRate Model = new CalculateRate();
		//Create controller
		RateController controller = new RateController(Model);
		//Create view with controller in parameter
		@SuppressWarnings("unused")
		FixedRatePanel view = new FixedRatePanel(controller);
	}	
	public static void main(String[] args) {
	   
		Main_Rate main = new Main_Rate();
	  }
}
