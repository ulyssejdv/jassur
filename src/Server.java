import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.ConnectionDB;

public class Server {

	public static void main(String[] args) {
		
		String dbName = new String();
		String dbHost = new String();
		String dbPort = new String();
		String dbLogin = new String();
		String dbPass = new String();
		String dbEnv = new String();
		/* Read the configuration file */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			try {
				Document doc = db.parse(new File("conf/srv_conf.xml"));
				
				Element root = doc.getDocumentElement();
				
				NodeList dbList = root.getElementsByTagName("database");
				
				System.out.println("Reading configuration file ...");
				
				for (int i = 0; i < dbList.getLength(); i++) {
					Node dbase = dbList.item(i);
					if (dbase.getNodeType() == Node.ELEMENT_NODE) {
			               Element ele = (Element) dbase;
			               
			               dbName = ele.getElementsByTagName("name").item(0).getTextContent();
			               System.out.println("Name : "+ dbName);
			               
			               dbHost = ele.getElementsByTagName("host").item(0).getTextContent();
			               System.out.println("Host : "+dbHost);
			               
			               dbPort = ele.getElementsByTagName("port").item(0).getTextContent();
			               System.out.println("Port : "+dbPort);
			               
			               dbLogin = ele.getElementsByTagName("login").item(0).getTextContent();
			               System.out.println("Login : "+dbLogin);
			               
			               dbPass = ele.getElementsByTagName("pass").item(0).getTextContent();
			               System.out.println("Pass : "+ dbPass);
			               
			               dbEnv = ele.getElementsByTagName("env").item(0).getTextContent();
			               System.out.println("Env : "+dbEnv);
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
		
		/* Create the connection pool */
		Connection conn = ConnectionDB.connect("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"",dbLogin,dbPass);
		ConnectionDB database= new ConnectionDB(conn);
		
		/* Start the server */

	}

}
