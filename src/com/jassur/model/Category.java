/**
 * 
 */
package com.jassur.model;

/**
 * @author ulysse
 *
 */
public class Category {
	
	private int id = 0;
	
	private String labelCategory;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabelCategory() {
		return labelCategory;
	}

	public void setLabelCategory(String labelCategory) {
		this.labelCategory = labelCategory;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", label=" + labelCategory + "]";
	}
	
	

}
