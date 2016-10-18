// SavePointTest.java
package com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Savepoint;

public class SavePointTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			// Connect to the database  
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);

			// SQL Statement  
			String SQL = "update person " +
			             "set income = ? " + 
			             "where person_id = ?";

			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setDouble(1, 20000);
			pstmt.setInt(2, 101);
			pstmt.execute();

			// Set a save point  
			Savepoint sp1 = conn.setSavepoint();

			// Change the income to 25000 and execute the SQL again  
			pstmt.setDouble(1, 25000);
			pstmt.execute();

			// Set a save point  
			Savepoint sp2 = conn.setSavepoint();

			// Perform some more database changes here  
			// Roll back the transaction to the save point sp1,
			// so that income for person_id 101 will remain set 
			// to 20000 and not the 25000  
			conn.rollback(sp1);

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
}
