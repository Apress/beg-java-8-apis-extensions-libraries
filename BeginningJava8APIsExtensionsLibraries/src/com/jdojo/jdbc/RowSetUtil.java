// RowSetUtil.java
package com.jdojo.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class RowSetUtil {
	private static boolean driverLoaded = false;

	public static void setConnectionParameters(RowSet rs) throws SQLException {
		// Register the JDBC driver only once for your database
		if (!driverLoaded) {
			// Change the JDBC driver class for your database
			Driver derbyEmbeddedDriver = 
new org.apache.derby.jdbc.EmbeddedDriver();
			DriverManager.registerDriver(derbyEmbeddedDriver);

			driverLoaded = true;
		}

		// Set the rowset database connection properties
		String dbURL = "jdbc:derby:beginningJavaDB;create=true;";
		String userId = "root";
		String password = "chanda";		
		rs.setUrl(dbURL);
		rs.setUsername(userId);
		rs.setPassword(password);
	}
	
	public static RowSetFactory getRowSetFactory() {
		try {
			RowSetFactory factory = RowSetProvider.newFactory();
			return factory;
		} 
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Print person id and name for each person record
	public static void printPersonRecord(RowSet rs) throws SQLException {
		while (rs.next()) {
			int personId = rs.getInt("person_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			System.out.println("Row #" + rs.getRow() + ":" + 
			                   " Person ID:" + personId + 
			                   ", First Name:" + firstName + 
			                   ", Last Name:" + lastName);
		}

		System.out.println();
	}
}
