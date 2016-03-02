package com.jassur.model;

import org.json.simple.JSONObject;

public class Address implements Model {
	
	private int id = 0;
	
	private String street;
	private String city;
	private String country;
	private String region;
	
	private int zip;
	
	private int clientId;

	
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("id_address", this.id);
		jObj.put("street", this.street);
		jObj.put("city", this.city);
		jObj.put("country", this.country);
		jObj.put("region", this.region);
		jObj.put("zip", this.zip);
		
		return jObj;
	}

	@Override
	public void parseJSON(JSONObject jo) {
		this.id = (int) (long)jo.get("id_address");
		this.street = (String)jo.get("street");
		this.city = (String)jo.get("city");
		this.country = (String)jo.get("country");
		this.region = (String)jo.get("region");
		this.zip = (int) (long)jo.get("zip");
	}
	

}
