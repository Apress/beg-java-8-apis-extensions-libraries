// LOBTest.java
package com.jdojo.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LOBTest {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			
			// Insert a record in the person_detail table. Files
			// picture.jpg and resume.txt are assumed to be in 
			// the working directory
			String inPicturePath = "picture.jpg";
			String inResumePath = "resume.txt";
			
			// Make sure that the files exist
			ensureFileExistence(inPicturePath);
			ensureFileExistence(inResumePath);
			
			try {
				// Insert a person_detail record
				insertPersonDetail(conn, 1, 101, 
					inPicturePath, inResumePath);
				
				// Commit the transaction  
				JDBCUtil.commit(conn);
				
				System.out.println(
					"Inserted person details successfully");
			}
			catch(SQLException e) {
				System.out.print("Inserting person details failed: ");
				System.out.println(e.getMessage());
				JDBCUtil.rollback(conn);
			}
			
			// These files will be created in the current directory
			String outPicturePath = "out_picture.jpg";
			String outResumePath = "out_resume.txt";
			
			try {
				// Read the person_detail record
				retrievePersonDetails(conn, 1, 
					outPicturePath,	outResumePath);

				// Commit the transaction  
				JDBCUtil.commit(conn);
				
				System.out.println(
				"Retrieved and saved person details successfully.");
			}
			catch(SQLException e) {
				System.out.print("Retrieving person details failed: ");
				System.out.println(e.getMessage());
				JDBCUtil.rollback(conn);
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
			JDBCUtil.rollback(conn);
		} 
		finally {
			JDBCUtil.closeConnection(conn);
		}
	}

	public static void insertPersonDetail(Connection conn,
		int personDetailId,
		int personId,
		String pictureFilePath,
		String resumeFilePath)
		throws SQLException {
				
		String SQL = "insert into person_detail " +
			"(person_detail_id, person_id, picture, resume) " +
			"values " +
			"(?, ?, ?, ?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, personDetailId);
			pstmt.setInt(2, personId);

			// Set the picture data  
			if (pictureFilePath != null) {
				// We need to create a Blob object first  
				Blob pictureBlob = conn.createBlob();
				readInPictureData(pictureBlob, pictureFilePath);
				pstmt.setBlob(3, pictureBlob);
			}

			// Set the resume data  
			if (resumeFilePath != null) {
				// We need to create a Clob object first  
				Clob resumeClob = conn.createClob();
				readInResumeData(resumeClob, resumeFilePath);
				pstmt.setClob(4, resumeClob);
			}

			pstmt.executeUpdate();
		}
		catch (IOException | SQLException e) {			
			throw new SQLException(e);
		} 
		finally {
			JDBCUtil.closeStatement(pstmt);
		}
	}

	public static void retrievePersonDetails(Connection conn,
		int personDetailId,
		String picturePath,
		String resumePath) throws SQLException {

		String SQL = "select person_id, picture, resume " + 
		             "from person_detail " + 
		             "where person_detail_id = ?";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, personDetailId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int personId = rs.getInt("person_id");
				Blob pictureBlob = rs.getBlob("picture");
				if (pictureBlob != null) {
					savePicture(pictureBlob, picturePath);
					pictureBlob.free();
				}

				Clob resumeClob = rs.getClob("resume");
				if (resumeClob != null) {
					saveResume(resumeClob, resumePath);
					resumeClob.free();
				}
			}
		} 
		catch (IOException | SQLException e) {			
			throw new SQLException(e);
		} 
		finally {
			JDBCUtil.closeStatement(pstmt);
		}
	}

	public static void readInPictureData(Blob pictureBlob,
		String pictureFilePath)
		throws FileNotFoundException, IOException, SQLException {

		// Get the output stream of the Blob object to write  
		// the picture data to it.  
		int startPosition = 1; // start writing from the beginning  
		OutputStream out = pictureBlob.setBinaryStream(startPosition);

		FileInputStream fis = new FileInputStream(pictureFilePath);

		// Read from the file and write to the Blob object  
		int b = -1;
		while ((b = fis.read()) != -1) {
			out.write(b);
		}

		fis.close();
		out.close();
	}

	public static void readInResumeData(Clob resumeClob,
		String resumeFilePath)
		throws FileNotFoundException, IOException, SQLException {

		// Get the character output stream of the Clob object 
		// to write the resume data to it.  
		int startPosition = 1; // start writing from the beginning  
		Writer writer = resumeClob.setCharacterStream(startPosition);
		FileReader fr = new FileReader(resumeFilePath);

		// Read from the file and write to the Clob object  
		int b = -1;
		while ((b = fr.read()) != -1) {
			writer.write(b);
		}
		fr.close();
		writer.close();
	}

	public static void savePicture(Blob pictureBlob, String filePath)
		throws SQLException, IOException {
		FileOutputStream fos = new FileOutputStream(filePath);
		InputStream in = pictureBlob.getBinaryStream();

		int b = -1;
		while ((b = in.read()) != -1) {
			fos.write((byte) b);
		}

		fos.close();
	}

	public static void saveResume(Clob resumeClob, String filePath)
		throws SQLException, IOException {
		FileWriter fw = new FileWriter(filePath);
		Reader reader = resumeClob.getCharacterStream();

		int b = -1;
		while ((b = reader.read()) != -1) {
			fw.write((char) b);
		}

		fw.close();
	}
	
	public static void ensureFileExistence(String filePath) {
		Path path = Paths.get(filePath);
		if (!Files.exists(path)) {
			throw new RuntimeException("File " + 
				path.toAbsolutePath() + " does not exist");
		}
	}
}
