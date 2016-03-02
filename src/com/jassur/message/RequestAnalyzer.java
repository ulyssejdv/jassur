package com.jassur.message;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RequestAnalyzer {
	private String fullRequest;
	
	public RequestAnalyzer(String request) {
		this.fullRequest = request;
	}
	
	private void analyze() {
		
		JSONParser parser = new JSONParser();
		
		Object obj;
		
		try {
			obj = parser.parse(this.fullRequest);
			JSONObject jsonObj = (JSONObject)obj;
			
			jsonObj.get("method");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        
	}

}
