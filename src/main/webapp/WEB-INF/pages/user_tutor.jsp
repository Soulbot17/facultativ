<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<head>
    <title>Tutor page</title>
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
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
            display: block;
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
    <link type="text/css" href="${contextPath}/resource/css/style.css" rel="stylesheet">
</head>
<body>
<div class="change_language">
    <a href="?lang=ru"><img src="/resource/images/ru.png"></a>
    <a href="?lang=en"><img src="/resource/images/en.png"></a>
</div>
<div class="center_field">
    <div class="user_info">
        <p>${UserName} ${UserLastName}</p>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <button class="buttons" type="submit"><spring:message code="label.logout"/></button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <div class="courses_info">
        <h4><spring:message code="label.my_courses"/></h4>
        <table>
            <tr>
                <th><spring:message code="label.course_name"/></th>
                <th><spring:message code="label.course_info"/></th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="course" items="${Courses}">
                <tr>
                    <td>${course.courseName}</td>
                    <td>${course.annotation}</td>
                    <td>
                        <form:form modelAttribute="course" id="studentListDirect" method="get" action="/student_list">
                            <button class="buttons" type="submit"><spring:message
                                    code="label.show_student_list"/></button>
                            <input type="hidden" name="course" value="${course.courseId}"/>
                            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                        </form:form>
                    </td>
                    <td>
                        <form:form class="go_to_course" modelAttribute="course" method="post">
                            <input type="hidden" name="course" value="${course.courseId}">
                            <input type="submit" class="finish_button buttons" value="Close course">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>