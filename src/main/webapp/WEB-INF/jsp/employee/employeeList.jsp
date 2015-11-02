<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<table>
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>Location</td>
			<td>Edit</td>
			<td>Delete</td>
		</tr>
		<c:forEach items="${employeelist}" var="employee">
			<tr>
				<td>${employee.eid}</td>
				<td>${employee.name}</td>
				<td>${employee.location}</td>
				<td>
				<sec:authorize access="hasRole('user') or hasRole('admin')">
				<a
					href="<%=request.getContextPath() %>/employee/edit/${employee.eid}.htm">Edit</a>
					</sec:authorize>
					</td>
				<td>
				<sec:authorize access="hasRole('admin')">
				<a
					href="<%=request.getContextPath() %>/employee/delete/${employee.eid}.htm">Delete</a>
					</sec:authorize>
					</td>
			</tr>

		</c:forEach>
	</table>
		<c:if test="${showCreateLink}">
	<a href="create.htm">Create New</a>
	</c:if>

</body>
</html>