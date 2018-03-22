<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student list</title>
</head>
<body>

<h1>${CourseName}</h1>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<head>
    <title>Student list</title>
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
        td, th{
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
            display: block;
            padding: 10px;
        }
        .show_student_list{
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
        .buttons, .finish_button{
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
        .finish_button{
            background-color: #d62121;
        }
        .table_edit{
            width: 50px;
        }
    </style>

</head>
<body>
<div class="center_field">
    <div class="user_info">
        <p>${UserLastName}</p>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <button class="buttons" type="submit">Logout</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <div class="courses_info">
        <h4>Student list</h4>
        <table>
            <tr>
                <th>Last name</th>
                <th>Name</th>
                <th>Mark</th>
                <th>Feedback</th>
            </tr>
            <c:forEach var="student" items="${Students}">
                <tr>
                    <td>${student.lastName}</td>
                    <td>${student.name}</td>
                    <td></td>
                    <td></td>
                </tr>
            </c:forEach>

        </table>
        <form  id="studentListDirect" method="get" action="/user_tutor">
            <button class="buttons" type="submit">Show courses</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

</div>
</body>