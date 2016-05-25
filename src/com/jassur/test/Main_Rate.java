package com.jassur.test;

import com.jassur.model.CalculateRate;
import com.jassur.controller.RateController;
import com.jassur.view.FixedRatePanel;


public class Main_Rate {

	
	public static void main(String[] args) {

		//Instanciation de notre modele
				CalculateRate Model = new CalculateRate();
			    //Creation du controleur
				RateController controller = new RateController(Model);
			    //Creation de notre fenetre avec le controleur en parametre
				FixedRatePanel view = new FixedRatePanel(controller);
	}
}
