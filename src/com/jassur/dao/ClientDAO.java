package com.jassur.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jassur.model.Client;
import com.mysql.jdbc.Statement;

public class ClientDAO extends DAO<Client> {


	@Override
	public boolean create(Client obj) {
		
		String sql = 
				"INSERT INTO clients "+
				"(last_name, first_name, phone, email, business, created_at) "+
				"VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, obj.getLastName());
			statement.setString(2, obj.getFirstName());
			statement.setString(3, obj.getPhone());
			statement.setString(4, obj.getEmail());
			statement.setBoolean(5, obj.getBusiness());
			statement.setDate(6, new Date(System.currentTimeMillis()));
			
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					/* Get the generated id for the client and set it */
	                obj.setId(generatedKeys.getInt(1));
	                obj.getAddress().setClientId(obj.getId());
	                AddressDAO addressDAO = new AddressDAO();
	                addressDAO.setConnect(connect);
	                addressDAO.create(obj.getAddress());
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
				AddressDAO addressDAO = new AddressDAO();
				client.setAddress(addressDAO.find(result.getInt("address_id")));
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

}
