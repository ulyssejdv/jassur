package com.jassur.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.jassur.model.Address;
import com.jassur.model.Category;
import com.jassur.model.Client;

public class CategoryDAO extends DAO<Category> {


	@Override
	public Category create(Category obj) {
		return null;
	}

	@Override
	public boolean delete(Category obj) {
		return false;
	}

	@Override
	public Category update(Category obj) {
		return null;
	}

	@Override
	public Category find(int id) {
		Category category = null;
		
		try	{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT * FROM categories WHERE id_category = "+id);
			
			if (result.first()) {
				category = new Category();
				category.setId(id);
				category.setLabelCategory(result.getString("label_category"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public ArrayList<Category> find() {
		ArrayList<Category> categories = new ArrayList<Category>();
			
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
						"SELECT * "+
					    "FROM categories");
				
		
				while (result.next()) {
					Category category = new Category();
					category.setId(result.getInt("id_category"));
					category.setLabelCategory(result.getString("label_category"));
					categories.add(category);
				}		

		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return categories;	
	}

}
