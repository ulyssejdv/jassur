/**
 * 
 */
package com.jassur.model;

import org.json.simple.JSONObject;

/**
 * @author ulysse
 *
 */
public class Category implements Model {
	
	public Category(int id, String labelCategory) {
		this.id = id;
		this.labelCategory = labelCategory;
	}

	public Category() {
		// TODO Auto-generated constructor stub
	}

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
	
	public JSONObject toJSON() {
		JSONObject jObj = new JSONObject();
		jObj.put("id_category", this.id);
		jObj.put("label_category", this.labelCategory);
		return jObj;
	}

	public void parseJSON(JSONObject jo) {
		this.id = (int) (long)jo.get("id_category");
		this.labelCategory = (String)jo.get("label_category");
	}
	

}
