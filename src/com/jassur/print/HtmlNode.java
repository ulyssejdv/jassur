package com.jassur.print;

import java.util.ArrayList;

public class HtmlNode {
	private String baliseStart;
	private String baliseEnd;
	private String content = "";
	
	private ArrayList<HtmlNode> nodes;
	
	public HtmlNode(String bs, String be) {
		
		this.nodes = new ArrayList<HtmlNode>();
		
		this.baliseStart = bs;
		this.baliseEnd = be;
	}
	
	public HtmlNode(String bs, String be, String content) {
		
		this.nodes = new ArrayList<HtmlNode>();
		
		this.baliseStart = bs;
		this.baliseEnd = be;
		this.content = content;
	}
	
	public void addNode(HtmlNode hn) {
		this.nodes.add(hn);
	}
	
	public void removeNode(HtmlNode hn) {
		this.nodes.remove(hn);
	}
	
	public String toHTML() {
		String html = this.baliseStart;
		html += this.content;
		for (HtmlNode htmlNode : this.nodes) {
			html += htmlNode.toHTML();
		}
		html += this.baliseEnd;
		return html;
	}
}
