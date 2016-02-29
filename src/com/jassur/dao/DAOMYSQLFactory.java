package com.jassur.dao;

import com.jassur.model.Address;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;

public class DAOMYSQLFactory extends DAOFactory {
	
	@Override
	public DAO<Loan> getLoanDAO() {
		return new LoanDAO();
	}

	@Override
	public DAO<Address> getAddressDAO() {
		return new AddressDAO();
	}

	@Override
	public DAO<Category> getCategoryDAO() {
		return new CategoryDAO();
	}

	@Override
	public DAO<Client> getClientDAO() {
		return new ClientDAO();
	}

	@Override
	public DAO<Rate> getRateDAO() {
		return new RateDAO();
	}

	@Override
	public DAO<State> getStateDAO() {
		return new StateDAO();
	}

}
