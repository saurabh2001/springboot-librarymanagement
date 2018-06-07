<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="application/json; charset=utf-8">
<h3>Books Management</h3>
<br>
<form action='/admin/edit-book/${book.bookId}' method='post'>
<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
<input type="hidden" name="bookId"   value ="${book.bookId}" />
<%-- <input type="hidden" name="createDate"   value ="${book.createDate}" /> --%>
    <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
            <td><b>title</b></td> 
            <td><input type='text' name='title' class='form-control'   value ="${book.title}"   required/></td>
        </tr>
 
        <tr>
            <td><b>author</b></td>
            <td><input type='text' name='author' class='form-control'  value ="${book.author}"   required /></td>
        </tr>
 
        <tr>
         <td><b>Category</b></td>
        <td>
         <select id="category" name="category" >    
       <option value="$book.category">Select a type</option>
       <c:forEach items="${categories}" var="category">
        <option value="${category.categoryId}">${category.categoryName}</option>
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