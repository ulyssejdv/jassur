
/**
 * 
 */
package com.jassur.model;

import org.json.simple.JSONObject;

/**
 * @author peyth_000
 *
 */
public class newRate implements Model {
	
	
	/*
	 * Attributes
	 */
	private double ratecompany;
	private double newRate;
	private int duration;
	private int age;
	private String job;
	private String risk;
	private int id_profile=0;
	private boolean healthy;
	
	public newRate()
	{
		
	}
	
	
	@Override
	public String toString() {
		return "\n newRate [id_profile=" + id_profile + ", ratecompany=" + ratecompany + ", healthy=" + healthy
				+ ", duration=" + duration + ", =" + risk +
				", newRate=" + newRate + ", age=" + age +  ", job=" + job +"]";
	}
	
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getProfileId() {
		return id_profile;
	}
	public void setProfileId(int id_profile) {
		this.id_profile = id_profile;
	}
	public double getRateCompany() {
		return ratecompany;
	}
	public void setRateCompany(double ratecompany) {
		this.ratecompany = ratecompany;
	}
	public boolean gethealthy() {
		return healthy;
	}
	public void sethealthy(boolean healthy) {
		this.healthy = healthy;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public double getNewRate() {
		return newRate;
	}
	public void setNewRate(double newRate) {
		this.newRate = newRate;
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("id_profile", this.getProfileId());
		jObj.put("age", this.getAge());
		jObj.put("duration", this.getDuration());
		jObj.put("job", this.getJob());
		jObj.put("healthy", this.gethealthy());
		jObj.put("ratecompany", this.getRateCompany());
		jObj.put("newRate", this.getNewRate());
		jObj.put("risk", this.getRisk());
		
		return jObj;
	}
	@Override
	public void parseJSON(JSONObject jo) {
		this.id_profile = (int)(long)jo.get("id_profile");
		this.age = (int)(long)jo.get("age");
		this.duration = (int)(long)jo.get("duration");
		this.job = (String)jo.get("job");
		this.healthy = (boolean)jo.get("healthy");
		this.ratecompany = (double)jo.get("ratecompany");
		this.newRate = (double)jo.get("newRate");
		this.risk = (String)jo.get("risk");
	}


	
}
