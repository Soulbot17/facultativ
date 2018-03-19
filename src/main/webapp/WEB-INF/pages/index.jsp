<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <meta charset="utf-8">
    <title>Test page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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
                <form method="post" action="/login" name="">
                    <input class="login_fields" type="email" name="email" placeholder="Email" required>
                    <input class="login_fields" type="password" name="password" placeholder="Password" required>
                    <input class="login_buttons" type="submit" name="submit" value="Sign in">
                    <input class="login_buttons" type="button" name="go_register" value="Sigh up" onclick="location.href='registration.jsp'">
                </form>
            </div>
        </div>
    </div>
</body>