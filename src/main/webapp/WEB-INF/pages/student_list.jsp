<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<mvc:resources mapping="/resources/**" location="/WEB-INF/resources/"/>

<!doctype html>
<head>
    <title><spring:message code="label.student_list"/></title>
    <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
    <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
    <meta charset="utf-8">
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
                <button class="buttons" type="submit"><spring:message code="label.logout"/> </button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>

        <div class="courses_info">
            <h4><spring:message code="label.student_list"/></h4>
            <table>
                <tr>
                    <th><spring:message code="label.student_name"/></th>
                    <th><spring:message code="label.mark"/></th>
                    <th><spring:message code="label.feedback"/></th>
                    <th></th>
                </tr>
                <c:forEach var="student" items="${Students}">
                    <tr>
                        <td>${student.lastName} ${student.name}</td>
                        <form:form modelAttribute="course" id="studentListDirect" method="post"
                                   action="/student_list">
                            <td>
                                <input class="student_list_text" maxlength="1" type="text" name="mark" required>
                                <input type="text" hidden name="courseId" value="${CourseId}">
                                <input type="text" hidden name="studentId" value="${student.userId}">
                            </td>
                            <td>
                                <textarea class="student_list_text" name="feedback" required></textarea>
                            </td>
                            <td>
                                <button class="buttons long_button" type="submit"><spring:message code="label.add_feedback"/></button>
                            </td>

                        </form:form>

                    </tr>
                </c:forEach>

            </table>
            <form id="studentListDirect" method="get" action="/user_tutor">
                <button class="buttons" type="submit"><spring:message code="label.show_courses"/></button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
    </div>
</body>