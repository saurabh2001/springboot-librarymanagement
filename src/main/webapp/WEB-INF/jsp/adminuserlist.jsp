<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<div align="center">
		<h1>User Management</h1>
		<h2>
			<a href="/admin/add-new-user">Add New User</a> &nbsp;&nbsp;&nbsp; <a
				href="/admin/list-all-users">List All Users</a> &nbsp;&nbsp;&nbsp; <a
				href="/admin/list-active-users/">List Active Users</a>

		</h2>
	</div>
	<div align="center">
		<table border="1">
			<tr>
				<th>USER ID</th>
				<th>Email ID</th>
				<th>password</th>
				<th>active</th>
				<th>Creation date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${allUsersList}">
				<tr>
					<td><c:out value="${user.userId}" /></td>
					<td><c:out value="${user.useremail}" /></td>
					<td><c:out value="${user.password}" /></td>
					<td><c:out value="${user.active}" /></td>
					<td><c:out value="${user.createDate}" /></td>
					<td><a
						href="/admin/change-user-status/<c:out value='${user.userId}' />">Switch</a>
						&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>