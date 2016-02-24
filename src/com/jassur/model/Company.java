/**
 * 
 */
package com.jassur.model;

import org.json.simple.JSONObject;

/**
 * @author ulysse
 *
 */
public class Company extends Model {
	
	/*
	 * Has one
	 */
	private Address address;
	private Client client;
	
	/*
	 * Attributes
	 */
	private String name;
	

	
	/* (non-Javadoc)
	 * @see model.Model#insert()
	 */
	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#get()
	 */
	@Override
	public void get() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.Model#update()
	 */
	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#delete()
	 */
	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#toJSON()
	 */
	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.Model#parseJSON(java.lang.String)
	 */
	@Override
	public void parseJSON(String json) {
		// TODO Auto-generated method stub

	}

}
