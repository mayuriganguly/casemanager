<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Case Manager - create case</title>
</head>
<body>
	<h2>Case Management System</h2>
	<hr>
	<div style="float: right">
		<a href="CaseSearchServlet"><a href="CaseSearchServlet">Search Case</a>&nbsp;|&nbsp;<a href="#">Admin</a>&nbsp;|&nbsp;<a
			href="LogoutServlet">Logout</a>
	</div>

	<h3>Create Case</h3>
	<hr>

	<form action="CaseServlet" method="post">
		<div class="caseForm">
			<div id="caseOverview">

				<fieldset>
					<legend>Case Overview:</legend>
					<label for="case_type">Case Type <span class="required">*</span></label>
					<select name="caseType">
						<option value="CP">Cerebral Palsy</option>
						<option value="POLIO">Polio</option>
						<option value="AUTISIM">Autisim</option>

					</select> <br> <label for="case_desc">Case Description <span
						class="required">*</span></label>
					<textarea name="caseDesc" rows="3" cols="30"> </textarea>
				</fieldset>
			</div>
			<br />


			<div id="patientDetails">
				<fieldset>
					<legend>Patient Details:</legend>
					<label for="patientName"> Name <span class="required">*</span></label>
					<input type="text" name="patientName" /> <br> <label
						for="patientAge">Age<span class="required">*</span></label> <input
						type="text" name="patientAge" /> <br> <label
						for="patientGender">Gender<span class="required">*</span></label>
					<select name="patientGender"><option value="M">Male</option>
						<option value="F">Female</option></select> <br> <label
						for="patientCity">City <span class="required">*</span></label> <input
						type="text" name="patientCity" /> <br> <label
						for="patientSate">Sate<span class="required">*</span></label> <input
						type="text" name="patientSate" /> <br> <label
						for="patientZip">Zip<span class="required">*</span></label> <input
						type="text" name="patientZip" /> <br>

				</fieldset>
			</div>
			<br />

			<div id="careProvider">
				<fieldset>
					<legend>Care Provider:</legend>
					<label for="case_provider">Care Provider <span
						class="required">*</span></label> <select name="careProvider">
						<option value="">--Select One--</option>
						<option value="3">Patty Diego</option>
						<option value="4">S Dora</option>
						<option value="5">M Borrow</option>
					</select>
				</fieldset>
			</div>
			<br />

			<div id="doctorDetails">
				<fieldset>
					<legend>Doctor:</legend>
					<label for="doctor">Doctor <span class="required">*</span></label>
					<select name="caseDoctor">
						<option value="">--Select One--</option>
						<option value="1">Dr. Marry Jones</option>
						<option value="2">Dr. P Sasha</option>
					</select>
				</fieldset>
			</div>
			<br /> <input type="submit" value="Submit">

		</div>
	</form>

</body>
</html>