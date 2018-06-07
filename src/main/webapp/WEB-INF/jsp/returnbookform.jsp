<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type"
	content="application/json; charset=utf-8">
<h3>Books Management</h3>
<br>
<form action='/admin/return-book/${bookToReturn.id}' method='post'>
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" />
		 
	<table class='table table-hover table-responsive table-bordered'>

		<tr>
			<td><b>title</b></td>
			<td>${bookToReturn.id}</td>
		</tr>

		<tr>
			<td><b>Title</b></td>
			<td>${bookToReturn.book.title}</td>
		</tr>

		<tr>
			<td><b>Author</b></td>
			<td>${bookToReturn.book.author}</td>
		</tr>
		<tr>
			<td><b>User email</b></td>
			<td>${bookToReturn.user.useremail}</td>
		</tr>

		<tr>
			<td><b>Issue Date</b></td>
			<td>${bookToReturn.issue_date}</td>
		</tr>
		<tr>
			<td><b>Return due Date</b></td>
			<td>${bookToReturn.return_due_date}</td>
		</tr>

		<tr>
			<td><b>Fine amount</b></td>
			<td>${bookToReturn.fine}</td>
		</tr>

		<!-- 		<tr> -->
		<!-- 			<td><b>Actual Return Date</b></td> -->
		<%-- 			<td>${bookToReturn.actual_return_date}</td> --%>
		<!-- 		</tr> -->

		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn btn-primary">Return</button>
				<a href="/admin/list-issued-books">
				<button type="button" class="btn btn-primary">Back</button></a>
			</td>
			
		</tr>
	</table>
</form>