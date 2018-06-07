<%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type"
	content="application/json; charset=utf-8">
<h3>Search Books</h3>
<br>
<form action='/admin/search-book' method='get'>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	<table class='table table-hover table-responsive table-bordered'>

		<tr>
			<td><b>title</b></td>
			<td><input type='text' name='title' class='form-control' /></td>
		</tr>

		<tr>
			<td><b>author</b></td>
			<td><input type='text' name='author' class='form-control' /></td>
		</tr>

		<tr>
			<td><b>Category</b></td>
			<td><select id="category" name="category">
					<option value="-1">Select a category</option>
					<c:forEach items="${categories}" var="category">
						<option value="${category.categoryId}">${category.categoryName}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn btn-primary">Search</button>
			</td>
		</tr>
	</table>
		<div align="center">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author</th>
					<th>Category</th>
				</tr>
				<c:forEach var="book" items="${allBooksList}">
					<tr>
						<td><c:out value="${book.bookId}" /></td>
						<td><c:out value="${book.title}" /></td>
						<td><c:out value="${book.author}" /></td>
						<td><c:out value="${book.category.categoryName}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
</form>