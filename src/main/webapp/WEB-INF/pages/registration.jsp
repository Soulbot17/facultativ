<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<head>
    <meta charset="utf-8">
    <title>Тестовая страница</title>
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
                <form method="post" action="/addUser">
                    <input class="login_fields" type="text" name="name" placeholder="Name" required>
                    <input class="login_fields" type="text" name="Surname" placeholder="Surname" required>
                    <input class="login_fields" id="email" type="email" name="email" placeholder="Email" onfocusout="check_email()" required>
                    <input class="login_fields" id="pass" type="password" name="password" placeholder="Password" required>
                    <input class="login_fields" id="repeat_pass" type="password" placeholder="Repeat password" onfocusout="compare_passwords()" required>
                    <input class="login_buttons" id="submit" type="submit" name="submit" value="Sign up">
                    <input class="login_buttons" type="button" name="go_register" value="Sigh in" onclick="location.href='/'">
                </form>
                <p class="result">${result}</p>
            </div>
        </div>
    </div>
</body>