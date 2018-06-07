<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="application/json; charset=utf-8">
<h3>Books Management</h3>
<br>
<form action='/admin/add-new-user' method='post'>
<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
    <table class='table table-hover table-responsive table-bordered'>
 
        <tr>
            <td><b>Email</b></td> 
            <td><input type='text' name='useremail' class='form-control'   value ="test.mail"   required/></td>
        </tr>
 
        <tr>
            <td><b>Password</b></td>
            <td><input type="password" name='password' class='form-control' value ="pqr"  required /></td>
        </tr>
 
        <tr>
            <td></td>
            <td>
                <button type="submit" class="btn btn-primary">Register</button>
            </td>
        </tr>
    </table>
</form>