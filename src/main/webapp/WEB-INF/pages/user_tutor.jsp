<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TutorPage</title>
</head>
<body>

<h1>${UserName}${UserLastName}</h1>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<head>
    <title>Tutor page</title>
    <meta charset="utf-8">
    <style>
        /*html{
            background-image: url(123.jpg);
        }*/
        body {
            margin: auto;
            background-color: dodgerblue;
            color: #fff;
            font-family: sans-serif;
            font-weight: 300;
        }

        .center_field {
            height: 100px;
            width: 1024px;
            margin: auto;
        }

        table, th, td {
            border: thin solid rgba(0, 0, 0, 0);
        }

        table {
            border-collapse: collapse;
        }

        td, th {
            width: 200px;
            height: 30px;
            text-align: center;
        }

        tr {
            background-color: #5f9ea0ad;
        }

        tr:nth-child(even) {
            background-color: cadetblue;
        }

        th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: darkorange;
            color: white;
        }

        .user_info {
            border: thin solid white;
            padding: 10px;
            margin: 10px;
            height: max-content;
            float: right;
            position: relative;
        }

        .courses_info {
            padding: 10px;
        }

        .show_student_list {
            display: none;
            padding: 10px;
        }

        h3, p {
            margin: 0;
        }

        p {
            display: inline-block;
            font-family: sans-serif;
            font-weight: 600;
            margin: 5px
        }

        form {
            display: initial;
        }

        .switcher {
            margin-top: 15px;
            display: inline-block;
        }

        .buttons, .finish_button {
            padding: 10px;
            border: 0;
            background-color: darkorange;
            color: #fff;
            font-family: sans-serif;
            font-weight: bold;
        }

        .buttons:hover {
            cursor: pointer;
        }

        h4 {
            margin-bottom: 18px;
        }

        .add_new_course {
            display: none;
            padding-top: 10px;
        }

        .new_course_inputs {
            display: block;
            width: 200px;
            height: 30px;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        .finish_button {
            background-color: #d62121;
        }

        .table_edit {
            width: 50px;
        }
    </style>
    <script>
        function change_tables() {
            var s = document.getElementsByClassName("show_student_list")[0].style.display == "block";
            if (s == "block" || s == "") {
                document.getElementsByClassName("courses_info")[0].style.display = "none";
                document.getElementsByClassName("show_student_list")[0].style.display = "block";
            } else {
                document.getElementsByClassName("courses_info")[0].style.display = "block";
                document.getElementsByClassName("show_student_list")[0].style.display = "none";
            }
        }

        function add_new_course() {
            if (document.getElementsByClassName("add_new_course")[0].style.display == "block") {
                document.getElementsByClassName("courses_info")[0].style.display = "block";
                document.getElementsByClassName("add_new_course")[0].style.display = "none";
            } else {
                document.getElementsByClassName("courses_info")[0].style.display = "none";
                document.getElementsByClassName("add_new_course")[0].style.display = "block";
            }
        }
    </script>
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
        <h4>My courses</h4>
        <table>
            <tr>
                <th>Course name</th>
                <th>Info</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="course" items="${Courses}">
                <tr>
                    <td>${course.courseName}</td>
                    <td>${course.annotation}</td>
                    <td>
                        <form:form modelAttribute="course" id="studentListDirect" method="get" action="/student_list">
                            <button class="buttons" type="submit">Show student list</button>
                            <input type="hidden" name="course" value="${course.courseId}"/>
                            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                        </form:form>
                    </td>
                    <td>
                        <form:form class="go_to_course" modelAttribute="course" method="post">
                            <input type="hidden" name="course" value="${course.courseId}">
                            <input type="submit" class="finish_button" value="Close course">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
</body>