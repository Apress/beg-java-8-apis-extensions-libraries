// CreatePersonDetailTable.java
package com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatePersonDetailTable {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Create a SQL string
			String SQL = "create table person_detail( " +
				"person_detail_id integer not null, " +
				"person_id integer not null, " +
				"picture blob, " +
				"resume clob, " +
				"primary key (person_detail_id), " +
				"foreign key (person_id) references person(person_id))";
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(SQL);
			} 
			finally {
				JDBCUtil.closeStatement(stmt);
			}
			
			// Commit the transaction  
			JDBCUtil.commit(conn);
			System.out.println("Person table created successfully.");
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);
		} 
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}
}
