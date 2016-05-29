package com.jassur.test;

import com.jassur.controller.RateController;
import com.jassur.model.CalculateRate;
import com.jassur.model.Client;
import com.jassur.view.BaseGUI;
import com.jassur.view.FixedRatePanel;

public class Main_Rate {
	
	
	public Main_Rate(BaseGUI bg, Client client)
	{
		//Implement model
		CalculateRate Model = new CalculateRate();
		//Create controller
		RateController controller = new RateController(Model);
		//Create view with controller in parameter
		@SuppressWarnings("unused")
		FixedRatePanel view = new FixedRatePanel(controller, bg, client);
	}	
	/*public static void main(String[] args) {
		Main_Rate main = new Main_Rate();
	}*/
}
