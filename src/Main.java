import controller.SimulationController;
import model.Simulation;
import model.SimulationList;
import view.MainFrame;

/**
 * 
 */

/**
 * @author ulysse
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/*
		 * Create a new Simulation Model
		 */
		Simulation s = new Simulation();
		SimulationList sl = new SimulationList();
		
		/* Init controller */
		SimulationController sc = new SimulationController(s, sl);
		
		/* Init main view */
		MainFrame mf = new MainFrame(sc);
		
		/* * * * * * * * * * * * * * * * * * 
		 * Give the MainFrame to the controller
		 * for rendering
		 */
		sc.setMainFrame(mf);
		
		sc.indexAction();
	}

}
