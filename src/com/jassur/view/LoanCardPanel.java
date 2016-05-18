package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import com.jassur.model.Loan;
import com.jassur.model.Rate;

public class LoanCardPanel extends JPanel {
	
	private Loan loan;
	
	private JTextField textFieldAmount;
	private JTextField textFieldDuration;
	private JTextField textFieldMonthWithA;
	private JTextField textFieldMonthA;
	private JTextField textFieldRate;
	private JTextField textFieldFolderCost;
	private JTextField textFieldTotalAmount;
	private JTextField textFieldMonthWithoutA;
	private JTable table;
	

	/**
	 * Create the frame.
	 */
	public LoanCardPanel(Loan l) {
		
		this.loan = l;
		
		setBounds(100, 100, 802, 574);
		
		/* Amount */
		System.out.println(this.loan.getAmount());
		
		
		/* Rate */
		Rate rate = new Rate();
		rate.setDuration(0);
		rate.setInterestRate(5);
		
		for (Rate r : this.loan.getRates()) {
			rate = r;
		}
		
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelHead = new JPanel();
		add(panelHead);
		
		JPanel panelTitle = new JPanel();
		panelHead.add(panelTitle);
		
		JLabel lblRestitutionDeVotre = new JLabel("Restitution de votre prêt");
		panelTitle.add(lblRestitutionDeVotre);
		
		JPanel panelCenter = new JPanel();
		panelHead.add(panelCenter);
		panelCenter.setLayout(new BorderLayout(0, 0));
		
		JPanel panelParams = new JPanel();
		panelCenter.add(panelParams, BorderLayout.NORTH);
		
		JPanel panelTable = new JPanel();
		panelParams.add(panelTable);
		panelTable.setLayout(new GridLayout(0, 4, 0, 0));
		JLabel lblMontantTotalDu = new JLabel("Montant total du prêt :");
		panelTable.add(lblMontantTotalDu);
		lblMontantTotalDu.setBackground(UIManager.getColor("PasswordField.selectionBackground"));
		textFieldAmount = new JTextField(String.valueOf(this.loan.getAmount()));
		textFieldAmount.setEditable(false);
		panelTable.add(textFieldAmount);
		textFieldAmount.setColumns(10);
		JLabel lblDureDuPrt = new JLabel("Durée du prêt :");
		panelTable.add(lblDureDuPrt);
		
		textFieldDuration = new JTextField(String.valueOf(this.loan.getTotalDuration()));
		textFieldDuration.setEditable(false);
		panelTable.add(textFieldDuration);
		textFieldDuration.setColumns(10);
		JLabel lblEchanceAssuranceComprise = new JLabel("Echéance assurance comprise :");
		panelTable.add(lblEchanceAssuranceComprise);
		
		textFieldMonthWithA = new JTextField();
		textFieldMonthWithA.setEditable(false);
		panelTable.add(textFieldMonthWithA);
		textFieldMonthWithA.setColumns(10);
		JLabel lblEchanceDeLassurance = new JLabel("Echéance de l'assurance :");
		panelTable.add(lblEchanceDeLassurance);
		
		textFieldMonthA = new JTextField();
		textFieldMonthA.setEditable(false);
		panelTable.add(textFieldMonthA);
		textFieldMonthA.setColumns(10);
		JLabel lblTauxDintrtDu = new JLabel("Taux d'intérêt du prêt :");
		panelTable.add(lblTauxDintrtDu);
		
		
		textFieldRate = new JTextField(String.valueOf(rate.getInterestRate()));
		textFieldRate.setEditable(false);
		panelTable.add(textFieldRate);
		textFieldRate.setColumns(10);
		JLabel lblFraisDeDossier = new JLabel("Frais de dossier :");
		panelTable.add(lblFraisDeDossier);
		
		textFieldFolderCost = new JTextField();
		textFieldFolderCost.setEditable(false);
		panelTable.add(textFieldFolderCost);
		textFieldFolderCost.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Coût total du prêt :");
		panelTable.add(lblNewLabel_1);
		
		textFieldTotalAmount = new JTextField(String.valueOf(this.loan.getTotalAmount()));
		textFieldTotalAmount.setEditable(false);
		panelTable.add(textFieldTotalAmount);
		textFieldTotalAmount.setColumns(10);
		JLabel lblNewLabel = new JLabel("Echéance hors assurance :");
		panelTable.add(lblNewLabel);
		
		textFieldMonthWithoutA = new JTextField(String.valueOf(rate.getMonthlyPayment()));
		textFieldMonthWithoutA.setEditable(false);
		panelTable.add(textFieldMonthWithoutA);
		textFieldMonthWithoutA.setColumns(10);
		
		
		
		JPanel panelChart = new JPanel();
		panelCenter.add(panelChart, BorderLayout.CENTER);
		
		JPanel panelAmo = new JPanel();
		add(panelAmo);
		
		Object[][] data = {};
		String  header[] = {"Mois", "Mensualité", "Capital restant" };
		JTableModel model = new JTableModel(data, header);
		
		table = new JTable(model);
		
		double capRest = loan.getTotalAmount();
		
		for (int i = 0; i < loan.getTotalDuration(); i++) {
			Object[]  obj = new Object[]{ 
					i, 
					loan.getRates().get(0).getMonthlyPayment(),
					capRest,
			};
			((JTableModel)table.getModel()).addRow(obj);
			
			capRest -= loan.getRates().get(0).getMonthlyPayment();
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelAmo.add(scrollPane);

	}
	
	
	
	
	class TableComponent extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			if (value instanceof JButton) {
				return (JButton) value;
			} else {
				return this;
			}
		}
	}


	class JTableModel extends AbstractTableModel {
		
		private Object[][] data;
		private String[] title;

		public JTableModel(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}

		public String getColumnName(int col) {
			return this.title[col];
		}

		public int getColumnCount() {
			return this.title.length;
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
