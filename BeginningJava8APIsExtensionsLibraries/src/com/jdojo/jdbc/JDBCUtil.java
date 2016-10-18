// JDBCUtil.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() throws SQLException {
		// Register the Java DB embedded JDBC driver
		Driver derbyEmbeddedDriver = new org.apache.derby.jdbc.EmbeddedDriver();
		DriverManager.registerDriver(derbyEmbeddedDriver);

		// Construct the connection URL		
		String dbURL = "jdbc:derby:beginningJavaDB;create=true;";
		String userId = "root";
		String password = "chanda";

		// Get a connection 
		Connection conn = DriverManager.getConnection(dbURL, userId, password);

		// Set the auto-commit off  
		conn.setAutoCommit(false);

		return conn;
	}

	public static void closeConnection(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeStatement(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			System.out.println("Connetced to the database.");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}
}
