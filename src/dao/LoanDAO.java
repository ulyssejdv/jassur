package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

import model.Loan;
import model.Rate;
import model.State;

public class LoanDAO extends DAO<Loan> {

	public LoanDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Loan obj) {
		
		String sql = 
				"INSERT INTO loans "+
				"(client_id, category_id, amount, total_duration, total_amount) "+
				"VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, obj.getId());
			statement.setInt(2, obj.getCategory().getId());
			statement.setInt(3, obj.getAmount());
			statement.setInt(4, obj.getTotalDuration());
			
			statement.setDouble(5, obj.getTotalAmount());
			
			int rowInserted = statement.executeUpdate();
			
			if (rowInserted > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
	                obj.setId(generatedKeys.getInt(1));
	                
	                RateDAO rateDAO = new RateDAO(this.connect);
	                StateDAO stateDAO = new StateDAO(this.connect);
	                
	                for (Rate rate : obj.getRates()) {
						rate.setLoanId(obj.getId());
						rateDAO.create(rate);
					}
	                
	                for (State state : obj.getStates()) {
						state.setLoanId(obj.getId());
						stateDAO.create(state);
					}
	                
	            }
				
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(Loan obj) {
		return false;
	}

	@Override
	public boolean update(Loan obj) {
		return false;
	}

	@Override
	public Loan find(int id) {
		Loan loan = null;
		
		try {
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			).executeQuery(
					"SELECT * "+
				    "FROM loans LEFT JOIN rates ON loans.id_loan = rates.loan_id "+
					"LEFT JOIN states ON loans.id_loan = states.loan_id "+
					"WHERE loans.id_loan = "+id+";");
			
			if (result.first()) {
				
				loan = new Loan();
				
				loan.setId(id);
				loan.setAmount(result.getInt("amount"));
				loan.setTotalAmount(result.getInt("total_amount"));
				loan.setTotalDuration(result.getInt("total_duration"));
				
				/* Get the category */
				CategoryDAO categoryDAO = new CategoryDAO(this.connect);
				loan.setCategory(categoryDAO.find(result.getInt("category_id")));
				
				/* Get all the rates */
				result.beforeFirst();
				RateDAO rateDAO = new RateDAO(this.connect);
				while (result.next()) {
					loan.addRate(rateDAO.find(result.getInt("id_rate")));
				}
				
				/* Get all the states */
				result.beforeFirst();
				StateDAO stateDAO = new StateDAO(this.connect);
				while (result.next()) {
					loan.addState(stateDAO.find(result.getInt("id_rate")));
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loan;
	}

}
