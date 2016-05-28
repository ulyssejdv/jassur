package com.jassur.dao;

public class DAOMYSQLFactory extends DAOFactory {
	
	@Override
	public DAO getLoanDAO() {
		return new LoanDAO();
	}

	@Override
	public DAO getAddressDAO() {
		return new AddressDAO();
	}

	@Override
	public DAO getCategoryDAO() {
		return new CategoryDAO();
	}

	@Override
	public DAO getClientDAO() {
		return new ClientDAO();
	}

	@Override
	public DAO getRateDAO() {
		return new RateDAO();
	}

	@Override
	public DAO getStateDAO() {
		return new StateDAO();
	}

	@Override
	public DAO getNewRateDAO() {
		return new newRateDAO();
	}

}
