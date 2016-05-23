package com.jassur.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.LoanVariable;
import com.jassur.model.Model;
import com.jassur.view.BaseGUI;
import com.jassur.view.LoanCardPanel;
import com.jassur.view.LoanShowPanel;
import com.jassur.view.LoanVariableRateCardPanel;
import com.jassur.view.LoanVariableShowPanel;

public class LoanVariableController implements Controller{

	@Override
	public void indexAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showAction(int id) {
		/* Build a new request */
		RequestBuilder rb1 = new RequestBuilder(RequestBuilder.GET, "loansVariable/"+id);
		
		String resp1 = Message.execRequest(rb1.toJSONString());
		LoanVariable lvc = new LoanVariable();
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj1 = parser.parse(resp1);
			
			System.out.println(resp1);
			
			/* get loans/ return a JSON array */
			JSONObject jObject = (JSONObject)obj1;
			
			lvc.parseJSON(jObject);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		/* Render the client card panel */
		LoanVariableRateCardPanel lvs = new LoanVariableRateCardPanel(lvc);
		BaseGUI.render(lvs);
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
