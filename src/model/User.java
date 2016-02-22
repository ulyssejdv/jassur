/**
 * 
 */
package model;

import org.json.simple.JSONObject;

/**
 * @author ulysse
 *
 */
public class User extends Model {
	
	
	/*
	 * Attributes
	 */
	private String password;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	
	/* (non-Javadoc)
	 * @see model.Model#insert()
	 */
	@Override
	public int insert() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#get()
	 */
	@Override
	public void get() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see model.Model#update()
	 */
	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#delete()
	 */
	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see model.Model#toJSON()
	 */
	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see model.Model#parseJSON(java.lang.String)
	 */
	@Override
	public void parseJSON(String json) {
		// TODO Auto-generated method stub

	}

}
