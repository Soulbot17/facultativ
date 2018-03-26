<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<mvc:resources mapping="/resources/**" location="/WEB-INF/resources/"/>
<html>
<head>
    <title>Student info</title>
</head>
<body>

<h1>${StudentLastName}</h1>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<head>
    <title>Student list</title>
    <meta charset="utf-8">
    <head>
        <meta charset="utf-8">
        <title><spring:message code="label.auth_page"/></title>
        <link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
        <link type="text/css" href="/resource/css/style.css" rel="stylesheet">
    </head>

</head>
<body>
<div class="center_field">
    <div class="user_info">
        <p>${UserName} ${UserLastName}</p>
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <button class="buttons" type="submit">Logout</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

    <div class="courses_info">
        <h4>Student list</h4>
        <table>
            <tr>
                <th>Student name</th>
                <th>Mark</th>
                <th>Feedback</th>
                <th></th>
            </tr>

            <c:forEach items="${NoFeedback}" var="cur">
                <tr>
                    <td>${cur.key.lastName} ${cur.key.name}</td>

                    <form:form modelAttribute="course" id="studentListDirect" method="post"
                               action="/student_list">
                        <td>
                            <input class="login_fields" maxlength="1" type="text" name="mark" placeholder="Mark"
                                   required>
                            <input type="text" hidden name="courseId" value="${CourseId}">
                            <input type="text" hidden name="studentId" value="${cur.key.userId}">

                        </td>
                        <td>
                            <textarea class="login_fields" name="feedback" placeholder="feedback" required></textarea>
                        </td>
                        <td>
                            <button class="buttons" type="submit">add feedback</button>
                        </td>

                    </form:form>
                </tr>
            </c:forEach>

            <c:forEach items="${WithFeedback}" var="cur">
                <tr>
                    <td>${cur.key.lastName} ${cur.key.name}</td>
                    <td>${cur.value.studentMark}</td>
                    <td>${cur.value.studentFeedback}</td>
                    <td></td>
                </tr>
            </c:forEach>

        </table>
        <form id="studentListDirect" method="get" action="/user_tutor">
            <button class="buttons" type="submit">Show courses</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

</div>
</body>