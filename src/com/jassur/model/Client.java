package com.jassur.model;

import java.util.ArrayList;


public class Client {
	
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
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	
	private boolean business;

	
	
	
	/*
	 * Constructors
	 */
	public Client() {
		// code ..
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
	
}
