<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <title>welcome page</title>
    <style>
        h1{
            font-family: sans-serif;
            font-weight: 900;
            color: #fff;
        }
        body{
            margin: 0;
            background-color: dodgerblue;
        }
        .left_main_area{
            float: left;
            width: 700px;
            height: 400px;
        }
        .right_main_area{
            float: left;
            width: 324px;
            height: 400px;
        }
        .center_main_area{
            width: 1024px;
            margin: auto;
        }
        .signin_area{
            margin-top: 200px;
            height: 200px;
        }
        p{
            color: #fff;
            font-family: sans-serif;
            font-weight: 600;
        }
        .login_fields{
            display: block;
            width: 200px;
            margin-bottom: 10px;
            height: 20px;
        }
        .login_buttons{
            padding: 10px;
            border: 0;
            background-color: darkorange;
            color: #fff;
            font-family: sans-serif;
            font-weight: bold;
        }
        .login_buttons:hover{
            cursor: pointer;
        }
        .logo{
            margin-top: 250px;
        }
    </style>
    <link type="text/css" href="${contextPath}/resource/css/style.css" rel="stylesheet">
</head>
<body>
<div class="center_main_area">
    <div class="left_main_area">
        <div class="logo">
            <h1>Welcome to the Training portal</h1>
        </div>
    </div>
    <div class="right_main_area">
        <div class="signin_area">
            <p>Sign in into your account</p>
            <form name='loginForm'
                  action="<c:url value='/index' />" method='POST'>

                <div class="form-group">
                    <input name="email" type="text" class="login_fields" placeholder="Email"/>
                    <input name="password" type="password" class="login_fields" placeholder="Password"/>
                    <button class="login_buttons" type="submit">Sign in</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input class="login_buttons" type="button" name="go_register" value="Sigh up" onclick="location.href='${contextPath}/registration'">

                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>