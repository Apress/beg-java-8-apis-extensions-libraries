// JdbcRowSetTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;

public class JdbcRowSetTest {
	public static void main(String[] args) {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();

		// Use a try-with-resources block 
		try (JdbcRowSet jdbcRs = factory.createJdbcRowSet()) {
			// Set the connection parameters
			RowSetUtil.setConnectionParameters(jdbcRs);

			// Set the command and input parameters
			String sqlCommand = "select person_id, first_name, " + 
			                    "last_name from person " + 
			                    "where person_id between ? and ?";
	
			jdbcRs.setCommand(sqlCommand);	    
			jdbcRs.setInt(1, 101);
			jdbcRs.setInt(2, 301);

			// Retrieve the data 
			jdbcRs.execute();

			// Scroll to the last row to get the row count It may throw an 
			// exception if the underlying JdbcRowSet implementation 
			// does not support a bi-directional scrolling result set.
			try {
				jdbcRs.last();
				System.out.println("Row Count: " + jdbcRs.getRow());

				// Position the cursor before the first row 
				jdbcRs.beforeFirst();
			}
			catch(SQLException e) {
				System.out.println("JdbcRowSet implementation" + 
					" supports forward-only scrolling");
			}

			// Print the records in the rowset 
			RowSetUtil.printPersonRecord(jdbcRs);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
