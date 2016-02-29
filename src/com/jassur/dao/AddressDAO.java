package com.jassur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jassur.model.Address;

public class AddressDAO extends DAO<Address> {

	@Override
	public boolean create(Address obj) {
		
		String sql = "INSERT INTO addresses "+
				"(street, city, country, region, zip, client_id) "+
				"VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(sql);
			
			statement.setString(1, obj.getStreet());
			statement.setString(2, obj.getCity());
			statement.setString(3, obj.getCountry());
			statement.setString(4, obj.getRegion());
			
			statement.setInt(5, obj.getZip());
			statement.setInt(6, obj.getClientId());
			
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
	public boolean delete(Address obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Address obj) {
		// TODO Auto-generated method stub
		return false;
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

}
