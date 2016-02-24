package com.jassur.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Connexion {
	
	private Connection connection = null;
	
	private ConfigurationDB conf = null;
	
	public Connexion (ConfigurationDB conf) {
		
		this.conf = conf;
		
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
			System.out.println("erreur "+e.getMessage());
		}
	
	    try {
	    	connection = DriverManager.getConnection(this.conf.getDbUrl(),this.conf.getDbLogin(),this.conf.getDbPass());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection c) {
		this.connection = c;
	}
	
}
