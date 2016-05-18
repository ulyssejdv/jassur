package com.jassur.controller;

import com.jassur.model.Client;
import com.jassur.model.Model;
import com.jassur.view.BaseGUI;
import com.jassur.view.HomePanel;

public class HomeController implements Controller {

	@Override
	public void indexAction() {
		HomePanel hp = new HomePanel();
		BaseGUI.render(hp);
	}

	@Override
	public void showAction(int id) {
		// TODO Auto-generated method stub
		
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
