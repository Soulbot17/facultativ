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
    <meta charset="utf-8">
    <title>Страница регистрации</title>
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
            width: 100px;
            background-color: #fff;
            border: 0;
            height: 25px;
        }
        .login_buttons:hover{
            cursor: pointer;
        }
        .logo{
            margin-top: 250px;
        }
        .result{
            color: lightgreen;
            font-weight: 400;
        }
    </style>
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
<div class="center_main_area">
    <div class="left_main_area">
        <div class="logo">
            <h1>Training your skills</h1>
        </div>
    </div>
    <div class="right_main_area">
        <div class="signin_area">
            <p>Sign up a new account</p>
            <p>${error}</p>
            <form:form method="post" modelAttribute="userForm"  class="form-signin">
                <input class="login_fields" type="text" name="name" placeholder="Name" required>
                <input class="login_fields" type="text" name="lastName" placeholder="Surname" required>
                <input class="login_fields" id="email" type="email" name="email" placeholder="Email" onfocusout="check_email()" required>
                <input class="login_fields" id="pass" type="password" name="password" placeholder="Password" required>
                <input class="login_fields" id="repeat_pass" type="password" name="confirmPassword" placeholder="Repeat password" onfocusout="compare_passwords()" required>
                <input class="login_buttons" id="submit" type="submit" name="submit" value="Sign up">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
