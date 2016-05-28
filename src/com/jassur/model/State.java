/**
 * 
 */
package com.jassur.model;

import org.json.simple.JSONObject;

/**
 * @author ulysse
 *
 */
public class State {
	
	private int id = 0;
	private String labelState;
	private int loanId;
	private int userId;
	
	
	@Override
	public String toString() {
		return "\n State [id=" + id + ", labelState=" + labelState + ", loanId=" + loanId + ", userId=" + userId + "]";
	}
	
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabelState() {
		return labelState;
	}
	public void setLabelState(String labelState) {
		this.labelState = labelState;
	}
	public JSONObject toJSON() {
		JSONObject jObj = new JSONObject();
		
		jObj.put("id_state",this.getId());
		jObj.put("loan_id",this.getLoanId());
		jObj.put("user_id",this.getUserId());
		jObj.put("label_state", this.getLabelState());
		
		return jObj;
	}
	
	public void parseJSON(JSONObject jo) {
		if(jo.get("id_state").equals(null)){
			this.id=0;
		}else this.id=(int)(long)jo.get("id_state");
		if(jo.get("loan_id").equals(null)){
			this.loanId=0;
		}else this.loanId = (int)(long)jo.get("loan_id");
		this.userId = (int)(long)jo.get("user_id");
		this.labelState = (String)jo.get("label_state");	
	}
}
