package com.jassur.model;

import org.json.simple.JSONObject;

public interface Model {
	
	public JSONObject toJSON();
	
	public void parseJSON(JSONObject jo);

}
