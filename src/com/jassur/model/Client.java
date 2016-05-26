package com.jassur.model;

import java.util.ArrayList;

import org.json.simple.JSONObject;


public class Client implements Model {
	
	private int id = 0;
	
	/*
	 * Has one
	 */
	private Address address;
	private int addressId;
	
	
	/*
	 * Has many
	 */
	private ArrayList<Loan> loans;
	
	/*
	 * Attributes
	 */
	private String firstName = "";
	private String lastName = "";
	private String phone = "";
	private String email = "";
	
	private boolean business;

	
	
	
	/*
	 * Constructors
	 */
	public Client() {
		// code ..
	}
	
	
	public String toHTML() {
		
		String htmlString = "";
		
		htmlString += "<h1>Restitution de Pret</h1>";

		htmlString += "<h2>Informations client</h2>";
		htmlString += this.getLastName()+", "+this.getFirstName()+" <br>";
		htmlString += this.getAddress().getStreet()+"<br>"; 
		htmlString += this.getAddress().getZip()+" "+this.getAddress().getCity()+" "+this.getAddress().getCountry()+"<br>";
		
		return htmlString;
	}
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}


	
	public ArrayList<Loan> getLoans() {
		return loans;
	}
	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}


	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	
	public boolean getBusiness() {
		return business;
	}
	public void setBusiness(boolean business) {
		this.business = business;
	}
	
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	
	

	@Override
	public String toString() {
		return "Client [id=" + id + ", address=" + address + ", addressId=" + addressId + ", loans=" + loans
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email
				+ ", business=" + business + "]";
	}



	@Override
	public JSONObject toJSON() {
		
		JSONObject jObj = new JSONObject();
		
		jObj.put("id_client", this.id);
		jObj.put("first_name", this.firstName);
		jObj.put("last_name", this.lastName);
		jObj.put("phone", this.phone);
		jObj.put("email", this.email);
		jObj.put("business", this.business);
		if (this.address != null) {
			jObj.put("address", this.address.toJSON());
		}
		
		
		return jObj;
	}



	@Override
	public void parseJSON(JSONObject jo) {
		this.id = (int) (long)jo.get("id_client");
		this.firstName = (String)jo.get("first_name");
		this.lastName = (String)jo.get("last_name");
		this.phone = (String)jo.get("phone");
		this.email = (String)jo.get("email");
		this.business = (boolean)jo.get("business");
		
		if (jo.containsKey("address")) {
			Address ad = new Address();
			ad.parseJSON((JSONObject) jo.get("address"));
			this.address = ad;
		}
	
	}
	
}
