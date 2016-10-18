// DatabaseMetaDataTest.java
package  com.jdojo.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;

public class DatabaseMetaDataTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();

			// Get DatabaseMetaData object  
			DatabaseMetaData dbmd = conn.getMetaData();

			System.out.println("About the database...");

			String dbName = dbmd.getDatabaseProductName();
			String dbVersion = dbmd.getDatabaseProductVersion();
			String dbURL = dbmd.getURL();
			System.out.println("Database Name:" + dbName);
			System.out.println("Database Version:" + dbVersion);
			System.out.println("Database URL:" + dbURL);
			
			System.out.printf("%nAbout JDBC driver...%n");
			String driverName = dbmd.getDriverName();
			String driverVersion = dbmd.getDriverVersion();
			System.out.println("Driver Name:" + driverName);
			System.out.println("Driver Version:" + driverVersion);

			System.out.printf("%nAbout supported features...%n");
			boolean ansi92BiEntry = dbmd.supportsANSI92EntryLevelSQL();
			boolean ansi92Intermediate = 
				dbmd.supportsANSI92IntermediateSQL();
			boolean ansi92Full = dbmd.supportsANSI92FullSQL();
			boolean supportsBatchUpdates = dbmd.supportsBatchUpdates();
			System.out.println("Supports Entry Level ANSI92 SQL:" + 
			                   ansi92BiEntry);
			System.out.println("Supports Intermediate Level ANSI92 SQL:" + 
			           ansi92Intermediate);
			System.out.println("Supports Full Level ANSI92 SQL:" + 
			                    ansi92Full);
			System.out.println("Supports batch updates:" + 
			                   supportsBatchUpdates);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}
}
