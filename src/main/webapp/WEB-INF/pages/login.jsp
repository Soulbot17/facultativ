<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<mvc:resources mapping="/resources/**" location="/WEB-INF/resources/"/>

<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="label.auth_page"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
</head>
<body>
<div class="change_language">
    <a href="?lang=ru"><img src="/resource/images/ru.png"></a>
    <a href="?lang=en"><img src="/resource/images/en.png"></a>
</div>
<div class="center_main_area">
    <div class="left_main_area">
        <div class="logo">
            <h1><spring:message code="label.welcome"/></h1>
        </div>
    </div>
    <div class="right_main_area">
        <div class="signin_area">
            <p class="form_label"><spring:message code="label.signin_info"/></p>
            <form name='loginForm'
                  action="<c:url value='/login' />" method='POST'>

                <div class="form-group">
                    <input name="email" type="text" class="login_fields" placeholder="Email"/>
                    <input name="password" type="password" class="login_fields"
                           placeholder="<spring:message code="label.password" />"/>
                    <button class="login_buttons" type="submit"><spring:message code="label.signin"/></button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input class="login_buttons" type="button" name="go_register"
                           value="<spring:message code="label.signup"/>"
                           onclick="location.href='${contextPath}/registration'">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>