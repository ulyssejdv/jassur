package com.jassur.message;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jassur.dao.DAO;
import com.jassur.dao.DAOFactory;
import com.jassur.database.PoolConnection;
import com.jassur.model.Client;
import com.jassur.model.Model;

public class Dispatcher {
	
	private DataOutputStream dataOutputStream = null;
	private PoolConnection poolConnexion = null;

	public Dispatcher(DataOutputStream outputClient, PoolConnection pc) {
		this.dataOutputStream = outputClient;
		this.poolConnexion = pc;
	}

	public void analyze(String request) {
		
		String method = "";
		String route = "";
		
		JSONParser parser = new JSONParser();
		
		Object obj;
		
		try {
			obj = parser.parse(request);
			JSONObject jsonObj = (JSONObject)obj;
			
			method = (String) jsonObj.get("method");
			route = (String) jsonObj.get("route");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println("Method : "+method);
		System.out.println("Route : "+route);
		
		switch (method) {
		case "GET":
			this.dealingGet(route);
			break;
		case "POST":
			//this.dealingPost(request);
			break;
		case "PUT":
			//this.dealingPut(request);
			break;
		case "DELETE":
			//this.dealingDelete(request);
			break;
		default:
			break;
		}
		
	}
	
	private void dealingGet(String route) {
		
		System.out.println("Deal with GET request");
		
		DAOFactory daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL_DAO_FACTORY);
		
		String responseString = new String();
		
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);
		
		System.out.println("Number of items in request : "+items.length);
		
		if (items.length == 1) {
			System.out.println("Item : "+items[0]);
			if (items[0].equals("clients")) {
				// get all the clients 
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				JSONArray array = new JSONArray();
				for (Model m : clientDAO.find()) {
					array.add(m.toJSON());
				}
				responseString = array.toJSONString();
			}
		} else if (items.length == 2) {
			if (items[0].equals("clients")) {
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				Model model = clientDAO.find(Integer.parseInt(items[1]));
				responseString = model.toJSON().toJSONString();
			}
		}
		
		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*private void dealingPost(String message) {
		
		Connection conn = this.poolConnexion.pop().getConnection(); 
		Integer res = new Integer(0);
		switch (message.getResourceType()) {
		case "pret":
			System.out.println("detect resource : pret");
			Pret p = new Pret();
			p.parseXML(message.getFullMsgString());
			res = p.insert(conn);
			break;

		default:
			break;
		}
		
		try {
			dataOutputStream.writeBytes("<message><code>"+res.toString()+"</code></message>"+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(message.toString());
	}
	
	private void dealingPut(String message) {
		
	}
	
	private void dealingDelete(String message) {
		
		System.out.println(message.toString());
		Connection conn = this.poolConnexion.pop().getConnection();
		
		switch (message.getResourceType()) {
		case "pret":
			//Integer res = Pret.deletePret(this.poolConnexion.pop().getConnection(), message.getResourceId());
			
			Pret p = new Pret();
			p.setIdPret(message.getResourceId());
			Integer res = p.delete(conn);
			
			try {
				dataOutputStream.writeBytes("<message><code>"+res.toString()+"</code></message>"+'\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			break;

		default:
			break;
		}
		
		System.out.println(message.toString());
	}*/
}
