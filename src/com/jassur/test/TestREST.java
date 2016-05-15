package com.jassur.test;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.message.Message;
import com.jassur.message.RequestBuilder;
import com.jassur.model.Client;
import com.jassur.model.Loan;

public class TestREST {

	public static void main(String[] args) {
		
		//testGetClients();
		
		testGetLoan(3);
		
	}
	
	/**
	 * Request testing clients
	 */
	public static void testGetClients() {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "clients/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get clients/ return a JSON array */
			JSONArray jArray = (JSONArray)obj;
			
			/* Analyze and instantiate all client in the JSON response */
			ArrayList<Client> clientList = new ArrayList<Client>();
			
			for(int i = 0; i < jArray.size(); i++) {
				JSONObject job = (JSONObject)jArray.get(i);				
				Client c = new Client();
				c.parseJSON(job);
				clientList.add(c);
			}
			
			/* Verify the client's list */
			for (Client client : clientList) {
				System.out.println(client.getFirstName()+" : "+client.getLastName());
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Request testing loan 
	 * 
	 * @param id ID of a Loan
	 */
	public static void testGetLoan(int id) {
		/* Build a new request */
		RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, "loans/"+id+"/");
		
		/* Send message with the builded request
		 * and get his response string 
		 */
		String resp = Message.execRequest(rb.toJSONString());
		
		try {
			/* Transformation of the response String in JSON */
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(resp);
			
			/* get laons/<id>/ return a JSONObject */
			JSONObject jo = (JSONObject)obj;
			
			System.out.println(jo);		
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
