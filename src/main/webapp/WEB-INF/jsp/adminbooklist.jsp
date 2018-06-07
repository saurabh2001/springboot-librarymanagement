<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"></jsp:include>
<html>
<body>
     <div align="center">
        <h1>Books Management</h1>
        <c:if test="${ isUser  != 'Yes'}">
        <h2>
            <a href="/admin/add-new-book">Add New Book</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/admin/list-all-books">List All Books</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/admin/list-issued-books">List Issued Books</a>
             
        </h2>
        </c:if>
        
    </div>
    <div align="center">
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Creation date</th>
				<th>Category</th>
                <c:if test="${ isUser  != 'Yes'}">
                <th>Actions</th>
                </c:if>
            </tr>
            <c:forEach var="book" items="${allBooksList}">
                <tr>
                    <td><c:out value="${book.bookId}" /></td>
                    <td><c:out value="${book.title}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.createDate}" /></td>
						<td><c:out value="${book.category.categoryName}" /></td>
                    <c:if test="${ isUser  != 'Yes'}">
                    <td>
                        <a href="/admin/edit-book/<c:out value='${book.bookId}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/admin/delete-book/<c:out value='${book.bookId}' />">Delete</a>                     
                    </td>
                    </c:if>

                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>