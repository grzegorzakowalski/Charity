<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 29.11.2023
  Time: 18:41
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
    <h2>Przypomnij hasło</h2>
    <form method="post">
        <div class="form-group">
            <label>
                <span class="admin">Podaj adres email:</span>
                <input type="email" name="email" placeholder="Email" />
            </label><br>
            <c:if test="${ msg != null}"><span class="success">${msg}</span></c:if>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Przypomnij hasło</button>
        </div>
    </form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
