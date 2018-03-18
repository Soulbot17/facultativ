<!doctype html>
<head>
    <title>Страница студента</title>
    <meta charset="utf-8">
    <style>
        /*html{
            background-image: url(123.jpg);
        }*/
        body{
            margin: auto;
            background-color: dodgerblue;
            color: #fff;
            font-family: sans-serif;
            font-weight: 300px;
        }

        .center_field{
            height: 100px;
            width: 1024px;
            margin: auto;
        }
        table, th, td{
            border: thin solid rgba(0,0,0,0);
        }
        table{
            border-collapse: collapse;
        }
        td{
            width: 200px;
            height: 30px;
            text-align: center;
        }
        tr{
            background-color: #5f9ea0ad;
        }
        tr:nth-child(even){
            background-color: cadetblue;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: darkorange;
            color: white;
        }
        .user_info{
            border: thin solid white;
            padding: 10px;
            margin: 10px;
            height: max-content;
            float: right;
            position: relative;
        }
        .courses_info{
            padding: 10px;
        }
        .ended_courses_info{
            display: none;
            padding: 10px;
        }
        h3, p{
            margin: 0;
        }
        p{
            display: inline-block;
            font-family: sans-serif;
            font-weight: 600;
            margin: 5px
        }
        form{
            display: inline-block;
        }
        .switcher{
            margin-top: 15px;
        }
        .buttons{
            padding: 10px;
            border: 0;
            background-color: darkorange;
            color: #fff;
            font-family: sans-serif;
            font-weight: bold;
        }
        .buttons:hover{
            cursor: pointer;
        }
        h4{
            margin-bottom: 18px;
        }
    </style>
    <script>
        function change_tables(){
            if(document.getElementsByClassName("courses_info")[0].style.display == "block"){
                document.getElementsByClassName("courses_info")[0].style.display = "none";
                document.getElementsByClassName("ended_courses_info")[0].style.display = "block";
            }else{
                document.getElementsByClassName("courses_info")[0].style.display = "block";
                document.getElementsByClassName("ended_courses_info")[0].style.display = "none";
            }

        }
    </script>
</head>
<body>
<div class="center_field">
    <div class="user_info">
        <p>Jack Nicholson</p>
        <form method="post" action="">
            <input type="submit" name="exit" class="buttons" value="Exit">
        </form>
    </div>
    <div class="ended_courses_info">
        <h4>Ended courses</h4>
        <table>
            <tr>
                <th>Course name</th>
                <th>Date</th>
                <th>Info</th>
                <th>Enter</th>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
        </table>
        <div class="switcher">
            <input type="button" class="buttons" onclick="change_tables()" value="Show available courses">
        </div>
    </div>
    <div class="courses_info">
        <h4>Available courses</h4>
        <table>
            <tr>
                <th>Course name</th>
                <th>Date</th>
                <th>Info</th>
                <th>Enter</th>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
        </table>
        <div class="switcher">
            <input type="button" class="buttons" onclick="change_tables()" value="Show ended courses">
        </div>
    </div>
</div>
</body>