<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 21.11.2023
  Time: 21:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Charity</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
<c:import url="nav.jsp"/>
</header>
<section class="login-page">
    <div class="steps">
        <h2>Lista użytkowników:</h2>
        <c:forEach items="${users}" var="user" varStatus="i">
        <div class="form-group form-group--inline" style="font-weight: 600; font-size: medium">
                ${i.count}. ${user.username} (${user.role}) - ${user.firstName} ${user.lastName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <a href="<c:url value="/panel/user/modify?id=${user.id}"/>"><button type="button" class="btn btn--small">Modyfikuj</button></a>&nbsp;&nbsp;
                    <a href="<c:url value="/panel/user/delete?id=${user.id}"/>"><button type="button" class="btn btn--small btn--highlighted" style="border-color: red">Usuń</button></a>
        </div>
        <br>
        </c:forEach>
        <a href="<c:url value="/panel/user/add"/>"><button class="btn ">Dodaj użytkownika</button></a>
    </div>
</section>

<c:import url="footer.jsp"/>
</body>
</html>
