<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<body>
	<input type="button" value="Add Student" onclick="window.location.href='\add-student-form.jsp'"/>
		<table border="1px" cellpadding="5px">
			<tr align="center">
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Mob</th>
				<th>Action</th>
			</tr>
			
			<c:forEach var="x" items="${STUDENT_LIST}">
				<c:url var="templink" value="StudentControllerServlet">
					<c:param name="command" value="LOAD"/>
					<c:param name="studentId" value="${x.id}"/>
				</c:url>
				<c:url var="del_link" value="StudentControllerServlet">
					<c:param name="command" value="DELETE"/>
					<c:param name="studentId" value="${x.id}"/>
				</c:url>
				<tr align="center">
					<td>${x.first}</td>
					<td>${x.last}</td>
					<td>${x.email}</td>
					<td>${x.mob}</td>
					<td><a href="${templink}">Update</a> | <a href="${del_link}" onclick="if (!(confirm('Are you sure(Delete ${first})?'))) reutrn false">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>