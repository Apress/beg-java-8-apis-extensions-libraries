// BatchUpdateTest.java
package com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.sql.Date;

public class BatchUpdateTest {
	public static void main(String[] args) {
		Connection conn = null;

		try {
			conn = JDBCUtil.getConnection();

			// Prepare the data
			int[] personIds = {801, 901};
			String[] firstNames = {"Matt", "Greg"};
			String[] lastNames = {"Flower", "Rice"};
			String[] genders = {"M", "M"};
			String[] dobString = {"{d '1960-04-01'}",
				"{d '1962-03-01'}"};
			double[] incomes = {56778.00, 89776.00};

			// Use batch update using the Statement objects
			insertPersonStatement(conn, personIds, firstNames,
				lastNames, genders, dobString, incomes);

			// Use batch update using the PreparedStatement objects	
			/*
			 java.sql.Date[] dobDate = {Date.valueOf("1960-04-01"), 
			 Date.valueOf("1962-03-01") };
			 insertPersonPreparedStatement(conn, personIds, 
			 firstNames,lastNames, genders, dobDate, incomes);
			 */
		
			// Commit the transaction  
			JDBCUtil.commit(conn);
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);
		} 
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}

	public static void insertPersonStatement(Connection conn,
		int[] personId,
		String[] firstName, String[] lastName,
		String[] gender, String[] dob,
		double[] income) throws SQLException {

		int[] updatedCount = null;
		Statement stmt = null;

		try {
			stmt = conn.createStatement();
			for (int i = 0; i < personId.length; i++) {
				String SQL = "insert into person " + 
					"(person_id, first_name, last_name," + 
					" gender, dob, income) " +
					"values " +
					"(" + personId[i] + ", " +
					"'" + firstName[i] + "'" + ", " +
					"'" + lastName[i] + "'" + ", " +
					"'" + gender[i] + "'" + ", " +
					dob[i] + ", " +
					income[i] + ")";

				// Add insert command to the batch  
				stmt.addBatch(SQL);
			}

			// Execute the batch  
			updatedCount = stmt.executeBatch();
			System.out.println("Batch executed successfully.");
			printBatchResult(updatedCount);
		} 
		catch (BatchUpdateException e) {
			// Let us see how many commands were successful  
			updatedCount = e.getUpdateCounts();

			System.out.println("Batch failed.");
			int commandCount = personId.length;
			if (updatedCount.length == commandCount) {
				System.out.println(
					"JDBC driver continues to execute all"
					+ " commands in a batch after a failure.");
			} 
			else {
				System.out.println(
					"JDBC driver stops executing subsequent"
					+ " commands in a batch after a failure.");
			}
			
			// Re-throw the exception  
			throw e;
		} 
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}

	public static void insertPersonPreparedStatement(
		Connection conn, int[] personId,
		String[] firstName, String[] lastName,
		String[] gender, java.sql.Date[] dob,
		double[] income) throws SQLException {

		int[] updatedCount = null;
		String SQL = "insert into person " + 
			"(person_id, first_name, last_name, gender, dob," + 
			" income) " + 
			" values " + 
			"(?, ?, ?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL);

			for (int i = 0; i < personId.length; i++) {
				// Set input parameters  
				pstmt.setInt(1, personId[i]);
				pstmt.setString(2, firstName[i]);
				pstmt.setString(3, lastName[i]);
				pstmt.setString(4, gender[i]);
				if (dob[i] == null) {
					pstmt.setNull(5, Types.DATE);
				} 
				else {
					pstmt.setDate(5, dob[i]);
				}

				pstmt.setDouble(6, income[i]);

				// Add insert command with current input parameters  
				pstmt.addBatch();
			}

			// Execute the batch  
			updatedCount = pstmt.executeBatch();
			System.out.println("Batch executed successfully.");
			printBatchResult(updatedCount);
		} 
		catch (BatchUpdateException e) {
			// Let us see how many commands were successful  
			updatedCount = e.getUpdateCounts();
			System.out.println("Batch failed.");
			int commandCount = personId.length;
			if (updatedCount.length == commandCount) {
				System.out.println(
					"JDBC driver continues to execute all" +
					"commands in a batch after a failure.");
			} 
			else {
				System.out.println(
					"JDBC driver stops executing subsequent" +
					"commands in a batch after a failure.");
			}
			
			// Re-throw the exception  
			throw e;
		} 
		finally {
			JDBCUtil.closeStatement(pstmt);
		}
	}

	public static void printBatchResult(int[] updateCount) {
		System.out.println("Batch Results...");
		for (int i = 0; i < updateCount.length; i++) {
			int value = updateCount[i];
			if (value >= 0) {
				System.out.println("Command #" + (i + 1)
					+ ": Success. Update Count=" + value);
			} 
			else if (value >= Statement.SUCCESS_NO_INFO) {
				System.out.println("Command #" + (i + 1)
					+ ": Success. Update Count=Unknown");
			} 
			else if (value >= Statement.EXECUTE_FAILED) {
				System.out.println("Command #" + (i + 1) + ": Failed");
			}
		}
	}
}
