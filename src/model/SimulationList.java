/**
 * 
 */
package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;
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

import observer.Observable;
import observer.Observer;

/**
 * @author ulysse
 *
 */
public class SimulationList implements Observable {
	
	private ArrayList<Simulation> listSimulation = new ArrayList<Simulation>();
	
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	private int compteur = 0;
	
	
	public void parseXML(String xml) {
		
		/* Read the configuration file */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			try {
				//System.out.println(msg);
				
				this.listSimulation = new ArrayList<Simulation>();
				
				InputSource is = new  InputSource(new StringReader(xml));
				Document doc = db.parse(is);
				
				NodeList dbList = doc.getElementsByTagName("pret");
				
				for (int i = 0; i < dbList.getLength(); i++) {
					Simulation s = new Simulation();
					Node dbase = dbList.item(i);
					if (dbase.getNodeType() == Node.ELEMENT_NODE) {
			               Element ele = (Element) dbase;
			               
			               boolean ajouter = true;
			               
			               s.setId(Integer.parseInt(ele.getElementsByTagName("id").item(0).getTextContent()));
			               s.setDuree(Integer.parseInt(ele.getElementsByTagName("nbmensualite").item(0).getTextContent()));
			               s.setMensualite(Double.parseDouble(ele.getElementsByTagName("mensualite").item(0).getTextContent()));
			               s.setMontant(Double.parseDouble(ele.getElementsByTagName("montant").item(0).getTextContent()));
			               
			               for (Simulation sim : listSimulation) {
			            	   if (sim.getId() == s.getId()) {
									ajouter = false;
				               }
			               }
			               
			               if (ajouter) {
			            	   this.listSimulation.add(s);
			               }
			               
			               
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

	
	public void sendRequestGetList(String xmlMsg) {
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
	
	
	public void addSimulation(Simulation s) {
		listSimulation.add(s);
		notifyObserver();
	}
	
	public void removeSimulation(Simulation s) {
		listSimulation.remove(s);
		notifyObserver();
	}
	
	public int countSimulation() {
		return listSimulation.size();
	}
	
	
	public ArrayList<Simulation> getListSimulation() {
		return listSimulation;
	}

	public void setListSimulation(ArrayList<Simulation> listSimulation) {
		this.listSimulation = listSimulation;
	}

	public void printSimulations() {
		for	(Simulation s : listSimulation) {
			System.out.println(s.toString());
		}
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

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}


}
