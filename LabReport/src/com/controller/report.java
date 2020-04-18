package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class report { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			System.out.print("Database Successfully connected");
		} catch (Exception e) {
			System.out.print("Database not connected");
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem(String reportID, String reportType, String description, String patientID,
			String doctorID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
// create a prepared statement
			String query = " insert into labreport (`reportID`,`reportType`,`description`,`patientID`,`doctorID`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, reportType);
			preparedStmt.setString(3, description);
			preparedStmt.setInt(4, Integer.parseInt(patientID));
			preparedStmt.setInt(5, Integer.parseInt(doctorID));

// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item." + e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Report ID</th><th>Report Type</th><th>Description</th><th>Assigned Doctor</th><th>Patient Name</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from labreport";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String reportID = Integer.toString(rs.getInt("reportID"));
				String reportType = rs.getString("reportType");
				String description = rs.getString("description");
				String doctorName = rs.getString("doctorID");
				String patientName = rs.getString("patientID");
// Add into the html table
				output += "<tr><td>" + reportID + "</td>";
				output += "<td>" + reportType + "</td>";
				output += "<td>" + description + "</td>";
				output += "<td>" + doctorName + "</td>";
				output += "<td>" + patientName + "</td>";
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + reportID + "\">" + "</form></td></tr>";
			}
			con.close();
// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteItem(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from labreport where reportID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item. :" + e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String reportID, String reportType, String description, String patientID,
			String doctorID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE labreport SET reportID=?,reportType=?, description=?, patientID=?, doctorID=? WHERE reportID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(reportID));
			preparedStmt.setString(2, reportType);
			preparedStmt.setString(3, description);
 			preparedStmt.setInt(4, Integer.parseInt(patientID));
 			preparedStmt.setInt(5, Integer.parseInt(doctorID));
 			preparedStmt.setInt(6, Integer.parseInt(reportID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item."+e.getMessage();
			System.err.println(e.getMessage());
		}
		return output;
	}

}

