package com.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.istack.logging.Logger;

public class PostGresCrud {
	// Step 1
	// create a SingletonDesignPattern class.
	// static member holds only one instance of the SingletonDesignPattern class.

	private static final Logger logger = Logger.getLogger(PostGresCrud.class);
	private static PostGresCrud jdbc;
	PostGresConnection postGres = new PostGresConnection();

	// SingletonDesignPattern prevents the instantiation from any other class.
	private PostGresCrud() {
	}

	// Now we are providing gloabal point of access.
	public static PostGresCrud getInstance() {
		if (jdbc == null) {
			jdbc = new PostGresCrud();
			logger.info("Instance is created only one");
		}
		return jdbc;
	}

	// to insert the record into the database
	public int insert(int id, String name, int age, String address, float salary) throws SQLException {
		Connection c = null;

		PreparedStatement ps = null;

		int recordCounter = 0;

		try {

			c = postGres.getPostGresConnectionInstance();
			ps = c.prepareStatement("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)values(?,?,?,?,?)");
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setInt(3, age);
			ps.setString(4, address);
			ps.setFloat(5, salary);
			recordCounter = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return recordCounter;
	}

	// to view the data from the database
	public void view(String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			con = postGres.getPostGresConnectionInstance();
			ps = con.prepareStatement("select * from COMPANY where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				logger.info("Name= " + rs.getString(2) + "\t" + "Age= " + rs.getInt(3));
			}

		} catch (Exception e) {
			logger.info("Error" + e);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	// to update the password for the given username
	public int update(String name, int age) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		int recordCounter = 0;
		try {
			c = postGres.getPostGresConnectionInstance();
			ps = c.prepareStatement(" update company set name=? where Age=? ");
			ps.setString(1, name);
			ps.setInt(2, age);
			recordCounter = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return recordCounter;
	}

	// to delete the data from the database
	public int delete(String name) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int recordCounter = 0;
		try {
			c = postGres.getPostGresConnectionInstance();
			ps = c.prepareStatement(" delete from company where name=? ");
			ps.setString(1, name);
			recordCounter = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (c != null) {
				c.close();
			}
		}
		return recordCounter;
	}

	public void createTable() {

		Statement stmt = null;
		try {
			Connection connection = postGres.getPostGresConnectionInstance();
			stmt = connection.createStatement();
			String sql = "CREATE TABLE COMPANY " + "(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT    NOT NULL, " + " AGE            INT     NOT NULL, "
					+ " ADDRESS        CHAR(50), " + " SALARY         REAL)";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		logger.info("Table created successfully");
	}

}// End of SingletonDesignPattern class