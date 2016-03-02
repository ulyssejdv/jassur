package com.jassur.message;

import org.json.simple.JSONObject;

public class RequestBuilder {
	
	public static final int GET = 1;
	public static final int PUT = 2;
	public static final int POST = 3;
	public static final int DELETE = 4;
	
	private int method;
	private String route;
	private Object resource = null;
	
	private JSONObject jObject = new JSONObject(); 
	
	public RequestBuilder(int method, String route) {
		this.method = method;
		this.route = route;
	}
	
	public RequestBuilder(int method, String route, Object resource) {
		this.method = method;
		this.route = route;
		this.resource = resource;
	}
	
	public String toJSONString() {
		
		JSONObject obj = new JSONObject();
		
		switch (this.method) {
		case GET:
			obj.put("method", "GET");
			break;
		case PUT:
			obj.put("method", "PUT");
			break;
		case POST:
			obj.put("method", "POST");
			break;
		case DELETE:
			obj.put("method", "DELETE");
			break;
		default:
			break;
		}
		
		obj.put("route", this.route);
		
		if (this.resource != null) {
			obj.put("resource", this.resource);
		}
		
		return obj.toJSONString();
	}	
}
