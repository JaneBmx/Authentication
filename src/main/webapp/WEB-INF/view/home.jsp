<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Home Page</title>
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.5.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.5.0/css/bootstrap.min.css"></script>
</head>
<body>
<a href="${pageContext.request.contextPath}/showMyLoginPage">Login form</a>
<security:authorize access="hasRole('DEFAULT')">
    <p>
        <a href="${pageContext.request.contextPath}/users/list">User list</a>
    </p>
</security:authorize>
<hr>
<!-- Add a logout button -->
<security:authorize access="hasRole('DEFAULT')">
    <form:form action="${pageContext.request.contextPath}/logout" method="POST">
        <input type="submit" value="Logout"/>
    </form:form>
</security:authorize>
</body>
</html>