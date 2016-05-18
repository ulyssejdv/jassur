package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.jassur.controller.LoanController;
//import com.jassur.controller.SimulateController;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
import javax.swing.JMenu;

public class BaseGUI extends JFrame {
	
	/*
	 * Panel who contain all the logic
	 */
	private JPanel contentPane;
	
	
	private static BaseGUI MAIN_FRAME = null;
	
	public static BaseGUI render(JPanel p) {

		BaseGUI tmpMf = MAIN_FRAME;
		MAIN_FRAME = new BaseGUI(p);
		if (tmpMf != null) {
			tmpMf.dispose();
		}
		//MAIN_FRAME.pack();
		return MAIN_FRAME;
	}

	/**
	 * Create the frame.
	 */
	public BaseGUI(JPanel p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 915, 576);
		
		setSize(915, 576);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/*
		 * Jassur Menu
		 */
		JMenu mnJassur = new JMenu("Jassur");
		menuBar.add(mnJassur);
		
		JMenuItem mntmAccueil = new JMenuItem("Accueil");
		mnJassur.add(mntmAccueil);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnJassur.add(mntmQuitter);
		
		/*
		 * Clients Menu
		 */
		JMenu mnClients = new JMenu("Clients");
		menuBar.add(mnClients);
		
		JMenuItem mntmNouveauClient = new JMenuItem("Nouveau client");
		mnClients.add(mntmNouveauClient);
		
		JMenuItem mntmChercherClient = new JMenuItem("Chercher client");
		mnClients.add(mntmChercherClient);
		
		/*
		 * Loans Menu
		 */
		JMenu mnPrts = new JMenu("Prêts");
		menuBar.add(mnPrts);
		
		JMenuItem cr = new JMenu("Nouvelle simulation");
		mnPrts.add(cr);
		
		// add item "taux fixe"
		JMenuItem mntmTauxFixe = new JMenuItem ("Choisir taux fixe");
		cr.add(mntmTauxFixe);
		
		// add item "taux fixe"
		JMenuItem mntmTauxVariable = new JMenuItem ("Choisir taux variable");
		mntmTauxVariable.addActionListener(new GotoLoanListener());
		cr.add(mntmTauxVariable);
		
		JMenuItem mntmRetrouverUnPrt = new JMenuItem("Retrouver un prêt");
		mntmRetrouverUnPrt.addActionListener(new GotoLoanListener());
		mnPrts.add(mntmRetrouverUnPrt);
		
		/*
		 * Help Menu
		 */
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JMenuItem mntmAideEnLigne = new JMenuItem("Aide en ligne");
		mnAide.add(mntmAideEnLigne);
		
		JMenuItem mntmReporterUnBug = new JMenuItem("Reporter un bug");
		mnAide.add(mntmReporterUnBug);
		
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(p);
		
		this.setVisible(true);
	}
	
	class GotoLoanListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LoanController lc = new LoanController();
		    lc.showAction(3);
		}
	}

	
	/*
	 * @author aurelie
	 * 
	 *  class GotoSimulateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			SimulateController Sc = new SimulateController();
		    Sc.showAction(3);
		}
	}
	*/

}
