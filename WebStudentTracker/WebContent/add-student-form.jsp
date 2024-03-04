<html>
	<body>
		<h1>Add Student</h1>
		<form method="GET" action="StudentControllerServlet">
			<input type="hidden" name="command" value="ADD"/>
			FN : <input type="text" name="fn"/>
			<br/><br/>
			LN : <input type="text" name="ln"/>
			<br/><br/>
			Email : <input type="text" name="email"/>
			<br/><br/>
			Mob : <input type="text" name="mob"/>
			<br/><br/>
			<input type="submit" value="Save"/>
			<br/><br/>
			<a href="StudentControllerServlet">Back to List</a>
		</form>
	</body>
</html>