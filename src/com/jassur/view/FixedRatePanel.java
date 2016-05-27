package com.jassur.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jassur.controller.ControllerRate;
import com.jassur.controller.RateController;
import com.jassur.model.Address;
import com.jassur.model.Client;
import com.jassur.model.newRate;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class FixedRatePanel extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JLabel lblEcran= new JLabel("0.0");
	private JTextField tfTauxMM= new JTextField();
	private JTextField tfAge = new JTextField();
	private int InputAge=0;
	private double InputTauxMM=0.0;
	private int InputDuration=0;
	private double taux=0.0;
	private double valeurRadio=0.0;
	private double rate;
	private String Risk;
	private String Job;
	private RateController controler;
	private JButton btnCalculer = new JButton("Evaluer");
	private JButton btnSave = new JButton("Sauvegarder");
	private JButton btnReset = new JButton("R\u00E9initialiser");
	private JLabel lblTauxMaisonMre = new JLabel("Taux Maison M\u00E8re :");
	private JLabel lblAge = new JLabel("Age :");
	private JTextField tfDuration = new JTextField();
	private JRadioButton rdbtnGood = new JRadioButton("Bon");
	private JRadioButton rdbtnBad = new JRadioButton("Mauvais");
	private JLabel lblJob = new JLabel("Situation professionnel :");
	private JRadioButton rdbtnCDD = new JRadioButton("CDD");
	private JRadioButton rdbtnCDI = new JRadioButton("CDI");
	private JRadioButton rdbtnUnemployed = new JRadioButton("Chomeur");
	private JRadioButton rdbtnInt = new JRadioButton("Interimaire");
	private ButtonGroup bg2 = new ButtonGroup();
	private ButtonGroup bg = new ButtonGroup();
	private DecimalFormat df = new DecimalFormat();
	private TextArea JtField = new TextArea();
	private boolean ValueRadio;
	private String InputRisk;



	/**
	 * @author jeremy
	 *Create the panel.
	 */

	public FixedRatePanel(RateController controler) {
		this.controler=controler;
		
		setTitle("D\u00E9termination du taux d'int\u00E9rets de l'agence");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(100, 100, 1020, 576);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_lblTauxMaisonMre = new GridBagConstraints();
		gbc_lblTauxMaisonMre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTauxMaisonMre.anchor = GridBagConstraints.EAST;
		gbc_lblTauxMaisonMre.gridx = 11;
		gbc_lblTauxMaisonMre.gridy = 3;
		contentPane.add(lblTauxMaisonMre, gbc_lblTauxMaisonMre);

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 13;
		gbc_textField.gridy = 3;
		contentPane.add(tfTauxMM, gbc_textField);
		tfTauxMM.setColumns(10);

		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.anchor = GridBagConstraints.EAST;
		gbc_lblAge.gridx = 11;
		gbc_lblAge.gridy = 5;
		contentPane.add(lblAge, gbc_lblAge);

		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 13;
		gbc_textField_1.gridy = 5;
		contentPane.add(tfAge, gbc_textField_1);
		tfAge.setColumns(10);

		JLabel lblDurDuPrt = new JLabel("Dur\u00E9e du pr\u00EAt en mois:");
		GridBagConstraints gbc_lblDurDuPrt = new GridBagConstraints();
		gbc_lblDurDuPrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDurDuPrt.anchor = GridBagConstraints.EAST;
		gbc_lblDurDuPrt.gridx = 11;
		gbc_lblDurDuPrt.gridy = 7;
		contentPane.add(lblDurDuPrt, gbc_lblDurDuPrt);

		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 13;
		gbc_textField_2.gridy = 7;
		contentPane.add(tfDuration, gbc_textField_2);
		tfDuration.setColumns(10);

		JLabel lblEtatDeSant = new JLabel("Etat de sant\u00E9 :");
		GridBagConstraints gbc_lblEtatDeSant = new GridBagConstraints();
		gbc_lblEtatDeSant.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtatDeSant.gridx = 11;
		gbc_lblEtatDeSant.gridy = 9;
		contentPane.add(lblEtatDeSant, gbc_lblEtatDeSant);

		//RadioButton

		GridBagConstraints gbc_rdbtnBon = new GridBagConstraints();
		gbc_rdbtnBon.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBon.gridx = 13;
		gbc_rdbtnBon.gridy = 9;
		contentPane.add(rdbtnGood, gbc_rdbtnBon);

		GridBagConstraints gbc_rdbtnMauvais = new GridBagConstraints();
		gbc_rdbtnMauvais.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMauvais.gridx = 14;
		gbc_rdbtnMauvais.gridy = 9;
		contentPane.add(rdbtnBad, gbc_rdbtnMauvais);

		bg.add(rdbtnGood);
		bg.add(rdbtnBad);

		GridBagConstraints gbc_lblJob = new GridBagConstraints();
		gbc_lblJob.insets = new Insets(0, 0, 5, 5);
		gbc_lblJob.gridx = 11;
		gbc_lblJob.gridy = 11;
		contentPane.add(lblJob, gbc_lblJob);

		bg2.add(rdbtnCDD);
		bg2.add(rdbtnCDI);
		bg2.add(rdbtnUnemployed);
		bg2.add(rdbtnInt);

		GridBagConstraints gbc_rdbtnCDD = new GridBagConstraints();
		gbc_rdbtnCDD.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCDD.gridx = 13;
		gbc_rdbtnCDD.gridy = 11;
		contentPane.add(rdbtnCDD, gbc_rdbtnCDD);

		GridBagConstraints gbc_rdbtnrdbtnCDI = new GridBagConstraints();
		gbc_rdbtnrdbtnCDI.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnrdbtnCDI.gridx = 14;
		gbc_rdbtnrdbtnCDI.gridy = 11;
		contentPane.add(rdbtnCDI, gbc_rdbtnrdbtnCDI);

		GridBagConstraints gbc_rdbtnUnemployed = new GridBagConstraints();
		gbc_rdbtnUnemployed.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnUnemployed.gridx = 15;
		gbc_rdbtnUnemployed.gridy = 11;
		contentPane.add(rdbtnUnemployed, gbc_rdbtnUnemployed);

		GridBagConstraints gbc_rdbtnInt = new GridBagConstraints();
		gbc_rdbtnInt.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnInt.gridx = 16;
		gbc_rdbtnInt.gridy = 11;
		contentPane.add(rdbtnInt, gbc_rdbtnInt);

		JLabel lblRsultat = new JLabel("R\u00E9sultat :");
		GridBagConstraints gbc_lblRsultat = new GridBagConstraints();
		gbc_lblRsultat.insets = new Insets(0, 0, 5, 5);
		gbc_lblRsultat.gridx = 11;
		gbc_lblRsultat.gridy = 13;
		contentPane.add(lblRsultat,gbc_lblRsultat);

		GridBagConstraints gbc_lblEcran = new GridBagConstraints();
		gbc_lblEcran.insets = new Insets(10, 0, 20, 5);
		gbc_lblEcran.gridx = 13;
		gbc_lblEcran.gridy = 13;
		contentPane.add(lblEcran, gbc_lblEcran);

		JtField.setPreferredSize(new Dimension(350,35));
		GridBagConstraints gbc_Jtfield = new GridBagConstraints();
		gbc_Jtfield.insets = new Insets(10, 0, 20, 5);
		gbc_Jtfield.gridx = 17;
		gbc_Jtfield.gridy = 14;
		contentPane.add(JtField, gbc_Jtfield);

		btnSave.addActionListener(this);
		contentPane.add(btnSave);
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 5, 5);
		gbc_btnSave.gridx = 12;
		gbc_btnSave.gridy = 15;
		contentPane.add(btnSave, gbc_btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent er) {
				
				taux =Double.parseDouble(lblEcran.getText());
				//get values
				InputRisk = JtField.getText();
				InputAge = Integer.parseInt(tfAge.getText());
				InputTauxMM = Double.parseDouble(tfTauxMM.getText());
				InputDuration = Integer.parseInt(tfDuration.getText()) ;
				if(rdbtnGood.isSelected())
				{
					ValueRadio =true;
				}
				else if (rdbtnBad.isSelected()){
					ValueRadio =false;				
				}
				if(rdbtnCDD.isSelected())
				{
					Job="CDD";
				}
				else if (rdbtnCDI.isSelected()){

					Job="CDI";
				}else if (rdbtnInt.isSelected()){

					Job="Interimaire";
				}else if (rdbtnUnemployed.isSelected()){

					Job="Chomeur";
				}
				
				ControllerRate cc = new ControllerRate();

					newRate r2 = new newRate();
					r2.setAge(InputAge);
					r2.setDuration(InputDuration);
					r2.setRateCompany(InputTauxMM);
					r2.sethealthy(ValueRadio);
					r2.setJob(Job);
					r2.setNewRate(taux);
					r2.setRisk(InputRisk);

					

					

					r2.setProfileId(r2.getProfileId());
					if (r2.getProfileId() != 0) {
						r2.setProfileId(r2.getProfileId());
						cc.updateAction(r2);
					} else {
						cc.createAction(r2);
					}
			}
		});
		
		btnCalculer.addActionListener(this);
		contentPane.add(btnCalculer);
		GridBagConstraints gbc_btnCalculer = new GridBagConstraints();
		gbc_btnCalculer.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculer.gridx = 13;
		gbc_btnCalculer.gridy = 15;
		contentPane.add(btnCalculer, gbc_btnCalculer);
		// Reset all fields of view with click to the button reset
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfTauxMM.setText("");
				tfAge.setText("");
				lblEcran.setText("0.0");
				bg.clearSelection();
				bg2.clearSelection();
				tfDuration.setText("");
				JtField.setText("");
			}
		});
		contentPane.add(btnReset);
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 0, 5, 5);
		gbc_btnReset.gridx = 14;
		gbc_btnReset.gridy = 15;
		contentPane.add(btnReset, gbc_btnReset);
		this.setVisible(true);

	}	
	//method get information of the form
	public void actionPerformed(ActionEvent e) {
		try{
			//if all the information are indicated
			if((!tfTauxMM.getText().isEmpty())&&(!tfAge.getText().isEmpty())&&(!tfDuration.getText().isEmpty()))
			{
				//get values
				InputAge = Integer.parseInt(tfAge.getText());
				InputTauxMM = Double.parseDouble(tfTauxMM.getText());
				InputDuration = Integer.parseInt(tfDuration.getText()) ;
				//Check if radio button is Selected and assign value in the field Job
				if(rdbtnCDD.isSelected())
				{
					Job="CDD";
				}
				else if (rdbtnCDI.isSelected()){

					Job="CDI";
				}else if (rdbtnInt.isSelected()){

					Job="Interimaire";
				}else if (rdbtnUnemployed.isSelected()){

					Job="Chomeur";
				}

				//For evaluate rate found which button radio is checked
				if(rdbtnGood.isSelected())
				{
					valeurRadio=-0.50;
				}
				else if (rdbtnBad.isSelected()){
					valeurRadio=+0.50;
				}
				//Result of method ReturnRisk 
				Risk=controler.get_Risk(valeurRadio, InputAge, InputDuration, Job);
				//displays the results in text area
				JtField.setText(Risk);

				//Conditions 
				if(InputTauxMM >0.3)
				{
					//rate is a result of method Calculs 
					rate=controler.get_rate(InputTauxMM, InputAge, InputDuration, valeurRadio);
					if(rate > 0.3)
					{
						//to format the numbers of decimals 
						DecimalFormat df = new DecimalFormat("##.##" ); 
						//dysplay the result of method Calculs in label lbecran
						lblEcran.setText(String.valueOf(rate));

					}else
					{
						JOptionPane.showMessageDialog(contentPane, "Le taux calcul\u00E9 est trop bas !");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(contentPane, "Le taux de la maison m\u00E8re saisi est trop faible ! ");

				}


			}else
			{
				JOptionPane.showMessageDialog(contentPane, "Attention champs manquants !");
			}
		}	
		catch (NumberFormatException ex) {
			ex.printStackTrace();
		}
	}
}




