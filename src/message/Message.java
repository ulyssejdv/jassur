package message;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Message {
	
	private String action = null;
	private String ressource = null;
	
	public Message(String a, String r) {
		this.action = a;
		this.ressource = r;
	}
	
	public Message(String msg) {
		this.read(msg);
	}
	
	private void read(String m) {
		
		/* Read the configuration file */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			try {
				//System.out.println(msg);
				InputSource is = new  InputSource(new StringReader(m));
				Document doc = db.parse(is);
				
				NodeList dbList = doc.getElementsByTagName("msg");
				
				for (int i = 0; i < dbList.getLength(); i++) {
					Node dbase = dbList.item(i);
					if (dbase.getNodeType() == Node.ELEMENT_NODE) {
			               Element ele = (Element) dbase;
			               
			               this.action = ele.getElementsByTagName("action").item(0).getTextContent();
			               
			               this.ressource = ele.getElementsByTagName("ressource").item(0).getTextContent();
			               
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
	
	public String toXML() {
		String xmlStr = new String();
		xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+"<msg>"
				+"<ressource>"+this.ressource+"</ressource>"
				+"<action>"+this.action+"</action>"
		+"</msg>";
		return xmlStr;
	}
	
	public void send() {
		
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRessource() {
		return ressource;
	}

	public void setRessource(String ressource) {
		this.ressource = ressource;
	}

	@Override
	public String toString() {
		return "Message [action=" + action + ", ressource=" + ressource + "]";
	}
}
