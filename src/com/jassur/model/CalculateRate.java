package com.jassur.model;

public class CalculateRate {

	private double taux;
	private int pts=0;
	private String Result="";

	/**
	 * @author jeremy
	 * Calculs rates.
	 * Calcul Risk
	 * @return 
	 */
	public String ReturnRisk(double valeurRadio, int InputAge, int InputDuration, String Job)
	{
		// number of points awarded according to Health status
		if(valeurRadio==+0.50)
		{
			pts=pts + 20;
		}else if(valeurRadio == -0.50)
		{
			pts = pts+5;
		}
		// number of points awarded according to age range
		if(InputAge >= 18 && InputAge <= 25 )
		{
			pts=pts + 10;
		}else if (InputAge > 25 && InputAge <= 45)
		{
			pts=pts+5;
		}
		else if (InputAge > 45 && InputAge <= 65)
		{
			pts=pts+15;
		}
		else if (InputAge > 65 && InputAge <= 85)
		{
			pts=pts+20;	
		}
		//Duration range
		if(InputDuration<=12)
		{
			pts=pts+5;

		}
		else if(InputDuration >= 18 && InputDuration <= 25 )
		{
			pts=pts + 10;
		}else if (InputDuration > 25 && InputDuration <= 45)
		{
			pts=pts+15;
		}
		else if (InputDuration > 45 && InputDuration <= 65)
		{
			pts=pts+20;
		}
		else if (InputDuration > 65 && InputDuration <= 85)
		{
			pts=pts+30;	
		}
		else if (InputDuration > 85 )
		{
			pts = pts+40;
		}
		//number of points awarded according to professional status
		if(Job == "CDD")
		{
			pts=pts+10;	

		}else if (Job == "CDI")
		{
			pts=pts+5;	

		}else if (Job == "Interimaire")
		{
			pts=pts+15;	

		}
		else if (Job == "Chomeur")
		{
			pts=pts+20;	

		}
		//Result 
		if(pts<= 45)
		{
			System.out.println("Nombres de points : " + pts);
			Result = "Le risque que le client soit d\u00E9faillant est faible !";
			pts=0;
		}else if(pts<= 60)
		{
			System.out.println("Nombres de points : " + pts);
			Result = "Le risque que le client soit d\u00E9faillant est mod\u00E9r\u00E9 !";
			pts=0;

		}else if(pts> 75)
		{
			System.out.println("Nombres de points : " + pts);
			Result = "Le risque que le client soit d\u00E9faillant est \u00E9lev\u00E9 !";
			pts=0;


		}
		return Result;
	}

	public double Calculs(double inputTauxMM2, int InputAge, int InputDuration, double valeurRadio){
		// if the rate company is upper at 0
		if(inputTauxMM2>0)
		{
			if(InputAge <= 45 && InputAge >=18)
			{
				if(InputDuration <= 24)
				{
					taux=inputTauxMM2 - 0.10+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration <= 48){
					taux=inputTauxMM2 + 0.30+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration <= 72){
					taux=inputTauxMM2 + 0.50+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration >72){
					taux=inputTauxMM2 + 0.85 +valeurRadio;
					System.out.println(taux);
					return taux;
				}

			}
			else if (InputAge > 45 && InputAge <= 65)
			{
				if(InputDuration <= 24)
				{
					taux=inputTauxMM2 + 0.10+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration <= 48){
					taux=inputTauxMM2 + 0.50+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration <= 72){
					taux=inputTauxMM2 + 0.80+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration >72){
					taux=inputTauxMM2 + 1 +valeurRadio;
					System.out.println(taux);
					return taux;
				}
			}
			else if (InputAge > 65)
			{
				if(InputDuration <= 24)
				{
					taux=inputTauxMM2 + 0.30+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration <= 48){
					taux=inputTauxMM2 + 0.70+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration <= 72){
					taux=inputTauxMM2 + 1+valeurRadio;
					System.out.println(taux);
					return taux;
				}else if (InputDuration >72){
					taux=inputTauxMM2 + 1.2 +valeurRadio;
					System.out.println(taux);
					return taux;
				}

			}
		}
		return 0;
	}
}