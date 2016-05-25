/**
 * 
 */
package com.jassur.controller;

import com.jassur.model.CalculateRate;

/**
 * @author jérémy
 */
public class RateController {

	CalculateRate mod;
	private double GetRate;
	
	public RateController(CalculateRate mr )
	{
		mod=mr;		
		
	}
	public double  get_rate (double inputTauxMM2, int InputAge, int InputDuration, double valeurRadio)
	{
		GetRate=mod.Calculs(inputTauxMM2, InputAge, InputDuration, valeurRadio);
		return GetRate;
	}
}
