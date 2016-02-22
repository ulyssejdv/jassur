/**
 * 
 */
package model;

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
}
