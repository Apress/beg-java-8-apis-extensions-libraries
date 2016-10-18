// CachedRowSetUpdateTest.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.spi.SyncProviderException;
import javax.sql.rowset.spi.SyncResolver;
import static javax.sql.rowset.spi.SyncResolver.DELETE_ROW_CONFLICT;
import static javax.sql.rowset.spi.SyncResolver.INSERT_ROW_CONFLICT;
import static javax.sql.rowset.spi.SyncResolver.UPDATE_ROW_CONFLICT;

public class CachedRowSetUpdateTest {
	public static void main(String[] args) throws SQLException {
		RowSetFactory factory = RowSetUtil.getRowSetFactory();
		CachedRowSet cachedRs = factory.createCachedRowSet();

		try {
			// Set the connection parameters for the CachedRowSet 
			RowSetUtil.setConnectionParameters(cachedRs);

			String sqlCommand = "select person_id, first_name, last_name, "
				+ "gender, dob, income "
				+ "from person "
				+ "where person_id between 101 and 301";

			cachedRs.setKeyColumns(new int[]{1});

			cachedRs.setCommand(sqlCommand);
			cachedRs.execute();

			// Print the records in the cached rowset 
			System.out.println("Before Update");
			System.out.println("Row Count: " + cachedRs.size());
			RowSetUtil.printPersonRecord(cachedRs);

			// Update income to 23000.00 for the first row 
			if (cachedRs.size() > 0) {
				updateRow(cachedRs, 1, 23000.00);
			}

			// Insert a new row 
			insertNewRow(cachedRs);

			// Send changes to the database 
			cachedRs.acceptChanges();

			System.out.println("After Update");
			System.out.println("Row Count: " + cachedRs.size());
			cachedRs.beforeFirst();
			RowSetUtil.printPersonRecord(cachedRs);
		} 
		catch (SyncProviderException spe) {
			// When acceptChanges() detects some conflicts 
			SyncResolver resolver = spe.getSyncResolver();

			// Print the details about the conflicts 
			printConflicts(resolver, cachedRs);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			if (cachedRs != null) {
				try {
					cachedRs.close();
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void insertNewRow(CachedRowSet cachedRs) throws SQLException {
		// Move cursor to the insert-row 
		cachedRs.moveToInsertRow();

		// Set the values for columns in the new row 
		cachedRs.updateInt("person_id", 751);
		cachedRs.updateString("first_name", "Mason");
		cachedRs.updateString("last_name", "Baker");
		cachedRs.updateString("gender", "M");
		cachedRs.updateDate("dob", java.sql.Date.valueOf("2006-01-02"));
		cachedRs.updateDouble("income", 0.00);

		// Insert the new row in the rowset. It is not sent to the 
		// database, until the acceptChanges() method is called 
		cachedRs.insertRow();

		// Must move back to the current row 
		cachedRs.moveToCurrentRow();
	}

	public static void updateRow(CachedRowSet cachedRs, int row, double newIncome)
		throws SQLException {
		// Set the values for columns in the new row 
		cachedRs.absolute(row);
		cachedRs.updateDouble("income", newIncome);
		cachedRs.updateRow();
	}

	public static void printConflicts(SyncResolver resolver, CachedRowSet cachedRs) {
		try {
			while (resolver.nextConflict()) {
				int status = resolver.getStatus();
				String operation = "None";
				if (status == INSERT_ROW_CONFLICT) {
					operation = "insert";
				} 
				else if (status == UPDATE_ROW_CONFLICT) {
					operation = "update";
				} 
				else if (status == DELETE_ROW_CONFLICT) {
					operation = "delete";
				}

				// Get person_id from the database 
				Object oldPersonId
					= resolver.getConflictValue("person_id");

				// Get person ID from the cached rowset 
				int row = resolver.getRow();
				cachedRs.absolute(row);
				Object newPersonId = cachedRs.getObject("person_id");

				// Use setResolvedValue() method to set resolved value 
				// for a column 
				// resolver.setResolvedValue(columnName,resolvedValue); 
				System.out.println("Conflict detected in row #"
					+ row
					+ " during " + operation + " operation."
					+ " person_id in database is " + oldPersonId
					+ " and person_id in rowset is " + newPersonId);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
