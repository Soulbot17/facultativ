<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<head>
    <title>Страница студента</title>
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

        .ended_courses_info {
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
            display: inline-block;
        }

        .switcher {
            margin-top: 15px;
        }

        .buttons {
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

        .go_to_course > .buttons {
            width: 190px;
            margin: 5px;
        }

        input[type="submit"][disabled] {
            background-color: grey;
        }

        h4 {
            margin-bottom: 18px;
        }
    </style>
    <script>
        function change_tables() {
            var element = document.getElementsByClassName("courses_info")[0].style.display;
            if (element === "block" || element === "") {
                document.getElementsByClassName("courses_info")[0].style.display = "none";
                document.getElementsByClassName("ended_courses_info")[0].style.display = "block";
            } else {
                document.getElementsByClassName("courses_info")[0].style.display = "block";
                document.getElementsByClassName("ended_courses_info")[0].style.display = "none";
            }
        }
    </script>
</head>
<body>
<div class="center_field">
    <div class="user_info">
        <p>${userName} ${userLastName}</p>
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
                <th>Info</th>
                <th>Mark</th>
                <th>Tutor review</th>
            </tr>
            <%--FIX IT--%>
            <c:forEach var="finished" items="${finishedMap}">
                <tr>
                    <td>${finished.key.courseName}</td>
                    <td>${finished.key.annotation}</td>
                    <td>${finished.value.studentMark}</td>
                    <td>${finished.value.studentFeedback}</td>
                </tr>
            </c:forEach>
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
                <th>Info</th>
            </tr>
            <c:forEach var="available" items="${availableCourses}">
                <tr>
                    <td>${available.courseName}</td>
                    <td>${available.annotation}</td>
                    <td>
                        <form:form class="go_to_course" modelAttribute="course" method="post">
                            <input type="hidden" name="course" value="${available.courseId}">
                            <input type="submit" class="buttons" value="Attend">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>

            <c:forEach var="waited" items="${waitedCourses}">
                <tr>
                    <td>${waited.courseName}</td>
                    <td>${waited.annotation}</td>
                    <td>
                        <form class="go_to_course">
                            <input type="submit" class="buttons" value="In wainitng" disabled>
                        </form>
                    </td>
                </tr>
            </c:forEach>

            <c:forEach var="active" items="${activeCourses}">
                <tr>
                    <td>${active.courseName}</td>
                    <td>${active.annotation}</td>
                    <td>
                        <form class="go_to_course">
                            <input type="submit" class="buttons" value="In progress" disabled>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="switcher">
            <input type="button" class="buttons" onclick="change_tables()" value="Show ended courses">
        </div>
    </div>
</div>
</body>