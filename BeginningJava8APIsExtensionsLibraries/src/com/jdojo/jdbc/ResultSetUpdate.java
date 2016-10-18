// ResultSetUpdate.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_UPDATABLE;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetUpdate {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Give everyone a 10% raise  
			giveRaise(conn, 10.0);

			// Commit the transaction  
			JDBCUtil.commit(conn);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);
			e.printStackTrace();
		}
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}

	public static void giveRaise(Connection conn,
	                             double raise) throws SQLException {
		String SQL = "select person_id, first_name, last_name, " +  
		             "income from person";

		Statement stmt = null;

		try {
			stmt = conn.createStatement(TYPE_FORWARD_ONLY,
			                            CONCUR_UPDATABLE);

			// Get the result set  
			ResultSet rs = stmt.executeQuery(SQL);

			// Make sure our resultset is updatable  
			int concurrency = rs.getConcurrency();

			if (concurrency != CONCUR_UPDATABLE) {
				System.out.println("The JDBC driver does not "+ 
					"support updatable result sets.");
				return;
			}

			// Give everyone a raise  
			while (rs.next()) {
				double oldIncome = rs.getDouble("income");
				double newIncome = 0.0;

				if (rs.wasNull()) {
					// null income starts at 10000.00
					oldIncome = 10000.00;
					newIncome = oldIncome;
				}
				else {
					// Increase the income  
					newIncome = 
					oldIncome + oldIncome * (raise / 100.0);
				}

				// Update the income column with the new value
				rs.updateDouble("income", newIncome);

				// Print the details about the changes  
				int personId = rs.getInt("person_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");

				System.out.println(firstName + " " + lastName + 
					" (person id=" + personId + 
					") income changed from " + 
					oldIncome + " to " + newIncome);
								
				// Send the changes to the database 
				rs.updateRow();
			}
		}
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}
}
