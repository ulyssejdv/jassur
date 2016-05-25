package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.jassur.dao.CategoryDAO;
import com.jassur.model.Category;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FixedRatePanel extends JFrame {
	
	private JPanel contentPane;
	private JLabel lblEcran;
	private JTextField tfTauxMM;
	private JTextField tfAge;
	private JTextField tfDuration;
	private int InputAge=0;
	private double InputTauxMM=0.0;
	private int InputDuration=0;
	private double taux=0.0;
	private double valeurRadio=0.0;
	
	/**
	* Create the panel.
	*/
	//Faire MVC et en anglais
	public FixedRatePanel() {
		//--------------------------------
		setTitle("D\u00E9termination du taux d'int\u00E9rets de l'agence");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblTauxMaisonMre = new JLabel("Taux Maison M\u00E8re :");
		GridBagConstraints gbc_lblTauxMaisonMre = new GridBagConstraints();
		gbc_lblTauxMaisonMre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTauxMaisonMre.anchor = GridBagConstraints.EAST;
		gbc_lblTauxMaisonMre.gridx = 3;
		gbc_lblTauxMaisonMre.gridy = 1;
		contentPane.add(lblTauxMaisonMre, gbc_lblTauxMaisonMre);

		tfTauxMM = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 1;
		contentPane.add(tfTauxMM, gbc_textField);
		tfTauxMM.setColumns(10);

		JLabel lblAge = new JLabel("Age :");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.anchor = GridBagConstraints.EAST;
		gbc_lblAge.gridx = 3;
		gbc_lblAge.gridy = 2;
		contentPane.add(lblAge, gbc_lblAge);

		tfAge = new JTextField();
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

		tfDuration = new JTextField();
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
		JRadioButton rdbtnBon = new JRadioButton("Bon");
		GridBagConstraints gbc_rdbtnBon = new GridBagConstraints();
		gbc_rdbtnBon.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBon.gridx = 4;
		gbc_rdbtnBon.gridy = 4;
		contentPane.add(rdbtnBon, gbc_rdbtnBon);

		JRadioButton rdbtnMauvais = new JRadioButton("Mauvais");
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

		JLabel lblEcran = new JLabel();
		GridBagConstraints gbc_lblEcran = new GridBagConstraints();
		gbc_lblEcran.insets = new Insets(10, 0, 20, 5);
		gbc_lblEcran.gridx = 4;
		gbc_lblEcran.gridy = 6;
		contentPane.add(lblEcran, gbc_lblEcran);

		JButton btnCalculer = new JButton("Calculer");
		btnCalculer.addActionListener(new ActionListener() {
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
						System.out.println(" //////////////Dur� du pret : :"+InputDuration+"////////////////");
						if(rdbtnBon.isSelected())
						{
							valeurRadio=-0.50;
						}
						else if (rdbtnMauvais.isSelected()){
							valeurRadio=+0.50;
						}
						Calculs(InputTauxMM, InputAge, InputDuration, valeurRadio);
						lblEcran.setText(String.valueOf(taux));
					}else
					{
						JOptionPane.showMessageDialog(contentPane, "Attention champs manquants !");
					}
				}	
				catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
			}


		});

		GridBagConstraints gbc_btnCalculer = new GridBagConstraints();
		gbc_btnCalculer.insets = new Insets(0, 0, 5, 5);
		gbc_btnCalculer.gridx = 4;
		gbc_btnCalculer.gridy = 7;
		contentPane.add(btnCalculer, gbc_btnCalculer);

		//-----------------------


	}	

	/**
	 * Calculs Taux.
	 */
	public void Calculs(double inputTauxMM2, int InputAge, int InputDuration, double valeurRadio){
		System.out.println("Valeur bouton radio :"+ valeurRadio + "////");

		//Inferieur ou egale a 45 ans et superieur e 18 ans

		if(InputAge <= 45 && InputAge >=18)
		{
			if(InputDuration <= 72)
			{
				taux=inputTauxMM2 - 0.30+valeurRadio;
				System.out.println(taux);

			}
			else{
				taux=inputTauxMM2 + 0.30+valeurRadio;
				System.out.println(taux);

			}

		}
		else if (InputAge > 45)
		{
			if(InputDuration <= 72)
			{
				taux=inputTauxMM2 + 0.20+valeurRadio;
				System.out.println(taux);

			}
			else{
				taux=inputTauxMM2 + 0.40+valeurRadio;
				System.out.println(taux);


			}
		}
	}

	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FixedRatePanel frame = new FixedRatePanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	    
		
	}
	
}

