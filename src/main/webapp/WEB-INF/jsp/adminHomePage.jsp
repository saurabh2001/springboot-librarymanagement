<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="header.jsp"></jsp:include>
<html><body>
<h3>Library Management</h3>


<a href="/admin/list-all-books">
                <button type="submit" class="btn btn-primary">Manage books</button>
                </a>
   
<a href="/admin/list-all-users">
                <button type="submit" class="btn btn-primary">Manage Users</button>
                </a>

<a href="/admin/list-issued-books">
                <button type="submit" class="btn btn-primary">Return book</button>
                </a>
                
<a href="/admin/issue-book">
                <button type="submit" class="btn btn-primary">Issue book</button>
                </a>                                 
      
<a href="/admin/search-book">
                <button type="submit" class="btn btn-primary">Search book</button>
                </a>                                 
</body>
</html>