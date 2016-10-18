// PreparedStatementTest.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PreparedStatementTest {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = JDBCUtil.getConnection();
			pstmt = getInsertSQL(conn);

			// Need to get dob in java.sql.Date object  
			Date dob = Date.valueOf("1970-01-01");

			// Insert two person records  
			insertPerson(pstmt, 401, "Sara", "Jain", "F", dob, 0.0);
			insertPerson(pstmt, 501, "Su", "Chi", "F", null, 10000.0);

			// Commit the transaction  
			JDBCUtil.commit(conn);
			
			System.out.println("Updated person records successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);			
		}
		finally {
			JDBCUtil.closeStatement(pstmt);
			JDBCUtil.closeConnection(conn);
		}
	}

	public static void insertPerson(PreparedStatement pstmt, 
		int personId, String firstName, String lastName,
		String gender, Date dob, double income) throws SQLException {
		// Set all the input parameters  
		pstmt.setInt(1, personId);
		pstmt.setString(2, firstName);
		pstmt.setString(3, lastName);
		pstmt.setString(4, gender);

		// Set the dob value properly if it is null  
		if (dob == null) {
			pstmt.setNull(5, Types.DATE);
		} 
		else {
			pstmt.setDate(5, dob);
		}

		pstmt.setDouble(6, income);

		// Execute the statement  
		pstmt.executeUpdate();
	}

	public static PreparedStatement getInsertSQL(Connection conn) 
throws SQLException {
		String SQL = "insert into person " + 
		  "(person_id, first_name, last_name, gender, dob, income) " + 
		  "values " + 
		  "(?, ?, ?, ?, ?, ?)"; 
		PreparedStatement pstmt = conn.prepareStatement(SQL);
		return pstmt;
	}
}
