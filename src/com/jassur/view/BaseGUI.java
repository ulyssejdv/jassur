package com.jassur.view;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.jassur.controller.HomeController;
import com.jassur.controller.LoanController;
//import com.jassur.controller.SimulateController;

public class BaseGUI extends JFrame {
	
	/*
	 * Panel who contain all the logic
	 */
	private JPanel contentPane;
	
	
	public static BaseGUI MAIN_FRAME = null;
	
	public static BaseGUI render(JPanel p) {

		if (MAIN_FRAME == null) {
			MAIN_FRAME = new BaseGUI(p);
		} else {
			JPanel cp = (JPanel) MAIN_FRAME.getContentPane();
			cp.removeAll();
			cp.add(p);
			cp.revalidate(); 
			cp.repaint();
		}
		
		return MAIN_FRAME;
	}

	/**
	 * Create the frame.
	 */
	public BaseGUI(JPanel p) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 576);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		/*
		 * Jassur Menu
		 */
		JMenu mnJassur = new JMenu("Jassur");
		menuBar.add(mnJassur);
		
		JMenuItem mntmAccueil = new JMenuItem("Accueil");
		mntmAccueil.addActionListener(new GotoHomeListener());
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
		
		JMenuItem mntmRetrouverUnPrt = new JMenuItem("Retrouver un pr�t");
		mntmRetrouverUnPrt.addActionListener(new GotoLoanListener());
		mnPrts.add(mntmRetrouverUnPrt);
		
		
		//JMenu maSimul = new JMenu("Simulation de Prêts");
		//menuBar.add(maSimul);
		
		/*
		 * Help Menu
		 */
		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);
		
		JMenuItem mntmAideEnLigne = new JMenuItem("Aide en ligne");
		mnAide.add(mntmAideEnLigne);
		
		JMenuItem mntmReporterUnBug = new JMenuItem("Reporter un bug");
		mnAide.add(mntmReporterUnBug);
		
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
	
	class GotoHomeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			HomeController hc = new HomeController();
			hc.indexAction();
		}
	}
	

}
