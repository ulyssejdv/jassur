package com.jassur.message;

import org.json.simple.JSONObject;

import com.jassur.model.Model;

public class RequestBuilder {
	
	public static final int GET = 1;
	public static final int PUT = 2;
	public static final int POST = 3;
	public static final int DELETE = 4;
	
	private int method;
	private String route;
	private Model resource = null;
	
	
	public RequestBuilder(int method, String route) {
		this.method = method;
		this.route = route;
	}
	
	public RequestBuilder(int method, String route, Model resource) {
		this.method = method;
		this.route = route;
		this.resource = resource;
	}
	
	
	@SuppressWarnings("unchecked")
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
			obj.put("resource", this.resource.toJSON());
		}
		return obj.toJSONString();
	}

	public int getMethod() {
		return method;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Model getResource() {
		return resource;
	}

	public void setResource(Model resource) {
		this.resource = resource;
	}
	
	
	
}
