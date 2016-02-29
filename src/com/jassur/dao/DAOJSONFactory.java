package com.jassur.dao;

import com.jassur.model.Address;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;

public class DAOJSONFactory extends DAOFactory {

	@Override
	public DAO<Loan> getLoanDAO() {
		return null;
	}

	@Override
	public DAO<Address> getAddressDAO() {
		return null;
	}

	@Override
	public DAO<Category> getCategoryDAO() {
		return null;
	}

	@Override
	public DAO<Client> getClientDAO() {
		return null;
	}

	@Override
	public DAO<Rate> getRateDAO() {
		return null;
	}

	@Override
	public DAO<State> getStateDAO() {
		return null;
	}

}
