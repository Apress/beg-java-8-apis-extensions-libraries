// DeletePersonTest.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeletePersonTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Delete the person with person_id = 101  
			deletePerson(conn, 101);

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

	public static void deletePerson(Connection conn, int personId) 
			throws SQLException {
		String SQL = "delete from person " + 
		             "where person_id = " + personId;
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			int deletedCount = stmt.executeUpdate(SQL);

			// Print how many persons were deleted  
			System.out.println("Deleted " + 
			                   deletedCount + " person(s).");
		}
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}
}
