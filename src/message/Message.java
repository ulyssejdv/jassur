package message;

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
	
	private String resource = null;
	
	private String method = null;
	
	private String body = null;
	
	private String fullMessage = null;

	
	public void read(String m) {
		
		this.setFullMessage(m);
		
		JSONParser parser = new JSONParser();
		
		try {
			JSONObject obj = (JSONObject) parser.parse(m);
			
			obj.get("resource");
			
			this.setResource((String) obj.get("resource")); 
			this.setMethod((String) obj.get("method"));
			
			if (obj.get("body") != null) {
				this.setBody((String) obj.get("body").toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	


	/**
	 * Send GET request for collect the specified resource 
	 * 
	 * @param resource
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String get(String resource) {
		JSONObject json = new JSONObject();
		
		json.put("method", "GET");
		json.put("resource", resource);
		
		return json.toJSONString();
	}
	
	
	
	/**
	 * Send POST request for create a resource in the body bellow
	 * 
	 * @param String resource
	 * @param JSONObject body
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String post(String resource, JSONObject body) {
		JSONObject json = new JSONObject();
		
		json.put("method", "POST");
		json.put("resource", resource);
		json.put("body", body);
		
		return json.toJSONString();
	}
	
	
	
	/**
	 * Send PUT request for update the resource with the body bellow
	 * 
	 * @param resource
	 * @param body
	 * @return String 
	 */
	@SuppressWarnings("unchecked")
	public String put(String resource, JSONObject body) {
		JSONObject json = new JSONObject();
		
		json.put("method", "PUT");
		json.put("resource", resource);
		json.put("body", body);
		
		return json.toJSONString();
	}
	
	
	
	/**
	 * Send DELETE request for delete the resource bellow
	 * 
	 * @param resource
	 * @return String 
	 */
	@SuppressWarnings("unchecked")
	public String delete(String resource) {
		JSONObject json = new JSONObject();
		
		json.put("method", "DELETE");
		json.put("resource", resource);
		
		return json.toJSONString();
	}
	
	
	
	/**
	 * Execute the request (using socket) given to the server 
	 * and catch his response in a String 
	 * 
	 * @param req
	 * @return String 
	 */
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
		return "Message [resource=" + resource + ", method=" + method + ", body=" + body + "]";
	}



	public String getResource() {
		return resource;
	}


	public void setResource(String resource) {
		this.resource = resource;
	}


	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public String getFullMessage() {
		return fullMessage;
	}


	public void setFullMessage(String fullMessage) {
		this.fullMessage = fullMessage;
	}
	
}
