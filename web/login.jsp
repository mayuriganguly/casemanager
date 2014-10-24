<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Case Manager - login</title>
</head>
<body>
	<form action="LoginServlet" method="post">
		<h2>Case Management System</h2>
		<hr>


		<div style="width: 500px; border: 1">
			<fieldset>
				<legend>Login:</legend>
				<div style="width: 150px;">UserName:</div>
				<div style="width: 350px;">
					<input type="text" name="userName"/>
				</div>
				<div style="width: 150px;">Password:</div>
				<div style="width: 350px;">
					<input type="password" name="password"/>
				</div>
				<div>
					<input type="submit"  value="login" />
				</div>

			</fieldset>
		</div>

	</form>
</body>
</html>