package com.jassur.view;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.jassur.model.Loan;
import java.awt.BorderLayout;

public class LoanShowTablePanel extends JPanel {
	
	private JTable jTable;
	
	private Loan loan;
	
	/**
	 * Create the panel.
	 */
	public LoanShowTablePanel(Loan l) {
		
		setSize(915, 576);
		
		this.loan = l;
		
		/* Table data */
	    Object[][] data = {};
	    
	    /* Columns name */
	    String  header[] = {"Rang", "Date échéance", "Mensualité", "Capital amorti", "Intérêts", "Assurance", "Capital restant dû"};
	    
	    TblModel tblModel = new TblModel(data, header);
	    
	    int size = this.loan.getTotalDuration();
	    
	    jTable = new JTable(tblModel);
	    jTable.setSize(915, 576);
	    
	    double ca = this.loan.getAmount();
	    
	    for (int i = 0; i < size; i++) {
	    	Object[] o = new Object[] {
	    		i,
	    		"date",
	    		this.loan.getRates().get(0).getMonthlyPayment(),
	    		"capital amorti",
	    		"Intérêt",
	    		"Assurance",
	    		ca
	    	};
	    	((TblModel)jTable.getModel()).addRow(o);
	    	ca = ca - this.loan.getRates().get(0).getMonthlyPayment();
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
