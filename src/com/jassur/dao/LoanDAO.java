package com.jassur.dao;

import java.sql.Date;
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
				"(client_id, category_id, amount, total_duration, total_amount, created_at) "+
				"VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, obj.getClient().getId());
			statement.setInt(2, obj.getCategory().getId());
			statement.setInt(3, obj.getAmount());
			statement.setInt(4, obj.getTotalDuration());
			statement.setDouble(5, obj.getTotalAmount());
			statement.setDate(6, new Date(new java.util.Date().getTime()));
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
		String sql = "DELETE l.*,r.*,s.* FROM loans l "
				+ "JOIN rates r ON l.id_loan = r.loan_id "
				+ "JOIN states s ON l.id_loan = s.loan_id "
				+ "WHERE l.id_loan = "+obj.getId()+";";
		System.out.println("SQL REQUEST : "+sql);
		try {
			int result = this.connect.createStatement().executeUpdate(sql);
			
			if (result > 0) {
				return true;
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Loan update(Loan obj) {
		String sql = "UPDATE loans l "
				+ "JOIN rates r ON l.id_loan = r.loan_id "
				+ "SET l.category_id= ? ,l.amount= ? , l.total_duration= ? ,"
				+ "l.total_amount = ?, r.interest_rate = ?, r.duration = ? ,"
				+ "r.monthly_payment = ?"
				+ " WHERE l.id_loan = "+obj.getId()+""
						+ ";";
		System.out.println("SQL REQUEST : "+sql);
		
		System.out.println(obj);
		try {
	
			PreparedStatement stateUpdate = connect.prepareStatement(sql);
			stateUpdate.setInt(1,obj.getCategory().getId());
			stateUpdate.setInt(2, obj.getAmount());
			stateUpdate.setInt(3, obj.getTotalDuration());
			stateUpdate.setDouble(4, obj.getTotalAmount());
			stateUpdate.setDouble(5,obj.getRates().get(0).getInterestRate());
			stateUpdate.setInt(6, obj.getRates().get(0).getDuration());
			stateUpdate.setDouble(7, obj.getRates().get(0).getMonthlyPayment());
			System.out.println("SQL REQUEST : "+sql);
			
			System.out.println(stateUpdate.toString());
			stateUpdate.executeUpdate();
			
			return obj;
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return null;
		}
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
				loan.setCreatedAt(result.getDate("created_at"));
				
				/* Get the category */
				 CategoryDAO categoryDAO = new CategoryDAO();
				 categoryDAO.setConnect(connect);
				 loan.setCategory(categoryDAO.find(result.getInt("category_id")));

				
				/* Get the client */
				 ClientDAO clientDAO = new ClientDAO();
				 clientDAO.setConnect(connect);
				 loan.setClient(clientDAO.find(result.getInt("client_id")));
				 loan.setClientId(result.getInt("client_id"));
			
				/* Get all the rates */
				result.beforeFirst();
				RateDAO rateDAO = new RateDAO();
				rateDAO.setConnect(connect);
				
				while (result.next()) {
					loan.addRate(rateDAO.find(result.getInt("id_rate")));
				}
				
				/* Get all the states */
				result.beforeFirst();
				StateDAO stateDAO = new StateDAO();
				stateDAO.setConnect(connect);
				while (result.next()) {
				loan.addState(stateDAO.find(result.getInt("id_state")));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loan;
	}

	@Override
	public ArrayList<Loan> find() {
		ArrayList<Loan> loans = new ArrayList<Loan>();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
					"SELECT distinct id_loan "+
				    "FROM loans;");
			
			//if(result.first()){
				LoanDAO ld =new LoanDAO();
				ld.setConnect(connect);
				while (result.next()) {
					int loan_id=result.getInt("id_loan");
					loans.add(ld.find(loan_id));
				}
			//}

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;
	}

}
