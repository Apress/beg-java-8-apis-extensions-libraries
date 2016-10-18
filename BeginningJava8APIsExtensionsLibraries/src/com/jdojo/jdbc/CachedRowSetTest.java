// CachedRowSetTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;

public class CachedRowSetTest {
	public static void main(String[] args) {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();

		// Use a try-with-resources block 
		try (CachedRowSet cachedRs = factory.createCachedRowSet()) {
			// Set the connection parameters 
			RowSetUtil.setConnectionParameters(cachedRs);

			String sqlCommand = "select person_id, first_name, last_name " + 
			                    "from person " +
			                    "where person_id between 101 and 501";

			cachedRs.setCommand(sqlCommand);
			cachedRs.execute();

			// Print the records in cached rowset	     
			System.out.println("Row Count: " + cachedRs.size());
			RowSetUtil.printPersonRecord(cachedRs);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
