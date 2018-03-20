<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<head>
    <meta charset="utf-8">
    <title>Test page</title>
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
    </style>
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
                    <input class="login_buttons" type="button" name="go_register" value="Sigh up" onclick="location.href='/registration'">
                </form>
            </div>
        </div>
    </div>
</body>