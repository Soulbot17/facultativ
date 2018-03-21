<%--
  Created by IntelliJ IDEA.
  User: banka
  Date: 18.03.18
  Time: 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
${UserName}
<h1>Tutor info</h1>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<head>
    <title>Tutor page</title>
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
            font-weight: 300;
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
            display: initial;
        }
        .switcher{
            margin-top: 15px;
            display: inline-block;
        }
        .buttons, .delete_button{
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
        .add_new_course{
            display: none;
            padding-top: 10px;
        }
        .new_course_inputs{
            display: block;
            width: 200px;
            height: 30px;
            margin-top: 10px;
            margin-bottom: 10px;
        }
        .delete_button{
            background-color: #d62121;
        }
        .table_edit{
            width: 50px;
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
        function add_new_course(){
            if(document.getElementsByClassName("add_new_course")[0].style.display == "block"){
                document.getElementsByClassName("courses_info")[0].style.display = "block";
                document.getElementsByClassName("add_new_course")[0].style.display = "none";
            }else{
                document.getElementsByClassName("courses_info")[0].style.display = "none";
                document.getElementsByClassName("add_new_course")[0].style.display = "block";
            }
        }
    </script>
</head>
<body>
<div class="center_field">
    <div class="user_info">
        <p>${UserName}</p>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <button class="buttons" type="submit">Logout</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <div class="ended_courses_info">
        <h4>Ended courses</h4>
        <table>
            <tr>
                <th>Course name</th>
                <th>Date</th>
                <th>Info</th>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
            </tr>
        </table>
        <div class="switcher">
            <input type="button" class="buttons" onclick="change_tables()" value="Show available courses">
        </div>
    </div>
    <div class="courses_info">
        <h4>My courses</h4>
        <table>
            <tr>
                <th>Course name</th>
                <th>Date</th>
                <th>Info</th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td class="table_edit">
                    <form>
                        <input class="buttons" type="submit" value="Edit">
                    </form>
                </td>
                <td class="table_edit">
                    <form>
                        <input class="delete_button" type="submit" value="Delete">
                    </form>
                </td>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td class="table_edit">
                    <form>
                        <input class="buttons" type="submit" value="Edit">
                    </form>
                </td>
                <td class="table_edit">
                    <form>
                        <input class="delete_button" type="submit" value="Delete">
                    </form>
                </td>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td class="table_edit">
                    <form>
                        <input class="buttons" type="submit" value="Edit">
                    </form>
                </td>
                <td class="table_edit">
                    <form>
                        <input class="delete_button" type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </table>
        <div class="switcher">
            <input type="button" class="buttons" onclick="change_tables()" value="Show ended courses">
        </div>
        <div class="switcher">
            <input type="button" class="buttons" onclick="add_new_course()" value="Add new course">
        </div>
    </div>
    <div class="add_new_course">
        <h4>Add new course</h4>
        <form>
            <input class="new_course_inputs" type="text" placeholder="Course name" name="course_name">
            <input class="new_course_inputs" type="date" placeholder="Start date" name="start_date">
            <input class="new_course_inputs" type="text" placeholder="Info" name="course_info">
            <input class="buttons" type="submit" value="Create course">
        </form>
        <div class="switcher">
            <input type="button" class="buttons" onclick="add_new_course()" value="Show my courses">
        </div>
    </div>
</div>
</body>