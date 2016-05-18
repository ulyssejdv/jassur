package com.jassur.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Model;
import com.jassur.view.BaseGUI;
import com.jassur.view.LoanCardPanel;
import com.jassur.view.LoanShowPanel;

public class LoanController implements Controller{

	@Override
	public void indexAction() {
		// TODO Auto-generated method stub
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
			
			System.out.println(resp);
			
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

}
