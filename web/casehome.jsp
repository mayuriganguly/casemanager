<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Case Manager - home</title>
</head>
<body>

	<h2>Case Management System</h2>
	<hr>

	<div style="float: right">
		<a href="CaseServlet">Create case </a>&nbsp;|&nbsp; <a
			href="CaseSearchServlet">Search Case</a>&nbsp;|&nbsp;<a href="#">Admin</a>&nbsp;|&nbsp;<a
			href="LogoutServlet">Logout</a>
	</div>

	<h3>Case Overview</h3>

	<table border="1" width="400">
		<tr>
			<th>Case Type</th>
			<th>No. Of Cases</th>
		</tr>

		<%
			HashMap<String, String> caseCount = (HashMap<String, String>) (request
					.getAttribute("caseCount"));
			for (Map.Entry<String, String> entry : caseCount.entrySet()) {
		%>
		<TR>
			<TD><%= entry.getKey()%></TD>
			<TD><%= entry.getValue() %></TD>
		</TR>
		<%
			}
		%>

	</table>


</body>
</html>