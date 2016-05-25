package com.jassur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;

import com.mysql.jdbc.Statement;

public class LoanDAO extends DAO<Loan> {
	

	@Override
	public Loan create(Loan obj) {
		
		String sql = 
				"INSERT INTO loans "+
				"(client_id, category_id, amount, total_duration, total_amount) "+
				"VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, obj.getClient().getId());
			statement.setInt(2, obj.getCategory().getId());
			statement.setInt(3, obj.getAmount());
			statement.setInt(4, obj.getTotalDuration());
			statement.setDouble(5, obj.getTotalAmount());
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
	                obj.setId(generatedKeys.getInt(1));
	                
	                RateDAO rateDAO = new RateDAO();
	                rateDAO.setConnect(connect);
	                StateDAO stateDAO = new StateDAO();
	                stateDAO.setConnect(connect);
	                
	                for (Rate rate : obj.getRates()) {
						rate.setLoanId(obj.getId());
						rateDAO.create(rate);
					}
	                
	                for (State state : obj.getStates()) {
						state.setLoanId(obj.getId());
						stateDAO.create(state);
					}
	                
	            }
				
				return obj;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return obj;
	}

	@Override
	public boolean delete(Loan obj) {
		return false;
	}

	@Override
	public Loan update(Loan obj) {
		return obj;
	}

	@Override
	public Loan find(int id) {
		Loan loan = null;
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
					"SELECT * "+
				    "FROM loans LEFT JOIN rates ON loans.id_loan = rates.loan_id "+
					"LEFT JOIN states ON loans.id_loan = states.loan_id "+
				    "JOIN clients ON clients.id_client=loans.client_id "+
					"WHERE loans.id_loan = "+id+";");
			
			if (result.first()) {
				
				loan = new Loan();
				
				loan.setId(id);
				loan.setAmount(result.getInt("amount"));
				loan.setTotalAmount(result.getInt("total_amount"));
				loan.setTotalDuration(result.getInt("total_duration"));
				
				/* Get the category */
				 CategoryDAO categoryDAO = new CategoryDAO();
				 categoryDAO.setConnect(connect);
				 loan.setCategory(categoryDAO.find(result.getInt("category_id")));
				
				/* Get the client */
				 ClientDAO clientDAO = new ClientDAO();
				 clientDAO.setConnect(connect);
				 loan.setClient(clientDAO.find(result.getInt("client_id")));
				 
				/* Get all the rates */
				result.beforeFirst();
				RateDAO rateDAO = new RateDAO();
				rateDAO.setConnect(connect);
				
				while (result.next()) {
					loan.addRate(rateDAO.find(result.getInt("id_rate")));
					System.out.println("1er "+result);
				}
				
				/* Get all the states */
				result.beforeFirst();
				StateDAO stateDAO = new StateDAO();
				stateDAO.setConnect(connect);
				while (result.next()) {
				loan.addState(stateDAO.find(result.getInt("id_state")));
				System.out.println("2eme "+result.getInt("id_state"));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loan;
	}

	@Override
	public ArrayList<Loan> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
