// JdbcRowSetUpdateTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;

public class JdbcRowSetUpdateTest {
	public static void main(String[] args) {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();

		// Use a try-with-resources block 
		try (JdbcRowSet jdbcRs = factory.createJdbcRowSet()) {
			// Set the connection parameters
			RowSetUtil.setConnectionParameters(jdbcRs);
			
			// Set the auto-commit mode to false
			jdbcRs.setAutoCommit(false);

			// Set the command and input parameters
			String sqlCommand = "select person_id, first_name, " + 
			                    "last_name, income from person " + 
			                    "where person_id = ?";
			jdbcRs.setCommand(sqlCommand);
			jdbcRs.setInt(1, 101);

			// Retrieve the data 
			jdbcRs.execute();

			// If a row is retrieved, update the first row's income 
			// column to 65000.00 
			if (jdbcRs.next()) {
				int personId = jdbcRs.getInt("person_id");
				jdbcRs.updateDouble("income", 65000.00);
				jdbcRs.updateRow();

				// Commit the changes 
				jdbcRs.commit();

				System.out.println("Income has been set to " + 
					"65000.00 for person_id=" + personId);
			}
			else {
				System.out.println("No person record was found.");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
