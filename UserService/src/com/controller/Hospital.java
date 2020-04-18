package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Hospital {

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

	// Insert
	public String insertHospital(String hosName, String location, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into hospital (`hospitalID`,`hosName`,`location`,`email`)" + " values (?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, hosName);
			preparedStmt.setString(3, location);
			preparedStmt.setString(4, email);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readHospitals() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting the database for reading";
			}

			output = "<table border=\"1\"><tr><th>Hospital name</th><th>Location</th><th>Email</th></tr>";

			String query = "select * from hospital";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				String hospitalID = Integer.toString(rs.getInt("hospitalID"));
				String hosName = rs.getString("hosName");
				String location = rs.getString("location");
				String email = rs.getString("email");

				output += "<tr><td>" + hosName + "</td>";
				output += "<td>" + location + "</td>";
				output += "<td>" + email + "</td>";

				output += "<td><input name=\"btnUpdate\" type =\"button\" value = \"Update\" class = \"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Hospital.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"hospitalID\" type=\"hidden\" value=\"" + hospitalID + "\">"
						+ "</form></td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading hospitals";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateHospital(String hospitalID, String hosName, String location, String email) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE hospital SET hosName=?,location=?,email=? WHERE hospitalID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, hosName);
			preparedStmt.setString(2, location);
			preparedStmt.setString(3, email);
			preparedStmt.setInt(4, Integer.parseInt(hospitalID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteHospital(String hospitalID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from hospital where hospitalID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(hospitalID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the hospital.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
