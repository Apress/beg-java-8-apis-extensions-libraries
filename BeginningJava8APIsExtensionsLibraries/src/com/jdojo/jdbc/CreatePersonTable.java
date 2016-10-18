// CreatePersonTable.java
package com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreatePersonTable {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Create a SQL string
			String SQL = "create table person ( " +
			             "person_id integer not null, " +
			             "first_name varchar(20) not null, " +
			             "last_name varchar(20) not null, " +
			             "gender char(1) not null, " +
			             "dob date , " +
			             "income double," +
			             "primary key(person_id))";

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

			System.out.println("Person table created.");
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
