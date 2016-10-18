// InsertPersonTest.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertPersonTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			
			// Insert 3 person records
			insertPerson(conn, 101, "John", "Jacobs",
			             "M", "{d '1970-01-01'}", 60000);
			insertPerson(conn, 102, "Donna", "Duncan",
			             "F", "{d '1960-01-01'}", 70000);
			insertPerson(conn, 103, "Buddy", "Rice",
			             "M", "{d '1975-01-01'}", 45000);

			// Commit the transaction  
			JDBCUtil.commit(conn);

			System.out.println("Inserted persons successfully.");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);			
		}
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}

	public static void insertPerson(Connection conn, int personId,
		String firstName, String lastName, String gender, String dob,
		double income) throws SQLException {
		
		// Create a SQL string  
		String SQL = "insert into person " + 
		             "(person_id, first_name, last_name," + 
		             " gender, dob, income) " + 
		             "values " + 
		             "(" + personId + ", " + 
		              "'" + firstName + "'" + ", " + 
		              "'" + lastName + "'" + ", " + 
		              "'" + gender + "'" + ", " + 
		              dob + ", " + 
		              income + ")";

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(SQL);
		}
		finally {
			JDBCUtil.closeStatement(stmt);
		}
	}
}
