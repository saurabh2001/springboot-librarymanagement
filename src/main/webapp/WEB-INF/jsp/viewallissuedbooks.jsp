<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

	<div align="center">
		<h1>Books Management</h1>
		<table border="1">
			<h2>All Issued Books</h2>
			<h3>
					<a href="/admin/list-all-books">List All Books</a>
			</h3>
			<tr>
				<th>ID</th>
				<th>Book ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>User Email</th>
				<th>Fine</th>
				<th>Issue date</th>
				<th>Return due date</th>
				<th>Actual Return date</th>
				<th>Action</th>
			</tr>
			<c:forEach var="issuedBook" items="${allIssuedBooksList}">
				<tr>
					<td><c:out value="${issuedBook.id}" /></td>
					<td><c:out value="${issuedBook.book.bookId}" /></td>
					<td><c:out value="${issuedBook.book.title}" /></td>
					<td><c:out value="${issuedBook.book.author}" /></td>
					<td><c:out value="${issuedBook.user.useremail}" /></td>
					<td><c:out value="${issuedBook.fine}" /></td>
					<td><c:out value="${issuedBook.issue_date}" /></td>
					<td><c:out value="${issuedBook.return_due_date}" /></td>
					<td><c:out value="${issuedBook.actual_return_date}" /></td>

					<td><c:if test="${ issuedBook.actual_return_date  == null}">
							<a href="/admin/return-book/<c:out value='${issuedBook.id}' />">Return</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						 </c:if> <c:if test="${ issuedBook.actual_return_date  != null}">Returned
						&nbsp;&nbsp;&nbsp;&nbsp;
						 </c:if></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>