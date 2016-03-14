package com.jassur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.Address;
import com.mysql.jdbc.Statement;

public class AddressDAO extends DAO<Address> {

	@Override
	public Address create(Address obj) {
		
		String sql = "INSERT INTO addresses "+
				"(street, city, country, region, zip, client_id) "+
				"VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, obj.getStreet());
			statement.setString(2, obj.getCity());
			statement.setString(3, obj.getCountry());
			statement.setString(4, obj.getRegion());
			
			statement.setInt(5, obj.getZip());
			statement.setInt(6, obj.getClientId());
			
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					obj.setId(generatedKeys.getInt(1));
					return obj;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean delete(Address obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address update(Address obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Address find(int id) {
		Address address = null;
		
		try	{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT * FROM addresses WHERE id_address = "+id);
			
			if (result.first()) {
				address = new Address();
				address.setId(id);
				address.setCity(result.getString("city"));
				address.setCountry(result.getString("country"));
				address.setRegion(result.getString("region"));
				address.setStreet(result.getString("street"));
				address.setZip(result.getInt("zip"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	@Override
	public ArrayList<Address> find() {
		// TODO Auto-generated method stub
		return null;
	}

}
