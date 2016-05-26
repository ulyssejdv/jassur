package com.jassur.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Address;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Model;
import com.jassur.view.BaseGUI;
import com.jassur.view.LoanFixedRateSimulationPanel;
import com.jassur.view.LoanShowPanel;

public class LoanController implements Controller{

	@Override
	public void indexAction() {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "categories/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get categories/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all categories in the JSON response */
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Category c = new Category();
				c.parseJSON(job);
				categoryList.add(c);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
			Client client=new Client();
		    client.setId(13);
		    client.setFirstName("a");
		    client.setLastName("b");
		    client.setPhone("0667939393");
		    client.setEmail("r@a.fr");
		    client.setBusiness(false);
		    client.setAddress(new Address());
		    
		    System.out.println(this.showClientLoans(client).size());
			/* Render the client list panel */
			LoanFixedRateSimulationPanel lfrsp = new LoanFixedRateSimulationPanel(categoryList,client);
			BaseGUI.render(lfrsp);	
		}


	@Override
	public void showAction(int id) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "loans/"+id);
		
		String resp = Message.execRequest(rb.toJSONString());
		Loan l = new Loan();
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
						
			/* get loans/ return a JSON array */
			JSONObject jObject = (JSONObject)obj;
			
			l.parseJSON(jObject);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/* Render the client card panel */
		LoanShowPanel lsp = new LoanShowPanel(l);
		BaseGUI.render(lsp);
	}

	@Override
	public void newAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createAction(Model m) {
		// TODO Auto-generated method stub
		
	}
	
	public void createSimulation(Model m){
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "loans/", m);
		String resp = Message.execRequest(rb.toJSONString());
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(resp);
			JSONObject jObject = (JSONObject)obj;
			Loan l= new Loan();
			l.parseJSON(jObject);
			
			LoanController lc = new LoanController();
			lc.showAction(l.getId());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void editAction(int id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void updateAction(Client input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroyAction(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Loan> showClientLoans(Client client){
RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "loans/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		ArrayList<Loan> loanList = new ArrayList<Loan>();
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get categories/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all categories in the JSON response */
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Loan l = new Loan();
				l.parseJSON(job);
				if(l.getClient().getId()==client.getId())
				loanList.add(l);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return loanList;
		
	}

}
