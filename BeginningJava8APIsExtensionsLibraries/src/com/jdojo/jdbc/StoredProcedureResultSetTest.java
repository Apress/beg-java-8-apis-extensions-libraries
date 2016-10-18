// StoredProcedureResultSetTest.java
package  com.jdojo.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StoredProcedureResultSetTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Print details for person_id 101  
			printPersonDetails(conn, 101);

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

	public static void printPersonDetails(Connection conn, 
		int personId) throws SQLException {
		String SQL = "{ call get_person_details(?) }";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(SQL);

			// Set the IN parameters  
			cstmt.setInt(1, personId);
			ResultSet rs = cstmt.executeQuery();

			// Process the result set  
			QueryPersonTest.printResultSet(rs);
		}
		finally {
			JDBCUtil.closeStatement(cstmt);
		}
	}
}
