<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Case Management System</h2>
	<hr>
	<div style="float: right">
		<a href="#"><a href="CaseSearchServlet">Search Case</a>&nbsp;|&nbsp;<a href="#">Admin</a>&nbsp;|&nbsp;<a
			href="LogoutServlet">Logout</a>
	</div>

	<h3>Add Note</h3>


	<form action="NoteServlet" method="post">

		<div style="font-size: 14px; font-weight: bold; color: navy;">for
			Case ID: ${caseId}</div>
		<div id="">
			<table width="700px">
				<tr>
					<td width="100">Note Desc:</td>
					<td><textarea name="noteText" rows="3" cols="60" > </textarea>
					<td>
					<td><input type="submit" value="Submit">
					<td></td>
			</table>
		</div>
		<input type="hidden" name="caseId" value="${caseId}" />
	</form>

	<hr>

	<table >


		<c:forEach var="x" items="${noteSearchResult}" >
			<tr>
			<td>Note: <br><c:out value="${x.noteText}" /></td>
			</tr>

		</c:forEach>


	</table>

</body>
</html>