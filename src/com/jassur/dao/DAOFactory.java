package com.jassur.dao;

import com.jassur.model.Address;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;

public abstract class DAOFactory {
	
	public static final int MYSQL_DAO_FACTORY = 0;
	public static final int JSON_DAO_FACTORY = 1;
	
	public abstract DAO<Loan> getLoanDAO();
	
	public abstract DAO<Address> getAddressDAO();
	
	public abstract DAO<Category> getCategoryDAO();
	
	public abstract DAO<Client> getClientDAO();
	
	public abstract DAO<Rate> getRateDAO();
	
	public abstract DAO<State> getStateDAO();
	
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
