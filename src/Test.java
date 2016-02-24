import com.jassur.dao.DAO;
import com.jassur.dao.LoanDAO;
import com.jassur.database.PoolConnexion;
import com.jassur.model.Category;
import com.jassur.model.Loan;
import com.jassur.model.Rate;
import com.jassur.model.State;

public class Test {

	public static void main(String[] args) {
		
		PoolConnexion poolConnexion = new PoolConnexion();
		
		DAO<Loan> loanDAO = new LoanDAO(poolConnexion.pop().getConnection());
		
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
		
		loanDAO.create(l);
		
		Loan loan = loanDAO.find(1);
		
		System.out.println(loan);
	}

}
