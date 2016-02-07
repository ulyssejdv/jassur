package model;

import java.sql.Connection;

public abstract class Model {
	
	protected String primaryKey = null;
	
	protected String tableName = null;
	
	
	/* Intercation with DB */
	public abstract int insert(Connection conn);
	
	public abstract void get(Connection conn, int id);
	
	public abstract int update(Connection conn);
	
	public abstract int delete(Connection con);
	
	
	/* Do you speak XML ? */
	public abstract String toXML();
	
	public abstract void parseXML(String xml);
}
