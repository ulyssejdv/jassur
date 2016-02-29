/**
 * 
 */
package com.jassur.model;

/**
 * @author ulysse
 *
 */
public class Company {
	
	/*
	 * Has one
	 */
	private Address address;
	private Client client;
	
	/*
	 * Attributes
	 */
	private String name;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
