<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>

	<div align="center">
	 <h1>Books Management</h1>
		<table border="1">
				<h2>My Issued Books</h2>
			<tr>
				<th>Book ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Fine</th>
				<th>Issue date</th>
				<th>Return due date</th>
				<th>Actual Return date</th>
			</tr>
			<c:forEach var="myRentedBook" items="${allRentedBookList}">
				<tr>
					<td><c:out value="${myRentedBook.book.bookId}" /></td>
					<td><c:out value="${myRentedBook.book.title}" /></td>
					<td><c:out value="${myRentedBook.book.author}" /></td>
					<td><c:out value="${myRentedBook.fine}" /></td>
					<td><c:out value="${myRentedBook.issue_date}" /></td>
					<td><c:out value="${myRentedBook.return_due_date}" /></td>
					<td><c:out value="${myRentedBook.actual_return_date}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>