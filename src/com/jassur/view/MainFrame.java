package com.jassur.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.jassur.controller.ClientController;
import java.awt.CardLayout;
import java.awt.FlowLayout;

public class MainFrame extends JFrame {
	
	
	private JPanel context;
	
	private static MainFrame INSTANCE = null;
	
	public static MainFrame getInstance(JPanel p) {
		/*if (INSTANCE != null) {
			INSTANCE.dispose();
		}*/
		MainFrame tmpMf = INSTANCE;
		INSTANCE = new MainFrame(p);
		tmpMf.dispose();
		return INSTANCE;
	}
	
	
	/**
	 * Create the frame.
	 */
	private MainFrame(JPanel p) {
		
		setTitle("JASSUR - Client");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 800);
		
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new TitledBorder(null, "Menu", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelLeft, BorderLayout.WEST);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Clients", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelLeft.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton btnClientList = new JButton("See all");
		btnClientList.addActionListener(new ClientListListener());
		panel.add(btnClientList);
		
		JButton btnClientForm = new JButton("Add new");
		btnClientForm.addActionListener(new ClientFormListener());
		panel.add(btnClientForm);
		
		// context = new JPanel();
		contentPane.add(p, BorderLayout.CENTER);
		//context.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.setVisible(true);
	}
	
	public void render(JPanel p) {
		getContentPane().removeAll();
		getContentPane().add(p);
		getContentPane().revalidate();
	}
	
	
	class ClientListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ClientController cc = new ClientController();
			cc.indexAction();
		}
	}
	
	class ClientFormListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ClientController cc = new ClientController();
			cc.newAction();
		}
	}

}
