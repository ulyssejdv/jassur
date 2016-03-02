import com.jassur.dao.DAO;
import com.jassur.dao.DAOFactory;
import com.jassur.database.PoolConnection;
import com.jassur.model.Address;
import com.jassur.model.Category;
import com.jassur.model.Client;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;

public class Test {

	public static void main(String[] args) {
		
		PoolConnection poolConnexion = new PoolConnection();
		
		// Get dao for mysql
		DAOFactory daoFactory = DAOFactory.getFactory(DAOFactory.MYSQL_DAO_FACTORY);
		
		// return a DAOMYSQLFactory object
		DAO<Loan> loanDAO = daoFactory.getLoanDAO();
		
		// setup the connection
		loanDAO.setConnect(poolConnexion.pop().getConnection());
		
		Loan l = new Loan();
		
		l.setAmount(5000);
		l.setTotalAmount(5095.5);
		l.setTotalDuration(24);
		
		Category c = new Category();
		c.setId(1);
		c.setLabelCategory("Immobilier");
		l.setCategory(c);
		
		Rate r = new Rate();
		r.setDuration(24);
		r.setInterestRate(1.90);
		r.setMonthlyPayment(212.50);
		l.addRate(r);
		
		State s = new State();
		s.setLabelState("Simulation");
		s.setUserId(1);
		l.addState(s);
		
		Client client = new Client();
		client.setId(1);
		client.setBusiness(false);
		client.setEmail("toto@gmail.com");
		client.setFirstName("Toto");
		client.setLastName("TITI");
		client.setPhone("0154982635");
		l.setClient(client);
		
		//loanDAO.create(l);
		
		Loan loan = loanDAO.find(1);
		
		System.out.println(loan);
		
		Address adr = new Address();
		adr.setCity("PARIS");
		adr.setCountry("FRANCE");
		adr.setZip(75012);
		adr.setStreet("2 rue voltaire");
		adr.setRegion("PARIS");
		
		Client client2 = new Client();
		client2.setBusiness(false);
		client2.setEmail("toto@gmail.com");
		client2.setFirstName("Toto");
		client2.setLastName("TITI");
		client2.setPhone("0154982635");
		client2.setAddress(adr);
		
		// return a DAOMYSQLFactory object
		DAO<Client> clientDAO = daoFactory.getClientDAO();
		
		// setup the connection
		clientDAO.setConnect(poolConnexion.pop().getConnection());
		
		//clientDAO.create(client2);
		Client c2 = clientDAO.find(13);
		System.out.println(c2.toJSON());
		
	}

}
