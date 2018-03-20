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

<html>
<head>
    <meta charset="utf-8">
    <title>Тестовая страница</title>
    <link rel="stylesheet" href="../../resource/css/style.css">
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
            <form:form method="post" modelAttribute="userForm"  class="form-signin">
                <form:input name="name" type="text" path="name" class="form-control" placeholder="Name"
                            autofocus="true" />
                <form:input name="Surname" type="text" path="lastName" class="form-control" placeholder="Surname"
                            autofocus="true" />
                <form:input name="email" type="text" path="email" class="form-control" placeholder="email"
                            autofocus="true" />
                <form:input name="password" type="password" path="password" class="form-control" placeholder="password"
                            autofocus="true" />
                <form:input  name="repeat_password" type="password" path="confirmPassword" class="form-control" placeholder="Confirm your password"
                            autofocus="true" />
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
                <%--<input class="login_fields" type="text" name="name" placeholder="Name" required>--%>
                <%--<input class="login_fields" type="text" name="Surname" placeholder="Surname" required>--%>
                <%--<input class="login_fields" id="email" type="email" name="email" placeholder="Email" onfocusout="check_email()" required>--%>
                <%--<input class="login_fields" id="pass" type="password" name="password" placeholder="Password" required>--%>
                <%--<input class="login_fields" id="repeat_pass" type="password" name="repeat_password" placeholder="Repeat password" onfocusout="compare_passwords()" required>--%>
                <%--<input class="login_buttons" id="submit" type="submit" name="submit" value="Sign up">--%>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
