package com.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingletonDesignPattern {
	// Step 1
	// create a SingletonDesignPattern class.
	// static member holds only one instance of the SingletonDesignPattern class.

	private static SingletonDesignPattern jdbc;

	// SingletonDesignPattern prevents the instantiation from any other class.
	private SingletonDesignPattern() {
	}

	// Now we are providing gloabal point of access.
	public static SingletonDesignPattern getInstance() {
		if (jdbc == null) {
			jdbc = new SingletonDesignPattern();
			System.out.println("Instance is created only one");
		}
		return jdbc;
	}

	// to get the connection from methods like insert, view etc.
	private static Connection getConnection() throws ClassNotFoundException, SQLException {

		Connection con = null;
		Class.forName("org.postgresql.Driver");
		con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin");
		return con;

	}

	// to insert the record into the database
	public int insert(String name, String place) throws SQLException {
		Connection c = null;

		PreparedStatement ps = null;

		int recordCounter = 0;

		try {

			c = this.getConnection();
			ps = c.prepareStatement("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY)values(?,?,?,?,?)");
			ps.setInt(1, 2);
			ps.setString(2, name);
			ps.setInt(3, 3);
			ps.setString(4, place);
			ps.setFloat(5, 0f);
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

			con = this.getConnection();
			ps = con.prepareStatement("select * from COMPANY where name=?");
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println("Name= " + rs.getString(2) + "\t" + "Age= " + rs.getInt(3));

			}

		} catch (Exception e) {
			System.out.println(e);
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
	public int update(String name, String password) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;

		int recordCounter = 0;
		try {
			c = this.getConnection();
			ps = c.prepareStatement(" update company set name=? where Age='" + 1 + "' ");
			ps.setString(1, "shanu123");
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
	public int delete(int userid) throws SQLException {
		Connection c = null;
		PreparedStatement ps = null;
		int recordCounter = 0;
		String userids = "shanu";
		try {
			c = this.getConnection();
			ps = c.prepareStatement(" delete from company where name='" + userids + "' ");
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
			Connection connection = this.getConnection();
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
		System.out.println("Table created successfully");
	}

}// End of SingletonDesignPattern class