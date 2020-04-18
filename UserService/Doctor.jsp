<%@page import="com.controller.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
	//Insert doctor---------------------------------
	if (request.getParameter("doctorName") != null) {
		Doctor docObj = new Doctor();
		String stsMsg = docObj.insertDoctor(request.getParameter("doctorName"), request.getParameter("NIC"),
				request.getParameter("specialization"), request.getParameter("hospitalID"), request.getParameter("email"),
				request.getParameter("mobileNo"));
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete doctor---------------------------------
	if (request.getParameter("doctorID") != null) {
		Doctor docObj = new Doctor();
		String stsMsg = docObj.deleteDoctor(request.getParameter("doctorID"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Management</title>
</head>
<body>
<h1>Users Management</h1>
	<form method="post" action="Doctor.jsp">
		Doctor Name : <input name="doctorName" type="text"><br>
		NIC : <input name="NIC" type="text"><br> Specialization
		: <input name="specialization" type="text"><br> Hospital : <input
			name="hospitalID" type="text"><br> Email : <input
			name="email" type="text"><br> Mobile No : <input name="mobileNo"
			type="text"><br> 
		<input class="btn btn-success" name="btnSubmit" type="submit" value="Save">
	</form>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	4
	<br>
	<%
		Doctor docObj = new Doctor();
		out.print(docObj.readDoctors());
	%>
</body>
</html>