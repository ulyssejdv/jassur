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
import com.jassur.database.ConfigurationDB;
import com.jassur.database.Connexion;
import com.jassur.database.PoolConnection;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Model;
import com.jassur.model.Model_Manage_id;
import com.jassur.model.Model_search_loan;
import com.jassur.model.Model_table_loan;
import com.jassur.model.newRate;

public class Dispatcher extends Thread {

	private DataOutputStream dataOutputStream = null;
	private PoolConnection poolConnexion = null;
	private DAOFactory daoFactory = null;

	private String responseString = new String();

	private String request;

	public Dispatcher(DataOutputStream outputClient, PoolConnection pc, String request) {
		this.dataOutputStream = outputClient;
		this.poolConnexion = pc;
		this.daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL_DAO_FACTORY);
		this.request = request;
	}

	public void run() {
		try {
			this.analyze(this.request);
		} catch (BadRequestException bre) {
			try {
				String resp = "{error: \""+bre.getMessage()+"\"}";
				dataOutputStream.writeBytes(responseString+'\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public void analyze(String request) throws BadRequestException {

		String method = "";
		String route = "";
		JSONObject resource = null;

		JSONParser parser = new JSONParser();

		Object obj;

		try {
			obj = parser.parse(request);
			JSONObject jsonObj = (JSONObject)obj;


			if (jsonObj.containsKey("method")) {
				method = (String) jsonObj.get("method");
			} else {
				throw new BadRequestException("method not found");
			}

			if (jsonObj.containsKey("route")) {
				route = (String) jsonObj.get("route");
			} else {
				throw new BadRequestException("route not found");
			}



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
		case "ID":
			this.dealingID(route);
			break;
		case "Table":
			this.dealingTable(route);
			break;
		case "NB_simulation_loan":
			this.dealingNB(route);
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
			JSONArray array = new JSONArray();
			/*
			 * Client
			 */
			if (items[0].equals("clients")) {
				/* Get all clients and push them to a JSON array */
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				for (Model m : clientDAO.find()) {
					array.add(m.toJSON());
				}
			}else if (items[0].equals("categories")) {
				/* Get all categories and push them to a JSON array */
				DAO<Category> categoryDAO = daoFactory.getCategoryDAO();
				categoryDAO.setConnect(poolConnexion.pop().getConnection());
				for (Model m : categoryDAO.find()) {
					array.add(m.toJSON());
				}
			}else if (items[0].equals("loans")) {
				/* Get all loans and push them to a JSON array */
				DAO<Loan> loanDAO = daoFactory.getLoanDAO();
				loanDAO.setConnect(poolConnexion.pop().getConnection());
				for (Model m : loanDAO.find()) {
					array.add(m.toJSON());
				}
			}
			responseString = array.toJSONString();


		} else if (items.length == 2) {

			Model model = null;

			switch (items[0]) {
			case "clients":
				DAO<Client> clientDAO = daoFactory.getClientDAO();
				clientDAO.setConnect(poolConnexion.pop().getConnection());
				model = clientDAO.find(Integer.parseInt(items[1]));
				responseString = model.toJSON().toJSONString();
				break;
			case "loans":
				DAO<Loan> loanDAO = daoFactory.getLoanDAO();
				loanDAO.setConnect(poolConnexion.pop().getConnection());
				model = loanDAO.find(Integer.parseInt(items[1]));
				if (model != null) {
					responseString = model.toJSON().toJSONString();
				} else {
					responseString = "{code:404}";
				}

				break;
			case "categories":
				DAO<Category> categoryDAO = daoFactory.getCategoryDAO();
				categoryDAO.setConnect(poolConnexion.pop().getConnection());
				model = categoryDAO.find(Integer.parseInt(items[1]));

				responseString = model.toJSON().toJSONString();
				break;
			default:
				responseString = "resource does not exists";
				break;
			}

		}


		ConfigurationDB conf = new ConfigurationDB();
		poolConnexion.push(new Connexion(conf));



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
			else if (items[0].equals("loans")) {
				DAO<Loan> loanDAO = daoFactory.getLoanDAO();
				loanDAO.setConnect(poolConnexion.pop().getConnection());
				Loan l = new Loan();
				l.parseJSON(resource);
				System.out.println("Insert : "+l);
				l = loanDAO.create(l);

				if(l != null) {
					Model model = l;
					responseString = model.toJSON().toJSONString();
				} else {
					responseString = "[]"; // server error
				}
			}
			/* New Rate */

			else if (items[0].equals("newRate")) {
				DAO<newRate> newRateDAO = daoFactory.getNewRateDAO();
				newRateDAO.setConnect(poolConnexion.pop().getConnection());
				newRate r = new newRate();
				
				r.parseJSON(resource);

				System.out.println("Insert : "+r);
				r = newRateDAO.create(r);

				if(r != null) {
					Model model = r;
					responseString = model.toJSON().toJSONString();
				} else {
					responseString = "[]"; // server error
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
			}else if (items[0].equals("loans")) {
				/* Get all clients and push them to a JSON array */
				DAO<Loan> loanDAO = daoFactory.getLoanDAO();
				loanDAO.setConnect(poolConnexion.pop().getConnection());

				System.out.println("Loan to find : "+Integer.parseInt(items[1]));

				Loan l = loanDAO.find(Integer.parseInt(items[1]));

				System.out.println("Ok client");

				if (l != null) {

					l.parseJSON(resource);

					System.out.println(l);

					l = loanDAO.update(l);
					if(l != null) {
						Model model = l;
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
			else  if (items[0].equals("loans")) {
				/* Get all clients and push them to a JSON array */
				DAO<Loan> loanDAO = daoFactory.getLoanDAO();
				loanDAO.setConnect(poolConnexion.pop().getConnection());
				Loan l = loanDAO.find(Integer.parseInt(items[1]));
				System.out.println(l);
				if (l != null) {
					if (loanDAO.delete(l)) {
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


	private void dealingID(String route) {
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);


		if (items.length == 3) {

			Model_Manage_id mod =new Model_Manage_id(); ;
			int id=mod.search_id(items[1],items[2]);

			responseString=Integer.toString(id);

		}if (items.length == 2) {

			Model_table_loan mod =new Model_table_loan(); ;
			int id=mod.recherche_id_loan(items[1]);

			responseString=Integer.toString(id);

		}

		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void dealingNB(String route) {
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);

		if (items.length == 3) {

			Model_table_loan mod =new Model_table_loan(); 
			int id_loan=Integer.parseInt(items[1]);
			int id_client=Integer.parseInt(items[2]);
			int id=mod.number_loan_client(id_loan,id_client);

			responseString=Integer.toString(id);

		}
		if (items.length == 2) {

			Model_search_loan mod =new Model_search_loan(); 
			int id_client=Integer.parseInt(items[1]);
			int nb=mod.Calcul_nb_type_loan(id_client);

			responseString=Integer.toString(nb);

		}
		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void dealingTable(String route) {
		/* explode route 
		 * and put fragments in items[]
		 */
		Pattern pattern = Pattern.compile("/");
		String[] items = pattern.split(route);
		JSONArray array = new JSONArray();

		if (items.length == 3) {


			Model_search_loan mod =new Model_search_loan(); 
			int id_of_client=Integer.parseInt(items[1]);
			int nb_type_loan=Integer.parseInt(items[2]);			
			array=(JSONArray) mod.typepret(id_of_client, nb_type_loan) ;
			responseString=array.toJSONString();


		}
		if (items.length == 4) {


			Model_table_loan mod =new Model_table_loan(); 
			int id_type_loan=Integer.parseInt(items[1]);
			int id_client=Integer.parseInt(items[2]);			
			array=(JSONArray) mod.recovery_data_loan(id_type_loan, id_client,items[3]);
			responseString=array.toJSONString();


		}
		/* Write the response in the socket */
		try {
			dataOutputStream.writeBytes(responseString+'\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
