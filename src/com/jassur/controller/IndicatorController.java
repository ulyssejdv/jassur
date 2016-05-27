package com.jassur.controller;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.dao.LoanDAO;
import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Address;
import com.jassur.model.Client;
import com.jassur.model.Indicator;
import com.jassur.model.Loan;
import com.jassur.model.Model;
import com.jassur.model.Rate;
import com.jassur.view.BaseGUI;
import com.jassur.view.IndicatorPanel;
import com.jassur.view.LoanFixedRateSimulationPanel;
import com.jassur.view.LoanShowPanel;

public class IndicatorController implements Controller{

	@Override
	public void indexAction() {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "loans/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		ArrayList<Loan> loan = new ArrayList<Loan>();
		//LoanDAO id = new LoanDAO();
		//loan = id.find();
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all loans in the JSON response */
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Loan l = new Loan();
				l.parseJSON(job);
				loan.add(l);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Indicator indicator=new Indicator();
	    int simulation=0;
	    	for (Loan l : loan) 
	    		if (l.getStates().get(0).getLabelState().equals("Simulation"))
	    		simulation++;

	    	indicator.setNbSimulation(simulation);

	    int en_cours=0;
	    	for (Loan l : loan) 
	    		if (l.getStates().get(0).getLabelState().equals("En Cours"))
	    		en_cours++;

	    	indicator.setNbEnCours(en_cours);

	    int nb=0;
	    int duree=0;
	    	for (Loan l : loan) {
	    		nb++;
	    		duree+=l.getTotalDuration();

	    	}
	    	indicator.setAvgDurationLoan(duree/nb);
	    	
	    	int interest=0;
	    	for (Loan l : loan) {
	    	
	    	interest += l.getTotalAmount() - l.getAmount();

	    	}
	   		    indicator.setSumInterest(interest);


	    int nb2=0;
	    int amount=0;
	    	for (Loan l : loan) {
	    		nb2++;
	    		amount+= l.getTotalAmount();

	    		    indicator.setAvgLoansAmount(amount/nb2);
	    		
	    }
	    	
	    	int amountLoan=0;
	    	for (Loan l : loan) {
	    		amountLoan++;
	    		
	    	
	    	indicator.setNbLoans(amountLoan);
	}
		    
			/* Render the indicator panel */
	    	IndicatorPanel ip= new IndicatorPanel(indicator);
	    	BaseGUI.render(ip);
	}

	

	@Override
	public void newAction() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Controller method for display loan form
	 * @param idClient
	 */
	public void newAction(Indicator indicator) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "loan/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		ArrayList<Loan> loanList = new ArrayList<Loan>();
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get indicators/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all indicators in the JSON response */
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Loan l = new Loan();
				l.parseJSON(job);
				loanList.add(l);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		IndicatorPanel ip = new IndicatorPanel(indicator);
		BaseGUI.render(ip);
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
			
			/* get indicators/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all indicators in the JSON response */
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

	@Override
	public void showAction(int id) {
		// TODO Auto-generated method stub
		
	}

}
