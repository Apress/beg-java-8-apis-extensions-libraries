// ResultSetMetaDataTest.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			String SQL = "select person_id as \"Person ID\", " + 
			             "first_name as \"First Name\", " + 
			             "gender as Gender, " + 
			             "dob as \"Birth Date\", " + 
			             "income as Income " + 
			             "from person";

			// Print the reSult set matadata
			printMetaData(conn, SQL);
			
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
	public static void printMetaData(Connection conn, String SQL) 
			throws SQLException {
		Statement stmt = conn.createStatement();
		try {
			ResultSet rs = stmt.executeQuery(SQL);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			System.out.println("Column Count:" + columnCount);

			for (int i = 1; i <= columnCount; i++) {
				System.out.println("Index:" + i +
					", Name:" + rsmd.getColumnName(i) +
					", Label:" + rsmd.getColumnLabel(i) +
					", Type Name:" + rsmd.getColumnTypeName(i) + 
					", Class Name:" + rsmd.getColumnClassName(i));
			}
		}
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}
}
