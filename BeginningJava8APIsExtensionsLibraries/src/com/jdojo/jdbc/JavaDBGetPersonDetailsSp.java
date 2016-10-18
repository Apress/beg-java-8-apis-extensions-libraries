// JavaDBGetPersonDetailsSp.java
package com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JavaDBGetPersonDetailsSp {
	public static void getPersonDetails(int personId,
			ResultSet[] personDetailRs) throws SQLException {
		
		// Must use the following URL to get the reference of 
		// the Conenction object in whose context this method 
		// is called.
		String dbURL = "jdbc:default:connection";
		Connection conn = DriverManager.getConnection(dbURL);
		
		String sql = "select person_id, first_name, " + 
		             "last_name, gender, dob, income " + 
		             "from person " + 
		             "where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, personId);
		ResultSet rs = pstmt.executeQuery();
		personDetailRs[0] = rs;

		/* Do not close pstmt or rs here. They are meant to be 
		   procssed and closed by the caller of this stored 
		   procedure. 
		*/
	}
}
