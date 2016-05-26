/**
 * 
 */
package com.jassur.controller;

import com.jassur.model.CalculateRate;

/**
 * @author jeremy
 */
public class RateController {

	CalculateRate mod;
	private double GetRate;
	private String GetRisk;

	public RateController(CalculateRate mr )
	{
		mod=mr;		

	}
	public double  get_rate (double inputTauxMM2, int InputAge, int InputDuration, double valeurRadio)
	{
		GetRate=mod.Calculs(inputTauxMM2, InputAge, InputDuration, valeurRadio);
		return GetRate;
	}
	public String get_Risk(double valeurRadio, int InputAge, int InputDuration, String Job)
	{
		GetRisk = mod.ReturnRisk(valeurRadio, InputAge, InputDuration, Job);
		return GetRisk;
	}

}
