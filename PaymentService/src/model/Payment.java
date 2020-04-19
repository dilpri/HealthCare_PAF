package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment( String cardType, String cardNo, String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into payment(`paymentID`,`cardType`,`cardNo`,`amount`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1,0);
	 preparedStmt.setString(2, cardType);
	 preparedStmt.setString(3, cardNo);
	 preparedStmt.setDouble(4, Double.parseDouble(amount));
	 
	// execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }

	public String readPayments()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>Payment ID</th><th>Card Type</th><th>Card No</th><th>Amount</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from payment";
	 Statement stmt = (Statement) con.createStatement();
	 ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String paymentID = Integer.toString(rs.getInt("paymentID"));
	 String cardType = rs.getString("cardType");
	 String cardNo = rs.getString("cardNo");
	 String amount = Double.toString(rs.getDouble("amount"));
	 
	 // Add into the html table
	 output += "<tr><td>" + paymentID + "</td>";
	 output += "<td>" + cardType + "</td>";
	 output += "<td>" + cardNo + "</td>";
	 output += "<td>" + amount + "</td>";
	 // buttons
	 output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"+ "<td><form method=\"post\" action=\"payments.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
	 + "<input name=\"paymentID\" type=\"hidden\" value=\"" + paymentID
	 + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the payments.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String updatePayment(String paymentID, String cardType, String cardNo, String amount)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE payments SET cardType=?,cardNo=?,amount=? WHERE paymentID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, cardType);
	 preparedStmt.setString(2, cardNo);
	 preparedStmt.setDouble(3, Double.parseDouble(amount));
	 preparedStmt.setInt(4, Integer.parseInt(paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the item.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deletePayment(String paymentID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from items where itemID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(paymentID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the payment.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	

}


