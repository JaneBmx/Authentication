<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>List Users</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<div id="wrapper">
    <div id="header">
        <h2>User control panel</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <form:form action="/users/" method="Post">
            <button name="job" value="unblock" class="btn"> <i class="fa fa-unlock"></i></button>
            <button name="job" value="block" class="btn"><i class="fa fa-lock"></i></button>
            <button name="job" value="delete" class="btn"><i class="fa fa-trash"></i></button>
            <table>
                <tr>
                    <th><input type="checkbox" id="cb" name="selectAllCheck" onClick="checkAll()"
                               value="Select All">
                    </th>
                    <th>Id</th>
                    <th>Username</th>
                    <th>Register date</th>
                    <th>Last Online</th>
                    <th>Email</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="tempUser" items="${users}">
                    <tr>
                        <td>
                            <p>
                                <input type="checkbox" name="id" value="${tempUser.id}">
                            </p>
                        </td>
                        <td>${tempUser.id}</td>
                        <td>${tempUser.userName}</td>
                        <td>${tempUser.registerDate}</td>
                        <td>${tempUser.lastOnline}</td>
                        <td>${tempUser.email}</td>
                        <td>${tempUser.enabled}</td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>
    </div>
</div>
</body>
<script>
    function checkAll() {
        const inputs = document.getElementsByTagName("input");
        let checked;
        for (let i = 0; i < inputs.length; i++)
            if (inputs[i].type == "checkbox" && inputs[i].id == "cb")
                checked = inputs[i].checked;
        for (let i = 0; i < inputs.length; i++)
            if (inputs[i].type == "checkbox")
                inputs[i].checked = checked;
    }
</script>
</html>
