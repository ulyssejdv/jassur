package message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import model.Model;

public class Message {
	
	private String resourceType = null;
	
	private String methode = null;
	
	private int resourceId = -1;
	
	private String fullMsgString = null;

	
	public void read(String m) {
		
		this.setFullMsgString(m);
		
		/* Read the message */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			try {
				//System.out.println(msg);
				InputSource is = new  InputSource(new StringReader(m));
				Document doc = db.parse(is);
				
				NodeList dbList = doc.getElementsByTagName("message");
				
				for (int i = 0; i < dbList.getLength(); i++) {
					Node dbase = dbList.item(i);
					if (dbase.getNodeType() == Node.ELEMENT_NODE) {
			               Element ele = (Element) dbase;
			               this.methode = ele.getElementsByTagName("methode").item(0).getTextContent();
			               this.resourceType = ele.getElementsByTagName("resourceType").item(0).getTextContent();
			               this.resourceId = Integer.parseInt(ele.getElementsByTagName("resourceId").item(0).getTextContent());
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
	
	
	/**
	 * Request for reading N resources
	 */
	public String get(String rt) {
		this.methode = "GET";
		String xmlStr = new String();
		xmlStr = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+"<message>"
			+"<methode>"+this.methode+"</methode>"
			+"<resourceType>"+rt+"</resourceType>"
			+"<resourceId>"+this.resourceId+"</resourceId>"
		+"</message>";
		
		return this.execRequest(xmlStr);
	}
	
	/**
	 * Request for reading 1 resource given by id
	 */
	public String get(String rt, int id) {
	
		this.methode = "GET";
		String xmlStr = new String();
		xmlStr = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+"<message>"
			+"<methode>"+this.methode+"</methode>"
			+"<resourceType>"+rt+"</resourceType>"
			+"<resourceId>"+id+"</resourceId>"
		+"</message>";
		
		return this.execRequest(xmlStr);
	}
	
	/**
	 * Request for create resources
	 */
	public String post(String rt, String xml) {
		this.methode = "POST";
		String xmlStr = new String();
		xmlStr = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+"<message>"
			+"<methode>"+this.methode+"</methode>"
			+"<resourceType>"+rt+"</resourceType>"
			+"<resourceId>"+this.resourceId+"</resourceId>"
			+"<resource>"+xml+"</resource>"
		+"</message>";
		
		return this.execRequest(xmlStr);
	}
	
	/**
	 * Request for update resources
	 */
	public String put(String xml) {
		this.methode = "PUT";
		String xmlStr = new String();
		xmlStr = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+"<message>"
			+"<methode>"+this.methode+"</methode>"
			+"<resourceType>"+this.resourceType+"</resourceType>"
			+"<resourceId>"+this.resourceId+"</resourceId>"
		+"</message>";
		
		return xmlStr;
	}
	
	/**
	 * Request for delete resources
	 */
	public String delete(String r, int id) {

		String xmlStr = new String();
		xmlStr = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
		+"<message>"
			+"<methode>DELETE</methode>"
			+"<resourceType>"+r+"</resourceType>"
			+"<resourceId>"+id+"</resourceId>"
		+"</message>";
		
		return this.execRequest(xmlStr);
	}
	
	
	public String execRequest(String req) {
		
		String rep = new String();
		
		/* Create the Client socket */
		Socket socket = null;
		try {
			System.out.println("Sending message ...");
			System.out.println(req);
			socket = new Socket("localhost", 6789);
			
			/* Sending request to the server */
			try {
				DataOutputStream outToSrv = new DataOutputStream(socket.getOutputStream());
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				/* send xml request */
				outToSrv.writeBytes(req+'\n');
				
				/* receive the xml response */
				rep = inFromServer.readLine();
				
				System.out.println("Server response : "+rep);
				
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
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		/* Return the server's response */
		return rep;
	}
	
	


	@Override
	public String toString() {
		return "Message [resourceType=" + resourceType + ", methode=" + methode + ", resourceId=" + resourceId + "]";
	}


	public String getResourceType() {
		return resourceType;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}


	public String getMethode() {
		return methode;
	}


	public void setMethode(String methode) {
		this.methode = methode;
	}


	public int getResourceId() {
		return resourceId;
	}


	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}


	/**
	 * @return the fullMsgString
	 */
	public String getFullMsgString() {
		return fullMsgString;
	}


	/**
	 * @param fullMsgString the fullMsgString to set
	 */
	public void setFullMsgString(String fullMsgString) {
		this.fullMsgString = fullMsgString;
	}
	
	
}
