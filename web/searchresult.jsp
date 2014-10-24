<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%@include file="searchcase.jsp"%>
	<hr>

	<div>results found</div>
	<table border="1">
		<tr
			style="font-family: serif; font-weight: bold; font-size: 14px; color: navy;">
			<th>Cast Type</th>
			<th>Case Desc</th>
			<th>Created On</th>
			<th>Patient Name</th>
			<th>Doctor Name</th>
			<th>Care Provider Name</th>
			<th>City</th>
			<th></th>
		</tr>

		<c:forEach var="caseSearchResult" items="${caseSearchResults}">
			<tr>
				<td><c:out value="${caseSearchResult.type}" /></td>
				<td><c:out value="${caseSearchResult.description}" /></td>
				<td><c:out value="${caseSearchResult.createDate}" /></td>
				<td><c:out value="${caseSearchResult.patientInfo.name}" /></td>
				<td><c:out value="${caseSearchResult.doctor.name}" /></td>
				<td><c:out value="${caseSearchResult.careProvider.name}" /></td>
				<td><c:out value="${caseSearchResult.patientInfo.city}" /></td>
				<td><a href="CaseEditServlet?caseId=${caseSearchResult.id}">Edit </a>&nbsp;|&nbsp; <a href="deleteCase.jsp?caseId=${caseSearchResult.id}">Delete</a>&nbsp;|&nbsp;<a
					href="NoteServlet?caseId=${caseSearchResult.id}">Add/View Notes</a></td>
			</tr>

		</c:forEach>


	</table>



</body>
</html>