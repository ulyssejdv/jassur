package com.jassur.dao;

import java.sql.Connection;

public abstract class DAO<T> {
	
	protected Connection connect = null;
	
	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}
	
	/*public DAO(Connection conn) {
		this.connect = conn;
	}*/
	
	

	/**
	 * Insert object into DB
	 * @param obj
	 * @return
	 */
	public abstract boolean create(T obj);
	
	/**
	 * Delete object of the DB
	 * @param obj
	 * @return
	 */
	public abstract boolean delete(T obj);
	
	/**
	 * Update object of the DB
	 * @param obj
	 * @return
	 */
	public abstract boolean update(T obj);
	
	/**
	 * Get the object in the DB with the specified ID
	 * @param id
	 * @return
	 */
	public abstract T find(int id);
}
