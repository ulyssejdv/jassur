package com.jassur.message;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Routes {
	
	private ArrayList<Pattern> getRoutes;
	private ArrayList<Pattern> postRoutes;
	private ArrayList<Pattern> putRoutes;
	private ArrayList<Pattern> deleteRoutes;
	
	public Routes() {
		loadRoutes();
	}
	
	private void loadRoutes() {
		
		/* Routes for clients */
		getRoutes.add(Pattern.compile("clients"));
		getRoutes.add(Pattern.compile("clients/[0-9]"));
		
		postRoutes.add(Pattern.compile("clients"));
		
		putRoutes.add(Pattern.compile("clients/[0-9]"));
		
		deleteRoutes.add(Pattern.compile("clients/[0-9]"));
	}

}
