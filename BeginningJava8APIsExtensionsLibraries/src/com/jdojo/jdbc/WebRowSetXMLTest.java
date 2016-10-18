// WebRowSetXMLTest.java
package com.jdojo.jdbc;

import java.io.StringWriter;
import java.sql.SQLException;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.WebRowSet;

public class WebRowSetXMLTest {
	public static void main(String[] args) {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();
	
		// Use a try-with-resources block 
		try (WebRowSet webRs = factory.createWebRowSet()) {
			// Set the connection parameters for the WebRowSet 
			RowSetUtil.setConnectionParameters(webRs);

			String sqlCommand = "select person_id, first_name, last_name " + 
			                    "from person " + 
			                    "where person_id between ? and ?";

			webRs.setCommand(sqlCommand);
			webRs.setInt(1, 101);
			webRs.setInt(2, 102);
			webRs.execute();

			// Change the last name for the first record 
			if (webRs.first()) {
				webRs.updateString("last_name", "Who knows?");
			}
	
			// Get the XML representation of of the WebRowSet 
			StringWriter sw = new StringWriter();
			webRs.writeXml(sw);
			String webRsXML = sw.toString();
	
			// Print the exported XML from the WebRowSet 
			System.out.println(webRsXML);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
