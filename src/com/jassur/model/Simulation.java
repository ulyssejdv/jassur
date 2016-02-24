/**
 * 
 */
package com.jassur.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jassur.observer.Observable;
import com.jassur.observer.Observer;

/**
 * @author ulysse
 *
 */
public class Simulation extends Model implements Observable {
	
	public static int compteur = 0;
	
	protected int id;

	protected int duree = 0;
	
	protected double montant = 0;
	
	protected double taux = 0.0;
	
	protected double mensualite = 0.0;
	
	protected double total = 0.0;

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public Simulation() {
		
	}
	
	public void calcul() {
		this.total = new Double(this.montant + ((this.montant * this.taux)/100));
		this.mensualite = this.total / this.duree;
		this.notifyObserver();
	}
	
	public void takeId() {
		compteur++;
		id = compteur;
	}
	
	public void sendRequestGet(String xmlMsg) {
		/* Create the Client socket */
		Socket socket = null;
		try {
			System.out.println("Creating the socket");
			socket = new Socket("localhost", 6789);
			
			/* Sending request to the server */
			try {
				DataOutputStream outToSrv = new DataOutputStream(socket.getOutputStream());
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				/* send xml request */
				outToSrv.writeBytes(xmlMsg+'\n');
				
				/* receive the xml response */
				String rsp = inFromServer.readLine();
				
				System.out.println("Server response : "+rsp);
				
				/* Parse xml response and creating real resources */
				this.parseXML(rsp);
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void sendRequestDelete(String xmlMsg) {
		/* Create the Client socket */
		Socket socket = null;
		try {
			System.out.println("Creating the socket");
			socket = new Socket("localhost", 6789);
			
			/* Sending request to the server */
			try {
				DataOutputStream outToSrv = new DataOutputStream(socket.getOutputStream());
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				/* send xml request */
				outToSrv.writeBytes(xmlMsg+'\n');
				
				/* receive the xml response */
				String rsp = inFromServer.readLine();
				
				System.out.println("Server response : "+rsp);
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void parseXML(String xml) {

		/* Read the configuration file */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			try {
				//System.out.println(msg);
				InputSource is = new  InputSource(new StringReader(xml));
				Document doc = db.parse(is);

				NodeList dbList = doc.getElementsByTagName("pret");

				for (int i = 0; i < dbList.getLength(); i++) {

					Node dbase = dbList.item(i);
					if (dbase.getNodeType() == Node.ELEMENT_NODE) {
						Element ele = (Element) dbase;

						this.setId(Integer.parseInt(ele.getElementsByTagName("id").item(0).getTextContent()));
						this.setDuree(Integer.parseInt(ele.getElementsByTagName("nbmensualite").item(0).getTextContent()));
						this.setMensualite(Double.parseDouble(ele.getElementsByTagName("mensualite").item(0).getTextContent()));
						this.setMontant(Double.parseDouble(ele.getElementsByTagName("montant").item(0).getTextContent()));
						this.setTaux(Double.parseDouble(ele.getElementsByTagName("taux").item(0).getTextContent()));
						this.setTotal(Double.parseDouble(ele.getElementsByTagName("total").item(0).getTextContent()));
					} else {
						System.out.println("non");
					}
				}

			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double d) {
		this.montant = d;
	}

	public double getTaux() {
		return taux;
	}
	
	public void setTaux(double taux) {
		this.taux = taux;
	}

	public double getMensualite() {
		return mensualite;
	}

	public void setMensualite(double mensualite) {
		this.mensualite = mensualite;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void reset() {
		// TODO 
	}
	

	@Override
	public String toString() {
		return "Simulation [id=" + id +", duree=" + duree + ", montant=" + montant + ", taux=" + taux + ", mensualite=" + mensualite
				+ ", total=" + total + "]";
	}
	
	public Object[] toRawTable() {
		Object[] o = {12, 13, 1.90};
		return o;
	}

	@Override
	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	@Override
	public void removeObserver(Observer obs) {
		listObserver.remove(obs);
	}

	@Override
	public void notifyObserver() {
		for	(Observer obs : listObserver) {
			obs.update(this);
		}
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
		strXml += "<pret>";
		strXml += "<id>"+this.getId()+"</id>";
		strXml += "<montant>"+this.getMontant()+"</montant>";
		strXml += "<mensualite>"+this.getMensualite()+"</mensualite>";
		strXml += "<nbmensualite>"+this.getDuree()+"</nbmensualite>";
		strXml += "<taux>"+this.getTaux()+"</taux>";
		strXml += "<total>"+this.getTotal()+"</total>";
		strXml += "</pret>";
		return strXml;
	}

	

}
