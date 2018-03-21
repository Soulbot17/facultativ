<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<!doctype html>
<head>
    <title>Страница студента</title>
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
        td{
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
            padding: 10px;
        }
        .ended_courses_info{
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
            display: inline-block;
        }
        .switcher{
            margin-top: 15px;
        }
        .buttons{
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
        .go_to_course > .buttons{
            width: 190px;
            margin: 5px;
        }
        input[type="submit"][disabled]{
            background-color: grey;
        }
        h4{
            margin-bottom: 18px;
        }
    </style>
    <script>
        function change_tables(){
            if(document.getElementsByClassName("courses_info")[0].style.display == "block"){
                document.getElementsByClassName("courses_info")[0].style.display = "none";
                document.getElementsByClassName("ended_courses_info")[0].style.display = "block";
            }else{
                document.getElementsByClassName("courses_info")[0].style.display = "block";
                document.getElementsByClassName("ended_courses_info")[0].style.display = "none";
            }
        }
    </script>
</head>
<body>
<div class="center_field">
    <div class="user_info">
        <p>${UserName}</p>
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
                <th>Date</th>
                <th>Info</th>
                <th>Tutor review</th>
            </tr>
            <c:forEach items="${courses}" var="course">
                <tr>
                    <td>${course.getName()}</td>
                    <td>${course.getDate()}</td>
                    <td>${course.getInfo()}</td>
                    <td></td>
                </tr>
            </c:forEach>
            <%--<tr>
                <td>HTML/CSS/JAVASCRIPT</td>
                <td>15/05/2018</td>
                <td>Ajkhasdkjgh ksjdhgkajsdh skdjhg ksadlj hgksldj hgsalkdjgh kasldjhg sakdjhgsdakjg</td>
                <td>ASfdgdsfgkjl dkfjhg kdlfjhg lksdjfhgksldfnvxzlkjcv lskdjngf dosuighsdfkjgn ksdjfhg adlfkjgsdf</td>
            </tr>
            <tr>
                <td>Java Spring</td>
                <td>20/05/2018</td>
                <td>Ajkhasdkjgh ksjdhgkajsdh skdjhg ksadlj hgksldj hgsalkdjgh kasldjhg sakdjhgsdakjg jksdfhgl kjshdfgkj
                    skdjfhgskljdfhgslk djfhgkljsdfhglksjdf gbskdlbjxckljgsdklfjg sdfkjghs ldfgjh sdfkjhg sdlfkjhg lsdf</td>
                <td>ASfdgdsfgkjl dkfjhg kdlfjhg lksdjfhgksldfnvxzlkjcv lskdjngf dosuighsdfkjgn ksdjfhg adlfkjgsdf dfgfd
                    sdfgsdfg sdfg sdf gsdfgf sjkdfhglskdfjhg ksldjfhglsdkjfhg sdfg</td>
            </tr>--%>
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
                <th>Date</th>
                <th>Info</th>
                <th></th>
            </tr>
            <tr>
                <td>Git</td>
                <td>11/11/2018</td>
                <td>Ajkhf klajdshf lkjsadhfl kjasdhflk jasdhfl kjashdf</td>
                <td>
                    <form class="go_to_course">
                        <input type="submit" class="buttons" value="Enter">
                    </form>
                </td>
            </tr>
            <tr>
                <td>SQL, MySql</td>
                <td>10/10/2018</td>
                <td>ASdkjfhs dklfjshd lfkjsdh lkjsdhf lksjdhf ksljdhf slkjd hfdh gkjhfdgkd fjhg dflkjg hdslfkj gdl</td>
                <td>
                    <form class="go_to_course">
                        <input type="submit" class="buttons" value="Invited" disabled>
                    </form>
                </td>
            </tr>
            <tr>
                <td>Java 11</td>
                <td>12/12/2018</td>
                <td>Ajhflsdkjhf lkfjdhg lkdsjfhg lkdsjfhgl ksjdfhglsdkjfhglk jhsdfl gkjhsdflkjghsd lfkjgshfdl gkjdsf</td>
                <td>
                    <form class="go_to_course">
                        <input type="submit" class="buttons" value="Enter">
                    </form>
                </td>
            </tr>
        </table>
        <div class="switcher">
            <input type="button" class="buttons" onclick="change_tables()" value="Show ended courses">
        </div>
    </div>
</div>
</body>