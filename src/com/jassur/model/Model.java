package com.jassur.model;

import java.sql.Connection;
import java.util.Date;

import org.json.simple.JSONObject;

public abstract class Model {
	
	protected String primaryKey = null;
	
	protected String tableName = null;
	
	protected int id;
	
	protected Date createdAt;
	
	protected Date updatedAt;
	
	protected Connection connection = null;
	
	
	
	/* CRUD PART */
	public abstract int insert();
	
	public abstract void get();
	
	public abstract int update();
	
	public abstract int delete();
	

	
	/* Do you speak JSON ? */
	public abstract JSONObject toJSON();
	
	public abstract void parseJSON(String json);
	
	
	
	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
