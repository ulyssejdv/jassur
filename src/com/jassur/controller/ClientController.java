package com.jassur.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;

import com.jassur.model.Address;
import com.jassur.model.Client;
import com.jassur.model.Model;

import com.jassur.view.ClientCardPanel;
import com.jassur.view.ClientFormPanel;
import com.jassur.view.ClientListPanel;
import com.jassur.view.MainFrame;

public class ClientController implements Controller{
	
	public void indexAction() {
		
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "clients/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get clients/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all client in the JSON response */
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Client c = new Client();
				c.parseJSON(job);
				clientList.add(c);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/* Render the client list panel */
		ClientListPanel cl = new ClientListPanel(clientList);
		//MainFrame.getInstance().render(cl);
		MainFrame.getInstance(cl);
	}
	
	public void showAction(int id) {
		
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "clients/"+id);
		
		String resp = Message.execRequest(rb.toJSONString());
		Client c = new Client();
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get clients/ return a JSON array */
			JSONObject jObject = (JSONObject)obj;
			
			c.parseJSON(jObject);
			
		} catch (ParseException e) {
			
		}
		
		/* Render the client card panel */
		ClientCardPanel ccp = new ClientCardPanel(c);
		//MainFrame.getInstance().render(ccp);
		MainFrame.getInstance(ccp);
	}
	
	public void newAction() {
		Client c = new Client();
		c.setAddress(new Address());
		ClientFormPanel cfp = new ClientFormPanel(c);
		// MainFrame.getInstance().render(cfp);
		MainFrame.getInstance(cfp);
	}
	
	public void createAction(Model m) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "clients/", m);
		String resp = Message.execRequest(rb.toJSONString());
		indexAction();
	}
	
	public void editAction(int id) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "clients/"+id);
		
		String resp = Message.execRequest(rb.toJSONString());
		Client c = new Client();
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get clients/ return a JSON array */
			JSONObject jObject = (JSONObject)obj;
			
			c.parseJSON(jObject);
			
		} catch (ParseException e) {
			
		}
		
		/* Render the client card panel */
		ClientFormPanel ccp = new ClientFormPanel(c);
		//MainFrame.getInstance().render(ccp);
		MainFrame.getInstance(ccp);
	}
	
	public void updateAction(Client input) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.PUT, "clients/"+input.getId()+"/", input);
		String resp = Message.execRequest(rb.toJSONString());
		indexAction();
	}
	
	public void destroyAction(int id) {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.DELETE, "clients/"+id+"/");
		String resp = Message.execRequest(rb.toJSONString());
		indexAction();
	}
	
}
