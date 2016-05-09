package com.jassur.controller;

import com.jassur.model.Client;
import com.jassur.model.Model;

public interface Controller {
	
	/**
	 * Action for display all the clients
	 * - get full client list from the server
	 * - render the ClientsList panel
	 */
	public void indexAction();
	
	/**
	 * Action for display the client given
	 * - get the client with the id given from the server
	 * - render the ClientCard panel
	 * @param id
	 */
	public void showAction(int id);
	
	/**
	 * Action for display new Client form
	 * - render the client form with empty client
	 */
	public void newAction();
	
	/**
	 * Action for create a client
	 * - get the form information
	 * - send them to the server 
	 * - render ClientsList or ClientForm
	 * @param JSONObject input
	 */
	public void createAction(Model m);
	
	/**
	 * Action for display edit client form
	 * - render the client form with selected client
	 * @param id
	 */
	public void editAction(int id);
	
	/**
	 * Action for update the client given
	 * - get the form information
	 * - send them to the server
	 * -render ClientsList or ClientForm
	 * @param JSONObject input
	 */
	public void updateAction(Client input);
	
	/**
	 * Action for delete the client given
	 * @param id
	 */
	public void destroyAction(int id);
}
