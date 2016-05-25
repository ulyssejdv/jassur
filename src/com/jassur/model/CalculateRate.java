package com.jassur.model;

public class CalculateRate {

	private double taux;

	/**
	 * Calculs rates.
	 * @return 
	 */
	
	public double Calculs(double inputTauxMM2, int InputAge, int InputDuration, double valeurRadio){

		//Inferieur ou egale a 45 ans et superieur e 18 ans

		if(InputAge <= 45 && InputAge >=18)
		{
			if(InputDuration <= 72)
			{
				taux=inputTauxMM2 - 0.30+valeurRadio;
				System.out.println(taux);
				return taux;
			}
			else{
				taux=inputTauxMM2 + 0.30+valeurRadio;
				System.out.println(taux);
				return taux;
			}

		}
		else if (InputAge > 45)
		{
			if(InputDuration <= 72)
			{
				taux=inputTauxMM2 + 0.20+valeurRadio;
				System.out.println(taux);
				return taux;

			}
			else{
				taux=inputTauxMM2 + 0.40+valeurRadio;
				System.out.println(taux);
				return taux;

			}
		}
		return 0;
	}
}