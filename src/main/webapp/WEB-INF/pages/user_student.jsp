<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!doctype html>
<head>
    <title><spring:message code="label.student_page"/></title>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link href="/resource/css/style.css" rel="stylesheet">
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
    <div class="change_language">
        <a href="?lang=ru"><img src="/resource/images/ru.png"></a>
        <a href="?lang=en"><img src="/resource/images/en.png"></a>
    </div>
    <div class="center_field">
        <div class="user_info">
            <p>${userName} ${userLastName}</p>
            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <button class="buttons" type="submit"><spring:message code="label.logout"/></button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        <div class="ended_courses_info">
            <h4><spring:message code="label.finished_courses"/></h4>
            <table>
                <tr>
                    <th><spring:message code="label.course_name"/></th>
                    <th><spring:message code="label.course_info"/></th>
                    <th><spring:message code="label.mark"/></th>
                    <th><spring:message code="label.feedback"/></th>
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
                <input type="button" class="buttons" onclick="change_tables()" value="<spring:message code="label.available_courses"/>">
            </div>
        </div>

        <div class="courses_info">
            <h4><spring:message code="label.available_courses"/></h4>
            <table>
                <tr>
                    <th><spring:message code="label.course_name"/></th>
                    <th><spring:message code="label.course_info"/></th>
                    <th></th>
                </tr>
                <c:forEach var="available" items="${availableCourses}">
                    <tr>
                        <td>${available.courseName}</td>
                        <td>${available.annotation}</td>
                        <td>
                            <form:form class="go_to_course" modelAttribute="course" method="post">
                                <input type="hidden" name="course" value="${available.courseId}">
                                <input type="submit" class="buttons" value="<spring:message code="label.signup"/>">
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
                                <input type="submit" class="buttons" value="<spring:message code="label.signup"/>" disabled>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="switcher">
                <input type="button" class="buttons" onclick="change_tables()"
                       value="<spring:message code="label.finished_courses"/>">
            </div>
        </div>
    </div>
</body>