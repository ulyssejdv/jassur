package model;

import java.util.ArrayList;
import java.util.Iterator;

public class ListPret {
	
	ArrayList<Pret> listPret = null;
	
	public ListPret(ArrayList<Pret> lp) {
		this.listPret = lp;
	}

	public ArrayList<Pret> getListPret() {
		return listPret;
	}

	public void setListPret(ArrayList<Pret> listPret) {
		this.listPret = listPret;
	}
	
	
	public String toXml() {
		String strXml = new String();
		
		strXml += "<prets>";
		for (Pret pret : listPret) {
			strXml += "<pret>";
			strXml += "<id>"+pret.getIdPret()+"</id>";
			strXml += "<montant>"+pret.getMontant()+"</montant>";
			strXml += "<mensualite>"+pret.getMontantEcheance()+"</mensualite>";
			strXml += "<nbmensualite>"+pret.getNombreEcheance()+"</nbmensualite>";
			strXml += "</pret>";
		}
		strXml += "</prets>";
		
		return strXml;
	}
	
}
