<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.11.2023
  Time: 19:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
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
    <h2>Podaj nowe hasło</h2>
    <form id="password-form" method="post">
        <div class="form-group">
            <label>
                <p class="admin">Podaj hasło:</p>
                <input type="password" id="original" name="password" placeholder="Hasło" />
            </label>
        </div><div class="form-group">
        <label>
            <p class="admin">Powtórz hasło:</p>
            <input type="password" id="confirm" placeholder="Hasło" />
        </label>
    </div>
        <input type="hidden" name="uuid" value="${uuid}">
        <div class="form-group form-group--buttons">
            <button class="btn" id="register" type="submit">Zmień hasło</button>
        </div>
    </form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
