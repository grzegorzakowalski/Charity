<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 25.11.2023
  Time: 13:06
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
    <h2>Zmodyfikuj użytkownika</h2>
    <form:form method="post" modelAttribute="user">
        <div class="form-group">
            Login:<br>
            <form:input path="username"/>
        </div>
        <div class="form-group">
            Hasło:<br>
            <form:password path="password" />
        </div>
        <div class="form-group">
            Imię:<br>
            <form:input path="firstName"/>
        </div>
        <div class="form-group">
            Nazwisko:<br>
            <form:input path="lastName"/>
        </div>
        <div class="form-group">
            Czy ma możliwość logowania się:<br>
            <form:select path="active">
                    <form:options items="${isActive}"/>
            </form:select>
        </div>
        <div class="form-group">
            Rola:<br>
            <form:select path="role">
                <form:options items="${roles}"/>
            </form:select>
        </div>
        <form:hidden path="id"/>
        <form:hidden path="donations"/>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zmodyfikuj użytkownika</button>
        </div>
    </form:form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
