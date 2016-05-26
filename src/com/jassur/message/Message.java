package com.jassur.message;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Message {
	
	/**
	 * Execute the request (using socket) given to the server 
	 * and catch his response in a String 
	 * 
	 * @param req
	 * @return String 
	 */
	public static String execRequest(String req) {
		
		String rep = new String();
		
		/* Create a new socket with the server */
		Socket socket = null;
		try {
			System.out.println("Sending message ...");
			System.out.println(req);
			socket = new Socket("localhost", 6789);
			
			/* Sending request to the server */
			try {
				DataOutputStream outToSrv = new DataOutputStream(socket.getOutputStream());
				
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				/* send JSON request */
				outToSrv.writeBytes(req+'\n');
				
				/* receive the JSON response */
				rep = inFromServer.readLine();
				System.out.println("response : "+rep);
				
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
}
