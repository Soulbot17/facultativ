<%--
  Created by IntelliJ IDEA.
  User: banka
  Date: 18.03.18
  Time: 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
    ${UserName}
    <h1>User info</h1>
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
        <button class="btn " type="submit">Logout</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</body>
</html>
