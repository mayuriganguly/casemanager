<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h2>Case Management System</h2>
	<hr>

	<div style="float: right">
		<a href="CaseHomeServlet">Home</a>&nbsp;|&nbsp;<a href="CaseServlet">Create case </a>&nbsp;|&nbsp; <a href="#">Admin</a>&nbsp;|&nbsp;<a
			href="#">Logout</a>
	</div>

	<h3>Search Case</h3>

	<form action="CaseSearchServlet" method="post">
		<div  style="width:600px;border:2px solid;">

			<table width="600" border="0" style="margin:10px">
				<tr>
					<td width="100"><label for="case_type">Case Type:</label> </td><td><select
						name="caseType">
						<option value="">--select--</option>
							<option value="CP">Cerebral Palsy</option>
							<option value="POLIO">Polio</option>
							<option value="Autisim">Autisim</option>
					</select></td>
					<td width="100"><label for="city"> City:</label> </td><td><input type="text"
						name="city" /></td>
				</tr>
				<tr>
					<td width="100"><label for="state"> State:</label> </td><td><input type="text"
						name="state" /></td>
					<td width="100"><label for="zipcode"> Zip:</label> </td><td><input type="text"
						name="zipcode" /></td>
				</tr>
				<tr>
					<td width="100"><label for="patient"> Patient:</label> </td><td><input type="text"
						name="patient" /></td>
					<td width="100"><label for="doctor"> Doctor:</label> </td><td><input type="text"
						name="doctor" /></td>
				</tr>


				<tr>
					<td colspan="2" align="center"><input type="submit" value="Search"></td>
				</tr>
			</table>
			</div>
	</form>


</body>
</html>