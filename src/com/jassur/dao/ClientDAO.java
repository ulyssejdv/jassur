package com.jassur.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.Address;
import com.jassur.model.Client;
import com.mysql.jdbc.Statement;

public class ClientDAO extends DAO<Client> {


	@Override
	public Client create(Client obj) {
		
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
				
				if (obj.getAddress() != null) {
					if (generatedKeys.next()) {
						/* Get the generated id for the client and set it */
		                obj.setId(generatedKeys.getInt(1));
		                obj.getAddress().setClientId(obj.getId());
		                AddressDAO addressDAO = new AddressDAO();
		                addressDAO.setConnect(connect);
		                Address newAdd = addressDAO.create(obj.getAddress());
		                obj.setAddress(newAdd);
		            }
				}
				
				return obj;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean delete(Client obj) {
		
		String sql = "DELETE FROM clients WHERE id_client = "+obj.getId();
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
	public Client update(Client obj) {
		String sql = "UPDATE clients SET last_name = ?, first_name = ?, phone = ?, business = ?, email = ?, updated_at = ? "
				+"WHERE id_client = ?";
		System.out.println("SQL REQUEST : "+sql);
		
		System.out.println(obj);
		try {
	
			PreparedStatement stateUpdate = connect.prepareStatement(sql);
			stateUpdate.setString(1,obj.getLastName());
			stateUpdate.setString(2,obj.getFirstName());
			stateUpdate.setString(3,obj.getPhone());
			stateUpdate.setBoolean(4,obj.getBusiness());
			stateUpdate.setString(5,obj.getEmail());
			stateUpdate.setDate(6, new Date(System.currentTimeMillis()));
			stateUpdate.setInt(7,obj.getId());
			
			stateUpdate.executeUpdate();
			
			return obj;
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			return null;
		}
		
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
				    "FROM clients INNER JOIN addresses ON clients.id_client = addresses.client_id "+
					"WHERE clients.id_client = "+id+";");
			
			if (result.first()) {
				
				client = new Client();
				
				client.setId(id);
				client.setBusiness(result.getBoolean("business"));
				client.setEmail(result.getString("email"));
				client.setFirstName(result.getString("first_name"));
				client.setLastName(result.getString("last_name"));
				client.setPhone(result.getString("phone"));
				
				
				Address address = new Address();
				address.setCity(result.getString("city"));
				address.setStreet(result.getString("street"));
				address.setCountry(result.getString("country"));
				address.setRegion(result.getString("region"));
				address.setZip(result.getInt("zip"));
				
				client.setAddress(address);
			
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	@Override
	public ArrayList<Client> find() {
		ArrayList<Client> clients = new ArrayList<Client>();
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
					"SELECT * "+
				    "FROM clients INNER JOIN addresses ON clients.id_client = addresses.client_id ");
			
			if (result.first()) {
				
				while (result.next()) {
					Client client = new Client();
					
					client.setId(result.getInt("id_client"));
					client.setBusiness(result.getBoolean("business"));
					client.setEmail(result.getString("email"));
					client.setFirstName(result.getString("first_name"));
					client.setLastName(result.getString("last_name"));
					client.setPhone(result.getString("phone"));
					
					
					Address address = new Address();
					address.setCity(result.getString("city"));
					address.setStreet(result.getString("street"));
					address.setCountry(result.getString("country"));
					address.setRegion(result.getString("region"));
					address.setZip(result.getInt("zip"));
					
					client.setAddress(address);
					
					clients.add(client);
				}		
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clients;
	}

}
