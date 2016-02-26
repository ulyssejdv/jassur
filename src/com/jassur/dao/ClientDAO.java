package com.jassur.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jassur.model.Client;
import com.jassur.model.Rate;
import com.jassur.model.State;
import com.mysql.jdbc.Statement;

public class ClientDAO extends DAO<Client> {

	public ClientDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Client obj) {
		String sql = 
				"INSERT INTO client "+
				"(last_name, first_name, phone, email, business) "+
				"VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, obj.getId());
			statement.setInt(2, obj.getCategory().getId());
			statement.setInt(3, obj.getAmount());
			statement.setInt(4, obj.getTotalDuration());
			
			statement.setDouble(5, obj.getTotalAmount());
			
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
	                obj.setId(generatedKeys.getInt(1));
	                
	                RateDAO rateDAO = new RateDAO(this.connect);
	                StateDAO stateDAO = new StateDAO(this.connect);
	                
	                for (Rate rate : obj.getRates()) {
						rate.setLoanId(obj.getId());
						rateDAO.create(rate);
					}
	                
	                for (State state : obj.getStates()) {
						state.setLoanId(obj.getId());
						stateDAO.create(state);
					}
	                
	            }
				
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Client obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Client find(int id) {
		Client client = null;
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
					"SELECT * "+
				    "FROM clients INNER JOIN addresses ON clients.address_id = addresses.id_address "+
					"WHERE clients.id_client = "+id+";");
			
			if (result.first()) {
				
				client = new Client();
				
				client.setId(id);
				client.setBusiness(result.getBoolean("business"));
				client.setEmail(result.getString("email"));
				client.setFirstName(result.getString("first_name"));
				client.setLastName(result.getString("last_name"));
				client.setPhone(result.getString("phone"));
				
				/* Get the category */
				AddressDAO addressDAO = new AddressDAO(this.connect);
				client.setAddress(addressDAO.find(result.getInt("address_id")));
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

}
