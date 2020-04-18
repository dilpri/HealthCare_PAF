<%@page import="com.controller.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%
	//Insert hospital---------------------------------
	if (request.getParameter("hosName") != null) {
		Hospital hosObj = new Hospital();
		String stsMsg = hosObj.insertHospital(request.getParameter("hosName"), request.getParameter("location"),
				request.getParameter("email"));
		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete hospital---------------------------------
	if (request.getParameter("hospitalID") != null) {
		Hospital hosObj = new Hospital();
		String stsMsg = hosObj.deleteHospital(request.getParameter("hospitalID"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospitals Management</title>
</head>
<body>
<h1>Hospitals Management</h1>
	<form method="post" action="Hospital.jsp">
		Hospital name : <input name="hosName" type="text"><br>
		Location : <input name="location" type="text"><br> Email
		: <input name="email" type="text"><br> 
		<input class="btn btn-success" name="btnSubmit" type="submit" value="Save">
	</form>
	<%
		out.print(session.getAttribute("statusMsg"));
	%>
	4
	<br>
	<%
		Hospital hosObj = new Hospital();
		out.print(hosObj.readHospitals());
	%>
</body>
</html>