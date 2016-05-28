package com.jassur.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.jassur.controller.ClientController;
import com.jassur.controller.HomeController;
import com.jassur.controller.IndicatorController;
import com.jassur.controller.LoanController;
//import com.jassur.controller.SimulateController;
import com.jassur.controller.LoanVariableController;
import com.jassur.test.MVCLoanVariable;
import com.jassur.test.Main_MVC;
import com.jassur.test.Main_Rate;
import com.jassur.view.BaseGUI.GotoSimulationpret;

public class BaseGUI extends JFrame {
	
	/*
	 * Panel who contain all the logic
	 */
	private JPanel contentPane;
	
	
	public static BaseGUI MAIN_FRAME = null;
	
	/**
	 * Display the given panel
	 * @param p
	 * @return
	 */
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
		mntmNouveauClient.addActionListener(new GotoNewClientListener());
		mnClients.add(mntmNouveauClient);
		
		JMenuItem mntmlistClient = new JMenuItem("Liste de clients");
		mntmlistClient.addActionListener(new GotoListClientListener());
		mnClients.add(mntmlistClient);
		
		JMenuItem mntmChercherClient = new JMenuItem("Chercher client");
		mnClients.add(mntmChercherClient);
		
		/*
		 * Loans Menu
		 */
		JMenu mnPrts = new JMenu("Pr\u00eats");
		menuBar.add(mnPrts);
		
		JMenuItem cr = new JMenu("Nouvelle simulation");
		mnPrts.add(cr);
		
		// add item "taux fixe"
		JMenuItem mntmTauxFixe = new JMenuItem ("Choisir taux fixe");
		cr.add(mntmTauxFixe);
		mntmTauxFixe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoanController lc = new LoanController();
				lc.indexAction();
				}
			});
		
		
		
		JMenuItem mntmRetrouverUnPrt = new JMenuItem("Retrouver un pr\u00eat");
		mntmRetrouverUnPrt.addActionListener(new GotoLoanListener());
		mnPrts.add(mntmRetrouverUnPrt);
		
		JMenuItem CalculerTaux = new JMenuItem("Calculer un taux fixe");
		CalculerTaux.addActionListener(new GotoRatesListener());
		mnPrts.add(CalculerTaux);
		
		// add item "taux variable"
		JMenuItem mnTauxVariable = new JMenuItem ("Choisir taux variable");
		mnTauxVariable.addActionListener(new GotoLoanVariableListener());
		mnPrts.add(mnTauxVariable);
		
		JMenuItem maSimul = new JMenuItem("Comparaison des simulations");
		maSimul.addActionListener(new GotoSimulationpret());
		mnPrts.add(maSimul);
		
		/*
		 * Indicators Menu
		 */
		
		JMenu mnIndic = new JMenu("Indicateurs");
		menuBar.add(mnIndic);
		
		JMenuItem mntmIndic = new JMenuItem("Voir Indicateurs");
		mntmIndic.addActionListener(new GotoShowIndicator());
		mnIndic.add(mntmIndic);
		
		
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
	
	class GotoShowIndicator implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			IndicatorController ic = new IndicatorController();
			ic.indexAction();
		    
		}
	}
	
	class GotoRatesListener implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			Main_Rate main_tauxFixe = new Main_Rate();
		}
	}
	
	class GotoHomeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			HomeController hc = new HomeController();
			hc.indexAction();
		}
	}
	
	class GotoSimulationpret implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Main_MVC main_simulation = new Main_MVC();
		    
		}
	}
	
	class GotoNewClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ClientController cc = new ClientController();
			cc.newAction();
		}
		
	}
	
	
	class GotoListClientListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ClientController cc = new ClientController();
			cc.indexAction();
		}
		
	}
	
	class GotoLoanVariableListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MVCLoanVariable main_loanVariation = new MVCLoanVariable();
		    
		}
	}

}
