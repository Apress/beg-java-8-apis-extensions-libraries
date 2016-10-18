// OracleStoredProcedureResultSetTest.java
package com.jdojo.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleStoredProcedureResultSetTest {
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
		String sql = "{ call get_person_details(?, ?) }";
		CallableStatement cstmt = null;
		try {
			cstmt = conn.prepareCall(sql);

			// Set the IN parameters  
			cstmt.setInt(1, personId);

			  
			/* Uncomment the following statement after you have
   the Oracle JDBC driver in CLASSPATH.
			   Register the second parameter as an OUT parameter
			   which will return the REF CURSOR (the ResultSet) */
			//cstmt.registerOutParameter(2, 
			//	 oracle.jdbc.OracleTypes.CURSOR);

			// Execute the stored procedure  
			cstmt.execute();

			// Get the result set from the OUT parameter  
			ResultSet rs = (ResultSet) cstmt.getObject(2);

			// Process the result set  
			QueryPersonTest.printResultSet(rs);
		} 
		finally {
			JDBCUtil.closeStatement(cstmt);
		}
	}
}
