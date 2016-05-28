
package com.jassur.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.newRate;
/**
 * @author peyth_000
 *
 */
public class newRateDAO extends DAO<newRate> {

	@Override
	public newRate create(newRate obj) {
		String sql = 
				"INSERT INTO newrates "+
				"(id_profile, age,duration,job,healthy, ratecompany,newRate , risk) "+
				"VALUES (?,?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			PreparedStatement statement = connect.prepareStatement(sql);
			
			statement.setInt(1, obj.getProfileId());
			statement.setInt(2, obj.getAge());
			statement.setInt(3, obj.getDuration());
			statement.setString(4, obj.getJob());
			statement.setBoolean(5, obj.gethealthy());
			statement.setDouble(6, obj.getRateCompany());
			statement.setDouble(7, obj.getNewRate());
			statement.setString(8, obj.getRisk());
			
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
	public ArrayList<newRate> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(newRate obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public newRate update(newRate obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public newRate find(int id) {
		// TODO Auto-generated method stub
newRate rate = null;
		
		try	{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT * FROM newrates WHERE id_profile = "+id);
			
			if (result.first()) {
				rate = new newRate();
				rate.setProfileId(id);
				rate.setDuration(result.getInt("duration"));
				rate.setAge(result.getInt("Age"));
				rate.setJob(result.getString("job"));
				rate.sethealthy(result.getBoolean("healthy"));
				rate.setRateCompany(result.getDouble("rateCompany"));
				rate.setNewRate(result.getDouble("newRate"));
				rate.setRisk(result.getString("risk"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rate;
		
	}

}
