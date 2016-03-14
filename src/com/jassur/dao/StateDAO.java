package com.jassur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.State;

public class StateDAO extends DAO<State> {
	
	@Override
	public boolean create(State obj) {
		
		String sql = 
				"INSERT INTO states "+
				"(loan_id, user_id, label_state) "+
				"VALUES (?, ?, ?)";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(sql);
			statement.setInt(1, obj.getLoanId());
			statement.setInt(2, obj.getUserId());
			statement.setString(3, obj.getLabelState());
			
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(State obj) {
		return false;
	}

	@Override
	public boolean update(State obj) {
		return false;
	}

	@Override
	public State find(int id) {
		State state = null;
		
		try	{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT * FROM states WHERE id_state = "+id);
			
			if (result.first()) {
				state = new State();
				state.setId(id);
				state.setLabelState(result.getString("label_state"));
				state.setLoanId(result.getInt("loan_id"));
				state.setUserId(result.getInt("user_id"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return state;
	}

	@Override
	public ArrayList<State> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
