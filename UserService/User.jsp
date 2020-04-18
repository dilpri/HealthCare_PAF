<%@page import="com.controller.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%
	//Save user---------------------------------
	if (request.getParameter("username") != null) {
		User userObj = new User();
		String stsMsg = "";

		if (request.getParameter("hidUserIDSave") == "") {
			stsMsg = userObj.insertUser(request.getParameter("username"), request.getParameter("password"),
					request.getParameter("email"), request.getParameter("address"),
					request.getParameter("phoneNo"), request.getParameter("age"), request.getParameter("sex"),request.getParameter("userType"));
		} else //Update
		{
			stsMsg = userObj.updateUser(request.getParameter("hidUserIDSave"), request.getParameter("username"),
					request.getParameter("password"), request.getParameter("email"),
					request.getParameter("address"), request.getParameter("phoneNo"),
					request.getParameter("age"), request.getParameter("sex"),request.getParameter("userType"));
		}

		session.setAttribute("statusMsg", stsMsg);
	}
	//Delete user---------------------------------
	if (request.getParameter("hidUserIDDelete") != null) {
		User userObj = new User();
		String stsMsg = userObj.deleteUser(request.getParameter("hidUserIDDelete"));
		session.setAttribute("statusMsg", stsMsg);
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.5.0.min.js"></script>
<script src="Components/User.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1>Users Management</h1>
				<form id="formUser" name="formUser" method="post" action="User.jsp">
					User name : <input id="username" name="username" type="text"
						class="form-control form-control-sm"><br> Password :
					<input id="password" name="password" type="text"
						class="form-control form-control-sm"><br> Email : <input
						id="email" name="email" type="text"
						class="form-control form-control-sm"><br> Address : <input
						id="address" name="address" type="text"
						class="form-control form-control-sm"><br> Phone No :
					<input id="phoneNo" name="phoneNo" type="text"
						class="form-control form-control-sm"><br> Age : <input
						id="age" name="age" type="text"
						class="form-control form-control-sm"><br> Sex : <input
						id="sex" name="sex" type="text"
						class="form-control form-control-sm"><br>User Type : <input
						id="userType" name="userType" type="text"
						class="form-control form-control-sm"><br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidUserIDSave" name="hidUserIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					User userObj = new User();
					out.print(userObj.readUsers());
				%>
			</div>
		</div>
	</div>
</body>
</html>