package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.jassur.controller.ClientController;
import com.jassur.controller.LoanController;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.view.ClientListPanel.ButtonEditor;
import com.jassur.view.ClientListPanel.ButtonRenderer;
import com.jassur.view.ClientListPanel.JTableModel;
import com.jassur.view.ClientListPanel.TableComponent;
import com.jassur.view.ClientListPanel.ButtonEditor.ButtonListener;

public class ClientCardPanel extends JPanel {
	private JTable table;
	
	private ArrayList<Loan> loans;
	private Client client;
	/**
	 * Create the panel.
	 * @param mainFrame 
	 */
	public ClientCardPanel(Client c ,ArrayList<Loan> loans) {
		
		this.client = c;
		this.loans = loans;

		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		JPanel panelInfo = new JPanel();

		GroupLayout layout = new GroupLayout(panelInfo);
		panelInfo.setLayout(layout);
		panelInfo.setBackground(Color.WHITE);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		// Create a sequential group for the vertical axis.
		GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

		JLabel lblFirstName = new JLabel("Prenom : ");
		JLabel lblFirstNameIn = new JLabel(c.getFirstName());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblFirstName)
				.addComponent(lblFirstNameIn));

		JLabel lblLastName = new JLabel("Nom : ");
		JLabel lblLastNameIn = new JLabel(c.getLastName());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblLastName)
				.addComponent(lblLastNameIn));

		JLabel lblPhone = new JLabel("Telephone : ");
		JLabel lblPhoneIn = new JLabel(c.getPhone());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblPhone)
				.addComponent(lblPhoneIn));

		JLabel lblEmail = new JLabel("E-mail : ");
		JLabel lblEmailIn = new JLabel(c.getEmail());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblEmail)
				.addComponent(lblEmailIn));

		JLabel lblStreet = new JLabel("Rue : ");
		JLabel lblStreetIn = new JLabel(c.getAddress().getStreet());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblStreet)
				.addComponent(lblStreetIn));

		JLabel lblCity = new JLabel("Ville : ");
		JLabel lblCityIn = new JLabel(c.getAddress().getCity());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblCity)
				.addComponent(lblCityIn));

		JLabel lblRegion = new JLabel("Region : ");
		JLabel lblRegionIn = new JLabel(c.getAddress().getRegion());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblRegion)
				.addComponent(lblRegionIn));

		JLabel lblZip = new JLabel("Code postal : ");
		JLabel lblZipIn = new JLabel(String.valueOf(c.getAddress().getZip()));
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblZip)
				.addComponent(lblZipIn));

		JLabel lblCountry = new JLabel("Pays : ");
		JLabel lblCountryIn = new JLabel(c.getAddress().getCountry());
		vGroup.addGroup(layout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblCountry)
				.addComponent(lblCountryIn));

		GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lblFirstName)
				.addComponent(lblLastName)
				.addComponent(lblPhone)
				.addComponent(lblEmail)
				.addComponent(lblStreet)
				.addComponent(lblCity)
				.addComponent(lblZip)
				.addComponent(lblRegion)
				.addComponent(lblCountry)
				);
		hGroup.addGroup(layout.createParallelGroup()
				.addComponent(lblFirstNameIn)
				.addComponent(lblLastNameIn)
				.addComponent(lblPhoneIn)
				.addComponent(lblEmailIn)
				.addComponent(lblStreetIn)
				.addComponent(lblCityIn)
				.addComponent(lblZipIn)
				.addComponent(lblRegionIn)
				.addComponent(lblCountryIn)
				);
		layout.setHorizontalGroup(hGroup);
		layout.setVerticalGroup(vGroup);
		
		
		
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		
		JButton btnCreateLoan = new JButton("Nouvelle simulation");
		btnCreateLoan.addActionListener(new GotoNewLoanListener(c));
		header.add(btnCreateLoan);
		
		
		
		
		//JPanel tableLoans = new JPanel();
		
		/* prepare the model table */
		Object[][] data = {};
		String  headertable[] = {"Date", "Montant", "Taux", "Status" , "Voir", "Modifier", "Supprimer" };
		JTableModel model = new JTableModel(data, headertable);

		/* initialization of the table */
		table = new JTable(model);
		table.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		/* feed the table */
		for (Loan l: this.loans) {
			/*Object[]  obj = new Object[]{ 
					c.getLastName(), 
					c.getFirstName(), 
					c.getPhone(), 
					c.getEmail(), 
					"Voir", 
					"Modifier",
					"Supprimer"
					
			};*/
			
			Object[]  obj = new Object[]{ 
					"Date", 
					l.getAmount(), 
					l.getRates().get(0).getInterestRate(), 
					l.getStates().get(0).getLabelState(), 
					"Voir", 
					"Modifier",
					"Supprimer"
					
			};
			((JTableModel)table.getModel()).addRow(obj);
		}
		
		table.setDefaultRenderer(JButton.class, new TableComponent());
		
		table.getColumn("Voir").setCellRenderer(new ButtonRenderer());
		table.getColumn("Voir").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		table.getColumn("Supprimer").setCellRenderer(new ButtonRenderer());
		table.getColumn("Supprimer").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		table.getColumn("Modifier").setCellRenderer(new ButtonRenderer());
		table.getColumn("Modifier").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true); 
		
		
		add(header, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(panelInfo, BorderLayout.WEST);

	}
	
	class GotoNewLoanListener implements ActionListener {
		private Client client;
		public GotoNewLoanListener(Client c) {
			this.client = c;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			LoanController lc = new LoanController();
			lc.newAction(this.client);
		}
		
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

	class ButtonRenderer extends JButton implements TableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value != null) ? value.toString() : "");
			return this;
		}
		
	}


	class ButtonEditor extends DefaultCellEditor {

		protected JButton button;
		private boolean   isPushed;
		private ButtonListener bListener = new ButtonListener();

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);

			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(bListener);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { 

			bListener.setRow(row);
			bListener.setColumn(column);
			bListener.setTable(table);
			
			button.setText( (value == null) ? "" : value.toString() );

			return button;
		}

		//Notre listener pour le bouton
		class ButtonListener implements ActionListener{        
			private int column, row;
			private JTable table;
			private int nbre = 0;

			public void setColumn(int col){this.column = col;}
			public void setRow(int row){this.row = row;}
			public void setTable(JTable table){this.table = table;}

			public void actionPerformed(ActionEvent event) {
				
				String action = ((JButton)event.getSource()).getText();
				LoanController lc = new LoanController();
				

				ClientController cc=new ClientController();

				Loan l = ClientCardPanel.this.loans.get(this.row);
				
				
				switch (action) {
				case "Voir":
					lc.showAction(l.getId());
					break;
				case "Supprimer":
					lc.destroyAction(l.getId());

					cc.showAction(ClientCardPanel.this.client.getId());
					break;
				case "Modifier":
					lc.editAction(l.getId());
				default:
					break;
				}
			}
		}     
	}

}
