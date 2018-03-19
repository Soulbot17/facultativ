<!doctype html>
<head>
    <meta charset="utf-8">
    <title>Тестовая страница</title>
    <link rel="stylesheet" href="../../css/style.css">
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
                <form method="post" action="some_url" name="">
                    <input class="login_fields" type="text" name="name" placeholder="Name" required>
                    <input class="login_fields" type="text" name="Surname" placeholder="Surname" required>
                    <input class="login_fields" id="email" type="email" name="email" placeholder="Email" onfocusout="check_email()" required>
                    <input class="login_fields" id="pass" type="password" name="password" placeholder="Password" required>
                    <input class="login_fields" id="repeat_pass" type="password" name="repeat_password" placeholder="Repeat password" onfocusout="compare_passwords()" required>
                    <input class="login_buttons" id="submit" type="submit" name="submit" value="Sign up">
                </form>
            </div>
        </div>
    </div>
</body>