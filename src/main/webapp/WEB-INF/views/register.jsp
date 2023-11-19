<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 19.11.2023
  Time: 14:14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" />
</head>
<body>
<header>
    <c:import url="nav.jsp"/>
</header>

<section class="login-page">
    <h2>Załóż konto</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            <form:input path="username" placeholder="Email"/><c:if test="${exist != null}"><span>Podany email jest już w użyciu</span></c:if>
        </div>
        <div class="form-group">
            <form:password path="password" id="original" placeholder="Hasło"/>
        </div>
        <div class="form-group">
            <input type="password" id="confirm" placeholder="Powtórz hasło" />
        </div>

        <div class="form-group form-group--buttons">
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" id="register" type="submit">Załóż konto</button>
        </div>
    </form:form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>

