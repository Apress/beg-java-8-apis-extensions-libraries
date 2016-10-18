// ResultSetRowCountTest.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class ResultSetRowCountTest {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			// Get a Connection  
			conn = JDBCUtil.getConnection();

			// Request a bi-directional scrollable ResultSet  
			stmt = conn.createStatement(TYPE_SCROLL_INSENSITIVE, 
			                            CONCUR_READ_ONLY);
			String SQL = "select person_id, first_name, last_name, dob, " + 
			             "income from person";

			// Execute the query  
			ResultSet rs = stmt.executeQuery(SQL);

			// Make sure you got a bi-directional ReseutSet 
			int cursorType = rs.getType();
			if (cursorType == ResultSet.TYPE_FORWARD_ONLY) {
				System.out.println("JDBC driver returned a " + 
				                   "forward - only cursor.");
			}
			else {
				// Move the cursor to the last row  
				rs.last();

				// Get the last row number, which is the row count  
				int rowCount = rs.getRow();
				System.out.println("Row Count: " + rowCount);

				// Place the cursor before the first row to 
				// process all rows again 
				rs.beforeFirst();
			}

			// Process the result set  
			while (rs.next()) {
				System.out.println("Person ID: " + rs.getInt(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {			
			JDBCUtil.closeStatement(stmt);
			JDBCUtil.commit(conn);
			JDBCUtil.closeConnection(conn);
		}
	}
}
