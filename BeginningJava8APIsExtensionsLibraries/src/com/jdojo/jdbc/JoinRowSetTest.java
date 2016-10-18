// JoinRowSetTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;

public class JoinRowSetTest {
	public static void main(String[] args) {		
		RowSetFactory factory = RowSetUtil.getRowSetFactory();
	
		// Use a try-with-resources block 
		try (CachedRowSet cachedRs1 = factory.createCachedRowSet();
			 CachedRowSet cachedRs2 = factory.createCachedRowSet();
			 JoinRowSet joinRs = factory.createJoinRowSet() ) {			
			// Set the connection parameters 
			RowSetUtil.setConnectionParameters(cachedRs1);
			RowSetUtil.setConnectionParameters(cachedRs2);

			String sqlCommand1 = "select person_id, first_name " + 
			                     "from person " + 
			                     "where person_id in (101, 102)";

			String sqlCommand2 = "select person_id, last_name " + 
			                     "from person " + 
			                     "where person_id in (101, 102, 103)";
	    
			cachedRs1.setCommand(sqlCommand1);
			cachedRs2.setCommand(sqlCommand2);
	    
			cachedRs1.execute();
			cachedRs2.execute();
	
			// Create a JoinRowSet for cachedRs1 and cachedRs2 
			// joining them based on the person_id column			
			joinRs.addRowSet(cachedRs1, "person_id");
			joinRs.addRowSet(cachedRs2, "person_id");
	
			System.out.println("Row Count: "  + joinRs.size());	 
			RowSetUtil.printPersonRecord(joinRs);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
