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
	private DAOFactory daoFactory = null;
	
	private String responseString = new String();

	public Dispatcher(DataOutputStream outputClient, PoolConnection pc) {
		this.dataOutputStream = outputClient;
		this.poolConnexion = pc;
		this.daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL_DAO_FACTORY);
	}

	public void analyze(String request) {
		
		String method = "";
		String route = "";
		JSONObject resource = null;
		
		JSONParser parser = new JSONParser();
		
		Object obj;
		
		try {
			obj = parser.parse(request);
			JSONObject jsonObj = (JSONObject)obj;
			
			method = (String) jsonObj.get("method");
			route = (String) jsonObj.get("route");
			
			if (jsonObj.containsKey("resource")) {
				resource = (JSONObject) jsonObj.get("resource");
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		switch (method) {
		case "GET":
			this.dealingGet(route);
			break;
		case "POST":
			this.dealingPost(route, resource);
			break;
		case "PUT":
			this.dealingPut(route, resource);
			break;
		case "DELETE":
			this.dealingDelete(route);
			break;
		default:
			System.out.println("Unknown method");
			break;
		}
		
	}
	
	private void dealingGet(String route) {
		
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);
		
		if (items.length == 1) {
			
			/*
			 * Client
			 */
			if (items[0].equals("clients")) {
				/* Get all clients and push them to a JSON array */
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				JSONArray array = new JSONArray();
				for (Model m : clientDAO.find()) {
					array.add(m.toJSON());
				}
				responseString = array.toJSONString();
			}
			
		} else if (items.length == 2) {
			
			/*
			 * Client
			 */
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
	
	private void dealingPost(String route, JSONObject resource) {
		
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);
		
		if (items.length == 1) {
			
			/*
			 * Client
			 */
			if (items[0].equals("clients")) {
				/* Get all clients and push them to a JSON array */
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				Client c = new Client();
				c.parseJSON(resource);
				
				System.out.println("Insert : "+c);
				c = clientDAO.create(c);
				
				if(c != null) {
					Model model = c;
					responseString = model.toJSON().toJSONString();
				} else {
					responseString = "[]"; // server error
				}
			}	
		}
		
		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dealingPut(String route, JSONObject resource) {
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);
		
		System.out.println("OK dealingPut");
		
		if (items.length == 2) {
			
			/*
			 * Client
			 */
			if (items[0].equals("clients")) {
				/* Get all clients and push them to a JSON array */
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				
				System.out.println("Client to find : "+Integer.parseInt(items[1]));
				
				Client c = clientDAO.find(Integer.parseInt(items[1]));
				
				System.out.println("Ok client");
				
				if (c != null) {
					
					c.parseJSON(resource);
					
					System.out.println(c);
					
					c = clientDAO.update(c);
					if(c != null) {
						Model model = c;
						responseString = model.toJSON().toJSONString();
					} else {
						responseString = "[]"; // server error
					}	
				}
			}	
		}
		
		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void dealingDelete(String route) {
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);
		
		if (items.length == 2) {
			
			/*
			 * Client
			 */
			if (items[0].equals("clients")) {
				/* Get all clients and push them to a JSON array */
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				Client c = clientDAO.find(Integer.parseInt(items[1]));
				System.out.println(c);
				if (c != null) {
					if (clientDAO.delete(c)) {
						System.out.println("Deleted");
					}
				}
				responseString = "[]";
			}	
		}
		
		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
