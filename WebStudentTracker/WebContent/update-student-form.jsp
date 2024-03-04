<html>
	<body>
		<h1>Update Student</h1>
		<form method="GET" action="StudentControllerServlet">
			<input type="hidden" name="command" value="UPDATE"/>
			<input type="hidden" name="studentId" value="${student_data.id}"/>
			FN : <input type="text" name="fn" value="${student_data.first}"/>
			<br/><br/>
			LN : <input type="text" name="ln" value="${student_data.last}"/>
			<br/><br/>
			Email : <input type="text" name="email" value="${student_data.email}"/>
			<br/><br/>
			Mob : <input type="text" name="mob" value="${student_data.mob}"/>
			<br/><br/>
			<input type="submit" value="Save"/>
			<br/><br/>
			<a href="StudentControllerServlet">Back to List</a>
		</form>
	</body>
</html>