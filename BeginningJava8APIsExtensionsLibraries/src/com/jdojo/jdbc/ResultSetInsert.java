// ResultSetInsert.java
package com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.ResultSet.CONCUR_UPDATABLE;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;

public class ResultSetInsert {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Add a new row  
			addRow(conn);

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

	public static void addRow(Connection conn) throws SQLException {
		String SQL = "select person_id, first_name, "
				+ "last_name, gender, dob, income  "
				+ "from person";

		Statement stmt = null;
		try {
			stmt = conn.createStatement(TYPE_FORWARD_ONLY, 
			                            CONCUR_UPDATABLE);

			// Get the result set  
			ResultSet rs = stmt.executeQuery(SQL);

			// Make sure your resultset is updatable  
			int concurrency = rs.getConcurrency();

			if (concurrency != ResultSet.CONCUR_UPDATABLE) {
				System.out.println("The JDBC driver does not " +
					"support updatable result sets.");
				return;
			}

			// First insert a new row to the ResultSet  
			rs.moveToInsertRow();
			rs.updateInt("person_id", 501);
			rs.updateString("first_name", "Richard");
			rs.updateString("last_name", "Castillo");
			rs.updateString("gender", "M");

			// Send the new row to the database  
			rs.insertRow();

			// Move back to the current row 
			rs.moveToCurrentRow();

			// Print all rows in the result set 
			while (rs.next()) {
				System.out.print("Person ID: " + 
					rs.getInt("person_id") +
					", First Name: " + 
					rs.getString("first_name") +
					", Last Name: " + 
					rs.getString("last_name"));
				System.out.println();
			}
		} 
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}
}
