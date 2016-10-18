// CachedRowSetPagingTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;

public class CachedRowSetPagingTest {
	public static void main(String[] args) {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();
	
		// Use a try-with-resources block 
		try (CachedRowSet cachedRs = factory.createCachedRowSet()) {	
			// Set the connection parameters 
			RowSetUtil.setConnectionParameters(cachedRs);

			// Set the command and the page size
			String sqlCommand = "select person_id, first_name, last_name " + 
			                    "from person";
			cachedRs.setCommand(sqlCommand);
			cachedRs.setPageSize(2); // page size is 2 
			
			// Execute the command
			cachedRs.execute();

			int pageCounter = 1;
	
			// Retrieve and print person records one page at a time 
			do {			
				System.out.println("Page #" + pageCounter + 
	 				" (Row Count=" + cachedRs.size() + ")");

				// Print the record in the current page 
				RowSetUtil.printPersonRecord(cachedRs);
		
				// Increment the page count by 1 
				pageCounter++;	       
			} 
			while (cachedRs.nextPage());	    
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
