package com.jassur.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONObject;


public class Client extends Model {
	
	/*
	 * Has one
	 */
	private Address address;
	
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
	
	}
	

	@Override
	public int insert() {
		int id_address = this.address.insert();
		
		return 0;
	}

	@Override
	public void get() {

	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void parseJSON(String json) {
		// TODO Auto-generated method stub
		
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


	public boolean isBusiness() {
		return business;
	}


	public void setBusiness(boolean business) {
		this.business = business;
	}
	
}
