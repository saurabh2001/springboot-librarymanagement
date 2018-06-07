<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="application/json; charset=utf-8">
<h3>Books Management</h3>
<br>
<form action='/admin/issue-book' method='post'>
<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
    <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
         <td><b>Book</b></td>
        <td>
         <select id="book" name="book" >    
       <option value="-1">Select a book</option>
       <c:forEach items="${allBooksList}" var="book">
        <option value="${book.bookId}">${book.title}</option>
       </c:forEach>
      </select>
     </td>
       </tr>
       
       <tr>
         <td><b>User</b></td>
        <td>
         <select id="user" name="user" >    
       <option value="-1">Select a user</option>
       <c:forEach items="${allUsersList}" var="user">
        <option value="${user.userId}">${user.useremail}</option>
       </c:forEach>
      </select>
     </td>
       </tr>
       
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary">Register</button>
            </td>
        </tr>
    </table>
</form>