package com.jassur.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.jassur.controller.RateController;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FixedRatePanel extends JFrame implements ActionListener {
	
	private JPanel contentPane;
	private JLabel lblEcran= new JLabel();
	private JTextField tfTauxMM= new JTextField();
	private JTextField tfAge = new JTextField();
	private int InputAge=0;
	private double InputTauxMM=0.0;
	private int InputDuration=0;
	private double taux=0.0;
	private double valeurRadio=0.0;
	private double rate;
	private RateController controler;
	private JButton btnCalculer = new JButton("Calculer");
	private JLabel lblTauxMaisonMre = new JLabel("Taux Maison M\u00E8re :");
	private JLabel lblAge = new JLabel("Age :");
	private JTextField tfDuration = new JTextField();
	private JRadioButton rdbtnBon = new JRadioButton("Bon");
	private JRadioButton rdbtnMauvais = new JRadioButton("Mauvais");


	
	
	/**
	 * @author j�r�my
	 *Create the panel.
	 */
	
	public FixedRatePanel(RateController controler) {
		this.controler=controler;
		//--------------------------------
		setTitle("D\u00E9termination du taux d'int\u00E9rets de l'agence");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setVisible(true);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		
		GridBagConstraints gbc_lblTauxMaisonMre = new GridBagConstraints();
		gbc_lblTauxMaisonMre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTauxMaisonMre.anchor = GridBagConstraints.EAST;
		gbc_lblTauxMaisonMre.gridx = 3;
		gbc_lblTauxMaisonMre.gridy = 1;
		contentPane.add(lblTauxMaisonMre, gbc_lblTauxMaisonMre);

		
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 1;
		contentPane.add(tfTauxMM, gbc_textField);
		tfTauxMM.setColumns(10);

		
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.anchor = GridBagConstraints.EAST;
		gbc_lblAge.gridx = 3;
		gbc_lblAge.gridy = 2;
		contentPane.add(lblAge, gbc_lblAge);

		
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 4;
		gbc_textField_1.gridy = 2;
		contentPane.add(tfAge, gbc_textField_1);
		tfAge.setColumns(10);

		JLabel lblDurDuPrt = new JLabel("Dur\u00E9e du pr\u00EAt :");
		GridBagConstraints gbc_lblDurDuPrt = new GridBagConstraints();
		gbc_lblDurDuPrt.insets = new Insets(0, 0, 5, 5);
		gbc_lblDurDuPrt.anchor = GridBagConstraints.EAST;
		gbc_lblDurDuPrt.gridx = 3;
		gbc_lblDurDuPrt.gridy = 3;
		contentPane.add(lblDurDuPrt, gbc_lblDurDuPrt);

		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 4;
		gbc_textField_2.gridy = 3;
		contentPane.add(tfDuration, gbc_textField_2);
		tfDuration.setColumns(10);

		JLabel lblEtatDeSant = new JLabel("Etat de sant\u00E9 :");
		GridBagConstraints gbc_lblEtatDeSant = new GridBagConstraints();
		gbc_lblEtatDeSant.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtatDeSant.gridx = 3;
		gbc_lblEtatDeSant.gridy = 4;
		contentPane.add(lblEtatDeSant, gbc_lblEtatDeSant);

		//RadioButton
		
		GridBagConstraints gbc_rdbtnBon = new GridBagConstraints();
		gbc_rdbtnBon.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBon.gridx = 4;
		gbc_rdbtnBon.gridy = 4;
		contentPane.add(rdbtnBon, gbc_rdbtnBon);

		GridBagConstraints gbc_rdbtnMauvais = new GridBagConstraints();
		gbc_rdbtnMauvais.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnMauvais.gridx = 5;
		gbc_rdbtnMauvais.gridy = 4;
		contentPane.add(rdbtnMauvais, gbc_rdbtnMauvais);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnBon);
		bg.add(rdbtnMauvais);

		JLabel lblRsultat = new JLabel("R\u00E9sultat :");
		GridBagConstraints gbc_lblRsultat = new GridBagConstraints();
		gbc_lblRsultat.insets = new Insets(0, 0, 5, 5);
		gbc_lblRsultat.gridx = 3;
		gbc_lblRsultat.gridy = 6;
		contentPane.add(lblRsultat, gbc_lblRsultat);

		
		GridBagConstraints gbc_lblEcran = new GridBagConstraints();
		gbc_lblEcran.insets = new Insets(10, 0, 20, 5);
		gbc_lblEcran.gridx = 4;
		gbc_lblEcran.gridy = 6;
		contentPane.add(lblEcran, gbc_lblEcran);

		btnCalculer.addActionListener(this);
			

		
		contentPane.add(btnCalculer);
		btnCalculer.setBounds(100, 150, 200, 30);

		//-----------------------


	}	

	public void actionPerformed(ActionEvent e) {
		try{

			if((!tfTauxMM.getText().isEmpty())&&(!tfAge.getText().isEmpty())&&(!tfDuration.getText().isEmpty()))
			{
				System.out.println("TEST //////////////////////////////");
				InputAge = Integer.parseInt(tfAge.getText());
				System.out.println(" //////////////Age :"+InputAge+"////////////////");
				InputTauxMM = Double.parseDouble(tfTauxMM.getText());
				System.out.println(" //////////////TauxMM :"+InputTauxMM+"////////////////");
				InputDuration = Integer.parseInt(tfDuration.getText()) ;
				System.out.println(" //////////////Duree du pret : :"+InputDuration+"////////////////");
				if(rdbtnBon.isSelected())
				{
					valeurRadio=-0.50;
				}
				else if (rdbtnMauvais.isSelected()){
					valeurRadio=+0.50;
				}
				rate=controler.get_rate(InputTauxMM, InputAge, InputDuration, valeurRadio);
				lblEcran.setText(String.valueOf(rate));
						
				
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

	


