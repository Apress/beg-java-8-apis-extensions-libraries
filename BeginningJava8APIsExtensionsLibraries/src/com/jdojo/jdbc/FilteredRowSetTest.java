// FilteredRowSetTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.Predicate;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.RowSetFactory;

public class FilteredRowSetTest {
	public static void main(String[] args) {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();

		// Use a try-with-resources block 
		try (FilteredRowSet filteredRs
			= factory.createFilteredRowSet()) {
			// Set the connection parameters
			RowSetUtil.setConnectionParameters(filteredRs);

			// Prepare, set, and execute the command
			String sqlCommand= "select person_id, first_name, last_name " +
			                   "from person";
			filteredRs.setCommand(sqlCommand);
			filteredRs.execute();

			// Print the retrieved records 
			System.out.println("Before Filter - Row count: " +
				filteredRs.size());
			RowSetUtil.printPersonRecord(filteredRs);

			// Set a filter 
			Predicate filter = new RangeFilter(1, "person_id", 101, 102);
			filteredRs.setFilter(filter);

			// Print the retrieved records 			
			System.out.println("After Filter - Row count: " +
				filteredRs.size());
			filteredRs.beforeFirst();
			RowSetUtil.printPersonRecord(filteredRs);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
