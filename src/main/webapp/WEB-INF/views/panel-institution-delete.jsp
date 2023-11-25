<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 25.11.2023
  Time: 19:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Charity</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>
<header>
    <c:import url="nav.jsp"/>
</header>
<section class="login-page">
    <h2>Czy na pewno chcesz usunąć tego użytkownika?</h2>
    <div class="admin">
        ${institution.name} - ${institution.description}<br>
    </div>
    <div>
        <form method="post">
            <input type="hidden" name="id" value="${institution.id}">
            <a href="<c:url value="/panel/crud"/>"><button class="btn" type="button">Nie</button> </a>
            <button class="btn btn--highlighted btn--delete" type="submit">Tak</button>
        </form>
    </div>
</section>

</body>
</html>

