package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import com.jassur.controller.ClientController;
import com.jassur.model.Client;

public class ClientListPanel extends JPanel {

	private JTable table;
	
	public ArrayList<Client> clients;
	

	/**
	 * Create the panel.
	 */
	public ClientListPanel(ArrayList<Client> cList) {
			
		this.clients = cList;
		
		Dimension d = new Dimension(BaseGUI.MAIN_FRAME.getWidth(), BaseGUI.MAIN_FRAME.getHeight());
		setPreferredSize(d);
		setBackground(Color.WHITE);
		
		//setBorder(new TitledBorder(null, "Clients Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		/* prepare the model table */
		Object[][] data = {};
		String  header[] = {"Last Name", "First Name", "Phone", "Email" , "Voir", "Modifier", "Supprimer" };
		JTableModel model = new JTableModel(data, header);

		/* initialization of the table */
		table = new JTable(model);
		table.setBounds(0, 0, this.getWidth(), this.getHeight());
		
		/* feed the table */
		for (Client c: cList) {
			Object[]  obj = new Object[]{ 
					c.getLastName(), 
					c.getFirstName(), 
					c.getPhone(), 
					c.getEmail(), 
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
		add(scrollPane, BorderLayout.CENTER);	

		setVisible(true);
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
				ClientController cc = new ClientController();
				
				Client c = ClientListPanel.this.clients.get(this.row);
				
				
				switch (action) {
				case "Voir":
					cc.showAction(c.getId());
					break;
				case "Supprimer":
					cc.destroyAction(c.getId());
					break;
				case "Modifier":
					cc.editAction(c.getId());
				default:
					break;
				}
			}
		}     
	}

}



