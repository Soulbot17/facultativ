<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <title>Тестовая страница</title>
    <link href="${contextPath}/resource/css/style.css" rel="stylesheet">
</head>
<body>
<div class="center_main_area">
    <div class="left_main_area">
        <div class="logo">
            <h1>Training your skills</h1>
        </div>
    </div>
    <div class="right_main_area">
        <div class="signin_area">
            <p>Sign in into your account</p>
            <form name='loginForm'
                  action="<c:url value='${contextPath}/login' />" method='POST'>

                <div class="form-group">
                    <input name="email" type="text" class="form-control" placeholder="Username"/>
                    <input name="password" type="password" class="form-control" placeholder="Password"/>
                    <button class="btn " type="submit">Sign in</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <%--<h4 class="text-center"><a href="${contextPath}/registration">Sign up</a></h4>--%>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
