package com.jassur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.Rate;

public class RateDAO extends DAO<Rate> {

	@Override
	public Rate create(Rate obj) {
		String sql = 
				"INSERT INTO rates "+
				"(loan_id, interest_rate, duration, monthly_payment) "+
				"VALUES (?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(sql);
			
			statement.setInt(1, obj.getLoanId());
			statement.setDouble(2, obj.getInterestRate());
			statement.setInt(3, obj.getDuration());
			statement.setDouble(4, obj.getMonthlyPayment());
			
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				return obj;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean delete(Rate obj) {
		return false;
	}

	@Override
	public Rate update(Rate obj) {
		return null;
	}

	@Override
	public Rate find(int id) {
		Rate rate = null;
		
		try	{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT * FROM rates WHERE id_rate = "+id);
			
			if (result.first()) {
				rate = new Rate();
				rate.setId(id);
				rate.setDuration(result.getInt("duration"));
				rate.setInterestRate(result.getDouble("interest_rate"));
				rate.setMonthlyPayment(result.getDouble("monthly_payment"));
				rate.setLoanId(result.getInt("loan_id"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rate;
	}

	@Override
	public ArrayList<Rate> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
