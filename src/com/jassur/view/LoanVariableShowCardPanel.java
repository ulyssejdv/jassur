package com.jassur.view;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.jassur.model.LoanVariable;
import com.jassur.utils.Util;

import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Dimension;


// the interface. What the user sees

public class LoanVariableShowCardPanel extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField amount = new JTextField(10);
	private JTextField nbYears = new JTextField(10);
	private JTextField loanV = new JTextField(10);
	private JButton calculateButton = new JButton("Calculer");
	//Button for best scenarios
	private JButton calculateBestButton = new JButton ("Afficher les sc\u00E9narios favorables");
	private JButton calculateBadButton = new JButton ("Afficher les sc\u00E9narios d\u00E9favorables");
	private JTextField calcSolution = new JTextField(10);
	private final JLabel JLab_Amount = new JLabel("Le Montant");
	private final JLabel JLab_nbYears = new JLabel("La Dur\u00E9e");
	private final JLabel JLab_LoanV = new JLabel("Le Taux %");
	private final JTextPane textPane = new JTextPane();
	private JTable table;
	private JTable table_1;
	private final JLabel label_1 = new JLabel(" en annee(s)");
	
 


	
	public LoanVariableShowCardPanel (){
		// sets up the view and adds the components
	
		
		
		
		String[][] data = null;
	    String[][] data2 = null;
	    
	    final String[] title = new String[]{"Annee","Montant", "Taux %", "Mensuel"};

	    // initCalcPanel
		JPanel calcPanel = initCalcPanel();

		final CardLayout cl=new CardLayout(0, 0);
	
		// int panelCentral
		final JPanel panelCentral = initPanelCentral(calcPanel,cl);

		//create panel best scenario
		createPanelBestScenario(panelCentral, data, title);
		
		//create panel worst scenario
		createPanelWorstScenario(panelCentral, calcPanel, data2, title);

		
		// link between button and panel Best Scenario
		calculateBestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("CALL actionPerformed");
				// update data table best senario
				updateTableSenario(table,true);
			
				cl.show(panelCentral,"name_626830455542738");
				
			}
		});
		// link between button and panel worstScenario
		calculateBadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// update data table worst Scenario 
			
				updateTableSenario(table_1,false);
				
				cl.show(panelCentral,"name_626834466493671");
			}

		
		});
		
	
	}
	

	private JPanel initCalcPanel() {
		
		JPanel calcPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//title
		this.setTitle("Simulation de pr\u00EAts  - JASSUR");
		//size of the windows
		setBounds(100, 100, 802, 574);	
	
		
		calcPanel.setLayout(null);
		amount.setBounds(349, 74, 86, 20);
		
		calcPanel.add(amount);
		nbYears.setBounds(349, 95, 86, 20);
		calcPanel.add(nbYears);
		loanV.setBounds(349, 116, 86, 20);
		calcPanel.add(loanV);
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
		//create button for calculate the total
		calculateButton.setBounds(230, 146, 104, 23);
		calcPanel.add(calculateButton);
		
		calcSolution.setBounds(349, 147, 86, 20);
		calcPanel.add(calcSolution);
		
		
		// create 2 buttons for best and bad scenarios
		calculateBestButton.setBounds(105,180,242,52);
		calcPanel.add(calculateBestButton);
		
		calculateBadButton.setBounds(435,180,242,52);
		calcPanel.add(calculateBadButton);
		
		getContentPane().add(calcPanel);
		JLab_Amount.setBounds(262, 77, 77, 14);
		
		calcPanel.add(JLab_Amount);
		JLab_nbYears.setBounds(262, 98, 77, 14);
		
		calcPanel.add(JLab_nbYears);
		JLab_LoanV.setBounds(262, 119, 77, 14);
		
		
		calcPanel.add(JLab_LoanV);
		textPane.setText("Simuler taux variable");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textPane.setBackground(SystemColor.menu);
		textPane.setBounds(50, 11, 332, 33);
		
		calcPanel.add(textPane);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setBounds(640, 501, 89, 23);
		calcPanel.add(btnRetour);
		
		return calcPanel;
	}


	private JPanel initPanelCentral(JPanel calcPanel, CardLayout cl) {
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBounds(66, 256, 663, 215);
		calcPanel.add(panelCentral);
		panelCentral.setLayout(cl);
	
		return panelCentral;
	}


	private void createPanelBestScenario(JPanel panelCentral,String[][]  data, String[] title) {
		
		JPanel BestScenario = new JPanel();
		BestScenario.setBackground(SystemColor.menu);
		panelCentral.add(BestScenario, "name_626830455542738");
		
		DefaultTableModel model = new DefaultTableModel(data, title);
		
		table = new JTable(model);

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setMaximumSize(new Dimension(660, 250));
	    scrollPane.setMinimumSize(new Dimension(660, 250));
	    scrollPane.setPreferredSize(new Dimension(660, 210));
	    BestScenario.add(scrollPane);
		
	}


	private void createPanelWorstScenario(JPanel panelCentral, JPanel calcPanel, String[][]  data, String[] title) {
		
		
		JPanel BestScenario = new JPanel();
		BestScenario.setBackground(SystemColor.menu);
		panelCentral.add(BestScenario, "name_626834466493671");
		
		DefaultTableModel model = new DefaultTableModel(data, title);
		
		table_1 = new JTable(model);

	    JScrollPane scrollPane = new JScrollPane(table_1);
	    scrollPane.setMaximumSize(new Dimension(660, 250));
	    scrollPane.setMinimumSize(new Dimension(660, 250));
	    scrollPane.setPreferredSize(new Dimension(660, 210));
	    BestScenario.add(scrollPane);
		
	}


	protected void updateTableSenario(JTable t,boolean type) {
		
		
		DefaultTableModel model = (DefaultTableModel)t.getModel();
		
		// reset data table
		if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
		
		LoanVariable lv  =  new LoanVariable();
		try {
			double loanV = getLoanV ();
			double amount = getAmount(); 
			int nbYears = getnbYears();
			
			Object[][] data3 =  new Object[nbYears][4];
			
			for (int i = 0; i < nbYears; i++) {
				
				lv.addValues(amount, nbYears-i, loanV);
				//lv.addBestValues(getAmount(), i+1, loanV);
				
				data3[i][0] = (i+1);
				data3[i][1] = Util.round(amount,2);
				data3[i][2] = Util.round(loanV,2);
				data3[i][3] = lv.getCalculationValue();
				 model.addRow(data3[i]);
				 if(type)
					 loanV = loanV*0.9990;
				 else
					 loanV = loanV*1.0010;

				 amount = amount - (lv.getCalculationValue()*12);
			}
		
		} catch (NumberFormatException e) {
			displayErrorMessage("Attention :"
					+"\n"+ "- Vous devez rentrer toutes les valeurs du formulaire"
					+"\n"+  "- Vous devez utiliser des caracteres numeriques");
		}
	}
	
	public double getAmount(){
		return Double.parseDouble(amount.getText());
	}
	
	public int getnbYears(){
		return Integer.parseInt(nbYears.getText());
	}
	public double getLoanV(){
		return Double.parseDouble(loanV.getText());
	}

	public double getCalcSolution(){
		return Double.parseDouble(calcSolution.getText());
	}
	
	public void setCalcSolution(double solution){
		calcSolution.setText(Double.toString(solution));
		
	}
	public void addCalculationListener(ActionListener listenerForCalcButton){
		
		calculateButton.addActionListener(listenerForCalcButton);
	}
	public void addCalculationBestListener(ActionListener listenerForCalcBestButton){
		
		calculateButton.addActionListener(listenerForCalcBestButton);
	}
	public void addCalculationWorstListener(ActionListener listenerForCalcBestButton){
		
		calculateButton.addActionListener(listenerForCalcBestButton);
	}
	
	//if the calculateButton is clicked execute a method in the controller named actionPerformed

	
	public void displayErrorMessage(String errorMessage){
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("call actionPerformed");
		
	}
}
