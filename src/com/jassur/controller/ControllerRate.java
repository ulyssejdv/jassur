package com.jassur.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Client;
import com.jassur.model.Model;
import com.jassur.model.newRate;
import com.jassur.view.BaseGUI;
import com.jassur.view.MainFrame;

public class ControllerRate implements Controller{
	
	public void indexAction() {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "newRate/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		
		ArrayList<newRate> rateList = new ArrayList<newRate>();
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get rates/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all rates in the JSON response */
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				newRate c = new newRate();
				c.parseJSON(job);
				rateList.add(c);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/* Render the t list panel */
		//newRateListPanel cl = new newRateListPanel(RateList);
		//BaseGUI.render(cl); */
	}
	
	public void showAction(int id) {
		
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "newRate/"+id);
		
		String resp = Message.execRequest(rb.toJSONString());
		newRate c = new newRate();
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get ts/ return a JSON array */
			JSONObject jObject = (JSONObject)obj;
			
			c.parseJSON(jObject);
			
		} catch (ParseException e) {
			
		}
		
		/* Render the t card panel */
		//pas encore fait
	}
	
	public void newAction() {
		
	}
	
	public void createAction(Model m) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "newRate/", m);
		String resp = Message.execRequest(rb.toJSONString());
		
	}
	
	public void editAction(int id) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "newRate/"+id);
		
		String resp = Message.execRequest(rb.toJSONString());
		newRate c = new newRate();
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get ts/ return a JSON array */
			JSONObject jObject = (JSONObject)obj;
			
			c.parseJSON(jObject);
			
		} catch (ParseException e) {
			
		}
		
		/* Render the rate card panel */
		//newRateFormPanel ccp = new newRateFormPanel(c);
		//BaseGUI.render(ccp);
	}
	
	
	
	public void destroyAction(int id) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.DELETE, "newRate/"+id+"/");
		String resp = Message.execRequest(rb.toJSONString());
		indexAction();
	}

	
	public void updateAction(newRate input) {
		// TODO Auto-generated method stub
		RequestBuilder rb = new RequestBuilder(RequestBuilder.PUT, "newRate/"+input.getProfileId()+"/", input);
		String resp = Message.execRequest(rb.toJSONString());
		indexAction();
	}

	@Override
	public void updateAction(Client input) {
		// TODO Auto-generated method stub
		
	}

	


}

