<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<head>
    <title><spring:message code="label.tutor_page"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
    <meta charset="utf-8">
</head>
<body>

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
                            <button class="buttons long_button" type="submit"><spring:message
                                    code="label.show_student_list"/></button>
                            <input type="hidden" name="course" value="${course.courseId}"/>
                            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
                        </form:form>
                    </td>
                    <td>
                        <form:form class="go_to_course" modelAttribute="course" method="post">
                            <input type="hidden" name="course" value="${course.courseId}">
                            <input type="submit" class="finish_button buttons" value="<spring:message code="label.close_course"/>">
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>