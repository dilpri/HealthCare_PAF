package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;




public class Notice {
	
	//A common method to connect DB
	
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "admin", "");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	// Insert
		public String insertNotice(String noticeType, String noticeDesc) {
			String output = "";
			
			
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into notices (`noticeID`,`noticeType`,'noticeDesc')"
						+ " values (?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, noticeType);
				preparedStmt.setString(3, noticeDesc);
				

				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
			} catch (Exception e) {
				output = "Error while inserting the notices.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//read
		
		
		public String readNotices() {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error while connecting the database for reading";
				}

				output = "<table border=\"1\"><tr><th>noticeType</th><th>noticeDesc</th></tr>";

				String query = "select * from notices";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					String noticeID = Integer.toString(rs.getInt("noticeID"));
					String noticeType = rs.getString("noticeType");
					String noticeDesc = rs.getString("noticeDesc");

					output += "<tr><td>" + noticeType + "</td>";
					output += "<td>" + noticeDesc + "</td>";
					

					output += "<td><input name=\"btnUpdate\" type =\"button\" value = \"Update\" class = \"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"Notice.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
							+ "<input name=\"noticeID\" type=\"hidden\" value=\"" + noticeID + "\">" + "</form></td></tr>";

				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading notices";
				System.err.println(e.getMessage());
			}
			return output;
		}

		
		//update
		
		
		
		public String updateNotice(String noticeID, String noticeType, String noticeDesc)
				{
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE notices SET noticeType=?,noticeDesc=?  WHERE noticeID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, noticeType);
				preparedStmt.setString(2, noticeDesc);
			    preparedStmt.setInt(3, Integer.parseInt(noticeID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the notice.";
				System.err.println(e.getMessage());
			}
			return output;
		}


		//delete
		
		
		public String deleteNotice(String noticeID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from notices where noticeID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(noticeID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the notice.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		


}
	


