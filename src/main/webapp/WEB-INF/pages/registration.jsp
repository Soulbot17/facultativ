<%--
  Created by IntelliJ IDEA.
  User: banka
  Date: 20.03.18
  Time: 15:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
    <meta charset="utf-8">
    <title>${reg_page}</title>
    <%--<style>
        .login_buttons{
            width: 100px;
            background-color: #fff;
            border: 0;
            height: 25px;
        }
    </style>--%>
    <script>
        function compare_passwords(){
            var firstPassword = document.getElementById("pass").value;
            var secondPassword = document.getElementById("repeat_pass").value;
            if(firstPassword != secondPassword){
                alert("Passwords doesnt match");
                document.getElementById("repeat_pass").value = '';
            }
        }

        function check_email(){
            var regex = /(\w+\@\w+\.\w+)/g;
            var email = document.getElementById("email");
            var result = email.value.match(regex);

            if(result == null){
                alert("bad email");
                document.getElementById("email").value = '';
            }
        }
    </script>
</head>
<body>
<div class="change_language">
    <a href="?lang=ru"><img src="/resource/images/ru.png"></a>
    <a href="?lang=en"><img src="/resource/images/en.png"></a>
</div>
<div class="center_main_area">
    <div class="left_main_area">
        <div class="logo">
            <h1>${welcome}</h1>
        </div>
    </div>
    <div class="right_main_area">
        <div class="signin_area reg">
            <p>${signup_info}</p>
            <p>${error}</p>
            <form:form method="post" modelAttribute="userForm"  class="form-signin">
                <input class="login_fields" type="text" name="name" placeholder="${name}" required>
                <input class="login_fields" type="text" name="lastName" placeholder="${surname}" required>
                <input class="login_fields" id="email" type="email" name="email" placeholder="Email" onfocusout="check_email()" required>
                <input class="login_fields" id="pass" type="password" name="password" placeholder="${password}" required>
                <input class="login_fields" id="repeat_pass" type="password" name="confirmPassword" placeholder="${repeat_password}" onfocusout="compare_passwords()" required>
                <input class="login_buttons" id="submit" type="submit" name="submit" value="${signup}">
                <input class="login_buttons" type="button" onclick="location.href='${contextPath}/index'" value="${auth}" >
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
