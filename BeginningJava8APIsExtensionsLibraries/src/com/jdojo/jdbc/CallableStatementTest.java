// CallableStatementTest.java
package  com.jdojo.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Give a 5% raise to person_id 101  
			giveRaise(conn, 102, 5.0);

			// Give a 5% raise to dummy person_id  
			giveRaise(conn, -100, 5.0);

			// Commit the transaction  
			JDBCUtil.commit(conn);
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);
		}
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}

	public static void giveRaise(Connection conn, int personId,
					  double raise) throws SQLException {
		String SQL = "{call app.give_raise (?, ?, ?, ?)}";
		CallableStatement cstmt = null;
		try {
			// Prepare the call  
			cstmt = conn.prepareCall(SQL);

			// Set the IN parameters  
			cstmt.setInt(1, personId);
			cstmt.setDouble(2, raise);

			// Register the OUT parameters  
			cstmt.registerOutParameter(3, Types.DOUBLE);
			cstmt.registerOutParameter(4, Types.DOUBLE);

			// Execute the stored procedure  
			int updatedCount = cstmt.executeUpdate();

			// Read the OUT parameters values  
			double oldIncome = cstmt.getDouble(3);
			boolean oldIncomeisNull = cstmt.wasNull();

			double newIncome = cstmt.getDouble(4);
			boolean newIncomeisNull = cstmt.wasNull();

			// Display the results  
			System.out.println("Updated Record: " + updatedCount);

			System.out.println("Old Income: " + oldIncome + 
			                   ", New Income: " + newIncome);

			System.out.println("Old Income was null: " + 
			                   oldIncomeisNull + 
			                   ", New Income is null: " + 
			                   newIncomeisNull);
		}
		finally {
			JDBCUtil.closeStatement(cstmt);
		}
	}
}
