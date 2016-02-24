package com.jassur.database;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationDB {
	
	private String dbName = new String();
	private String dbHost = new String();
	private String dbPort = new String();
	private String dbLogin = new String();
	private String dbPass = new String();
	private String dbEnv = new String();
	
	private String dbUrl = new String();

	public ConfigurationDB() {
		this.load();
	}
	
	@Override
	public String toString() {
		return "ConfigurationDB [dbName=" + dbName + ", dbHost=" + dbHost + ", dbPort=" + dbPort + ", dbLogin="
				+ dbLogin + ", dbPass=" + dbPass + ", dbEnv=" + dbEnv + ", dbUrl=" + dbUrl + "]";
	}

	public void load() {
		System.out.println("Loading configuration file ...");
		
		/* Read the configuration file */
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			try {
				Document doc = db.parse(new File("conf/srv_conf.xml"));
				
				Element root = doc.getDocumentElement();
				
				NodeList dbList = root.getElementsByTagName("database");
				
				for (int i = 0; i < dbList.getLength(); i++) {
					Node dbase = dbList.item(i);
					if (dbase.getNodeType() == Node.ELEMENT_NODE) {
			               Element ele = (Element) dbase;
			               
			               dbName = ele.getElementsByTagName("name").item(0).getTextContent();
			               
			               dbHost = ele.getElementsByTagName("host").item(0).getTextContent();
			               
			               dbPort = ele.getElementsByTagName("port").item(0).getTextContent();
			               
			               dbLogin = ele.getElementsByTagName("login").item(0).getTextContent();
			               
			               dbPass = ele.getElementsByTagName("pass").item(0).getTextContent();
			               
			               dbEnv = ele.getElementsByTagName("env").item(0).getTextContent();
					} else {
						System.out.println("non");
					}
				}
				
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.out.println("EXIT ...");
				System.exit(0);
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(this.toString());
	}
	
	
	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbLogin() {
		return dbLogin;
	}

	public void setDbLogin(String dbLogin) {
		this.dbLogin = dbLogin;
	}

	public String getDbPass() {
		return dbPass;
	}

	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}

	public String getDbEnv() {
		return dbEnv;
	}

	public void setDbEnv(String dbEnv) {
		this.dbEnv = dbEnv;
	}

	public String getDbUrl() {
		return "jdbc:mysql://"+this.dbHost+":"+this.dbPort+"/"+this.dbName;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

}
