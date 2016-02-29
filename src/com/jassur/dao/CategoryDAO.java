package com.jassur.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jassur.model.Category;

public class CategoryDAO extends DAO<Category> {


	@Override
	public boolean create(Category obj) {
		return false;
	}

	@Override
	public boolean delete(Category obj) {
		return false;
	}

	@Override
	public boolean update(Category obj) {
		return false;
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

}
