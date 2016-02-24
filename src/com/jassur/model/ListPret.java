package com.jassur.model;

import java.sql.Connection;
import java.util.ArrayList;

public class ListPret extends Model {
	
	private ArrayList<Pret> listPret = null;
	
	public ListPret(ArrayList<Pret> lp) {
		this.listPret = lp;
	}

	public ArrayList<Pret> getListPret() {
		return listPret;
	}

	public void setListPret(ArrayList<Pret> listPret) {
		this.listPret = listPret;
	}

	@Override
	public int insert(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void get(Connection conn, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int update(Connection conn) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Connection con) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toXML() {
		String strXml = new String();
		strXml += "<prets>";
		for (Pret pret : listPret) {
			strXml += pret.toXML();
			System.out.println(pret.toString());
		}
		strXml += "</prets>";
		
		
		System.out.println("CONSTRUIT : "+strXml);
		return strXml;
	}

	@Override
	public void parseXML(String xml) {
		// TODO Auto-generated method stub
		
	}
	
}
