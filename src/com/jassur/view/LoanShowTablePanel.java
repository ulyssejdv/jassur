package com.jassur.view;

import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.jassur.model.Loan;

public class LoanShowTablePanel extends JPanel {
	
	private JTable jTable;
	
	private Loan loan;
	
	/**
	 * Create the panel.
	 */
	public LoanShowTablePanel(Loan l) {
		
		this.loan = l;
		
		/* Table data */
	    Object[][] data = {};
	    
	    /* Columns name */
	    String  header[] = {"Rang","Date échéance","Intérêts","Capital amorti","Assurance","Mensualité","Capital restant dû"};
	    
	    TblModel tblModel = new TblModel(data, header);
	    
	    String pattern = "0.00";
	    DecimalFormat decimalFormat = new DecimalFormat(pattern);
	    
	    
	    int totalDuration = this.loan.getTotalDuration();
	    double interest = 0;
	    double insurance = this.loan.insurancePerMonth();
	    double remainingCapital = this.loan.getAmount();
	    double monthlyPayment = this.loan.getRates().get(0).getMonthlyPayment();
	   
	    
	    jTable = new JTable(tblModel);
	    
	    Object[] o = new Object[] {0,"-","-","-","-","-",decimalFormat.format((double)remainingCapital)};
	    ((TblModel)jTable.getModel()).addRow(o);
	   
	    
	    for (int i = 1; i <= totalDuration; i++) {
	    	
	    	// calculation for next round
	    	remainingCapital = this.loan.remainingCapital(remainingCapital,interest,monthlyPayment);
	    	interest = this.loan.interestPerMonth(remainingCapital);
	    	double principal = this.loan.principal(monthlyPayment, interest);
	    	
	    	o = new Object[] {
	    			i,
	    			"date",
	    			decimalFormat.format((double)interest),
	    			decimalFormat.format((double)principal),
	    			insurance,
	    			monthlyPayment,
	    			decimalFormat.format((double)remainingCapital)
	    	};
	    	((TblModel)jTable.getModel()).addRow(o);
	    }
	    setLayout(new BorderLayout(0, 0));
	    
	    jTable.setFillsViewportHeight(true);
	    JScrollPane jsp = new JScrollPane(jTable);
	    jsp.setSize(915, 576);
	    add(jsp);
	}
	
	class TblModel extends AbstractTableModel {
		
		private Object[][] data;
	    private String[] header;
	    
	    public TblModel(Object[][] data, String[] header) {
	    	this.header = header;
	    	this.data = data;
	    }
	    
	    public String getColumnName(int col) {
			return this.header[col];
		}

		public int getColumnCount() {
			return this.header.length;
		}

		public int getRowCount() {
			return this.data.length;
		}

		public Object getValueAt(int row, int col) {
			return this.data[row][col];
		}

		public void setValueAt(Object value, int row, int col) {
			if(!this.getColumnName(col).equals("Voir") && !this.getColumnName(col).equals("Supprimer"))
				this.data[row][col] = value;
		}

		public Class getColumnClass(int col){
			return this.data[0][col].getClass();
		}

		public boolean isCellEditable(int row, int col){
			if(getValueAt(0, col) instanceof JButton)
				return false;
			return true; 
		}
		
		public void removeRow(int position){
			int indice = 0, indice2 = 0;
			int nbRow = this.getRowCount()-1, nbCol = this.getColumnCount();
			Object temp[][] = new Object[nbRow][nbCol];

			for(Object[] value : this.data){
				if(indice != position){
					temp[indice2++] = value;
				}
				System.out.println("Indice = " + indice);
				indice++;
			}
			this.data = temp;
			temp = null;

			this.fireTableDataChanged();
		}
		
		public void addRow(Object[] data) {
			int indice = 0;
			int nbRow = this.getRowCount();
			int nbCol = this.getColumnCount();
			
			Object[][] temp = this.data;
			
			this.data = new Object[nbRow+1][nbCol];
			for(Object[] value : temp) {
				this.data[indice++] = value;
			}
			this.data[indice] = data;
			temp = null;
			this.fireTableDataChanged();
		}
	}

}
