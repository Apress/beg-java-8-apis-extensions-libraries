// JavaDBGiveRaiseSp.java
package com.jdojo.jdbc;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class JavaDBGiveRaiseSp {
	public static void giveRaise(int personId, double raise,
			double[] oldIncomeOut, double[] newIncomeOut) throws SQLException {
		double oldIncome = 0.0;
		double newIncome = 0.0;

		// Must use the following URL to get the reference of the Conenction 
		// object in whose context this method is called.
		String dbURL = "jdbc:default:connection";
		Connection conn = DriverManager.getConnection(dbURL);

		String sql = "select income from person where person_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, personId);

		ResultSet rs = pstmt.executeQuery();
		if (!rs.next()) {
			return;
		}

		oldIncome = rs.getDouble("income");
		if (rs.wasNull()) {
			newIncome = 20000.00;
		} 
		else {
			newIncome = oldIncome * (1 + raise / 100);
		}

		String updateSql = "update person " +
		                   "set income = ? " +
		                   "where person_id = ?";
		PreparedStatement updateStmt = 
			conn.prepareStatement(updateSql);
		updateStmt.setDouble(1, newIncome);
		updateStmt.setInt(2, personId);
		updateStmt.executeUpdate();

		// Close the statement
		updateStmt.close();

		oldIncomeOut[0] = oldIncome;
		newIncomeOut[0] = newIncome;
	}
}
