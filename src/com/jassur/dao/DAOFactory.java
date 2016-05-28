package com.jassur.dao;

public abstract class DAOFactory {
	
	public static final int MYSQL_DAO_FACTORY = 0;
	public static final int JSON_DAO_FACTORY = 1;
	
	public abstract DAO getLoanDAO();
	
	public abstract DAO getAddressDAO();
	
	public abstract DAO getCategoryDAO();
	
	public abstract DAO getClientDAO();
	
	public abstract DAO getRateDAO();
	
	public abstract DAO getNewRateDAO();
	
	public abstract DAO getStateDAO();
	
	public static DAOFactory getFactory(int type) {
		switch (type) {
		case MYSQL_DAO_FACTORY:
			return new DAOMYSQLFactory();
		case JSON_DAO_FACTORY:
			return new DAOJSONFactory();
		default:
			return null;
		}
	}
}
