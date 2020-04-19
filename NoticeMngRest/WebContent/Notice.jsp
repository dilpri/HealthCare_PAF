<%@page import="model.Notice"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	//Insert doctor---------------------------------
if (request.getParameter("noticeType") != null) {
	Notice nObj = new Notice();
	String stsMsg = nObj.insertNotice(request.getParameter("noticeType"), request.getParameter("noticeDesc"));
	session.setAttribute("statusMsg", stsMsg);
}
//Delete doctor---------------------------------
if (request.getParameter("noticeID") != null) {
	Notice nObj = new Notice();
	String stsMsg = nObj.deleteNotice(request.getParameter("noticeID"));
	session.setAttribute("statusMsg", stsMsg);
}
%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notice Management</title>
</head>
<body>

<h1>Notice Management</h1>
	
	<form method="post" action="Notice.jsp">
		Notice Type : <input name="noticeType" type="text"><br>
		NoticeDesc : <input name="noticeDesc" type="text"><br> <br>
		<input class="btn btn-success" name="btnSubmit" type="submit"
			value="Save">
	</form>
	<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					Notice nObj = new Notice();
					out.print(nObj.readNotices());
				%>
		

</body>
</html>