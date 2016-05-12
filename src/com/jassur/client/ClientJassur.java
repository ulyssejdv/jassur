package com.jassur.client;
import com.jassur.view.BaseGUI;
import com.jassur.view.HomePanel;

/**
 * 
 */

/**
 * @author ulysse
 *
 */
public class ClientJassur {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
        //ClientController cc = new ClientController();
        //cc.indexAction();	
		try {
			/*BaseGUI frame = new BaseGUI();
			frame.setContentPane(new HomePanel());
			frame.setVisible(true);*/
			
			BaseGUI.render(new HomePanel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
