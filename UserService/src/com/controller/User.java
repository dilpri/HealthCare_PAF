package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {

	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/*
	 * public String insertUser(String username, String password, String email,
	 * String address, String phoneNo, String age, String sex) { String output = "";
	 * try { Connection con = connect();
	 * 
	 * if (con == null) { return
	 * "Error while connecting to the database for inserting"; }
	 * 
	 * String query =
	 * "insert into user ('userID','username', 'password', 'email', 'address', 'phoneNo', 'age', 'sex')"
	 * + "values (?,?,?,?,?,?,?,?)";
	 * 
	 * PreparedStatement ps = con.prepareStatement(query);
	 * 
	 * ps.setInt(1, 0); ps.setString(2, username); ps.setString(3, password);
	 * ps.setString(4, email); ps.setString(5, address); ps.setString(6, phoneNo);
	 * ps.setString(7, age); ps.setString(8, sex); ps.execute(); con.close();
	 * 
	 * output = "Inserted Successfully";
	 * 
	 * } catch (Exception e) { output = "Error while inserting the user";
	 * System.err.println(e.getMessage()); } return output; }
	 */

	// Insert
	public String insertUser(String username, String password, String email, String address, String phoneNo, String age,
			String sex, String userType) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user (`userID`,`username`,`password`,`email`,`address`,`phoneNo`,`age`,`sex`,`userType`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, username);
			preparedStmt.setString(3, password);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, address);
			preparedStmt.setString(6, phoneNo);
			preparedStmt.setString(7, age);
			preparedStmt.setString(8, sex);
			preparedStmt.setString(9, userType);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUsers() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting the database for reading";
			}

			output = "<table border=\"1\"><tr><th>User name</th><th>Password</th><th>Email</th><th>Address</th><th>Phone No</th><th>Age</th><th>Sex</th><th>User Type</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String userID = Integer.toString(rs.getInt("userID"));
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String phoneNo = rs.getString("phoneNo");
				String age = rs.getString("age");
				String sex = rs.getString("sex");
				String userType = rs.getString("userType");

				output += "<tr><td><input id=\"hidUserIDUpdate\" name =\"hidUserIDUpdate\" type=\"hidden\" value=\""
						+ userID + "\">" + username + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + phoneNo + "</td>";
				output += "<td>" + age + "</td>";
				output += "<td>" + sex + "</td>";
				output += "<td>" + userType + "</td>";

				output += "<td><input name=\"btnUpdate\" type =\"button\" value = \"Update\" class = \"btnUpdate btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"User.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hidUserIDDelete\" type=\"hidden\" value=\"" + userID + "\">"
						+ "</form></td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading users";
			System.err.println(e.getMessage());
		}
		return output;
	}

	/*
	 * public String updateUser(String userID, String username, String password,
	 * String email, String address, String phoneNo, String age, String sex) {
	 * String output = ""; try { Connection con = connect();
	 * 
	 * if (con == null) { return
	 * "Error while connecting to the database for updating"; }
	 * 
	 * String query =
	 * "UPDATE user SET username=?,password=?,email=?,address=?,phoneNo=?,age=?,sex=? WHERE userID=?"
	 * ; PreparedStatement ps = con.prepareStatement(query);
	 * 
	 * ps.setString(1, username); ps.setString(2, password); ps.setString(3, email);
	 * ps.setString(4, address); ps.setString(5, phoneNo); ps.setString(6, age);
	 * ps.setString(7, sex); ps.setInt(5, Integer.parseInt(userID));
	 * 
	 * ps.execute(); con.close(); output = "Updated successfully";
	 * 
	 * } catch (Exception e) { output = "Error while updating the user";
	 * System.err.println(e.getMessage()); } return output; }
	 */

	public String updateUser(String userID, String username, String password, String email, String address,
			String phoneNo, String age, String sex, String userType) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE user SET username=?,password=?,email=?,address=?,phoneNo=?,age=?,sex=?,userType=? WHERE userID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, username);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, address);
			preparedStmt.setString(5, phoneNo);
			preparedStmt.setString(6, age);
			preparedStmt.setString(7, sex);
			preparedStmt.setString(8, userType);
			preparedStmt.setInt(9, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	/*
	 * public String deleteUser(String userID) { String output = ""; try {
	 * Connection con = connect(); if (con == null) { return
	 * "Error while connecting to the database for deleting."; } // create a
	 * prepared statement String query = "delete from items where userID=?";
	 * PreparedStatement preparedStmt = con.prepareStatement(query); // binding
	 * values preparedStmt.setInt(1, Integer.parseInt(userID)); // execute the
	 * statement preparedStmt.execute(); con.close(); output =
	 * "Deleted successfully"; } catch (Exception e) { output =
	 * "Error while deleting the user."; System.err.println(e.getMessage()); }
	 * return output; }
	 */

	public String deleteUser(String userID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from user where userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the user.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}