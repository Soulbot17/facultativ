<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<mvc:resources mapping="/resources/**" location="/WEB-INF/resources/"/>
<html>
<head>
    <title>TutorPage</title>
</head>
<body>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<head>
    <title>Tutor page</title>
    <meta charset="utf-8">
    <meta charset="utf-8">
    <title><spring:message code="label.auth_page"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
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
                            <input type="submit" class="finish_button buttons" value="Close course">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
</body>