<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!doctype html>
<head>
    <title>Страница студента</title>
    <meta charset="utf-8">
    <title><spring:message code="label.tutor_page"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
    <meta charset="utf-8">
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
                            <input type="submit" class="buttons" value="Sign up">
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
                            <input type="submit" class="buttons" value="Sign up" disabled>
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