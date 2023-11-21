<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 21.11.2023
  Time: 19:18
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
<c:import url="header.jsp"/>
<h2>
    Ilość twoich dotacji:
</h2>
<section class="stats">
    <div class="container container--85">
        <div class="stats--item">
            <em>${bags}</em>
            <h3>Oddanych worków</h3>
            <p>
                <c:if test="${bags == 0}">Nie możemy się doczekać twojej pierwszej dotacji!</c:if>
                <c:if test="${bags >= 0}">Dziękujemy za każdą dotację i mamy nadzieję, że będzie ich jeszcze więcej!</c:if>
            </p>
        </div>

        <div class="stats--item">
            <em>${donations}</em>
            <h3>Przekazanych darów</h3>
            <p>
                <c:if test="${bags == 0}">Nie możemy się doczekać twojej pierwszej dotacji!</c:if>
                <c:if test="${bags >= 0}">Dziękujemy za każdą dotację i mamy nadzieję, że będzie ich jeszcze więcej!</c:if>
            </p>
        </div>

    </div>
</section>
<section class="login-page">

    <form:form method="post" modelAttribute="user">
        <div class="steps">
            <h2>Zmień swoje dane osobowe:</h2>
            <div class="form-group form-group--inline">
                <label>
                    Zmień imię:
                    <form:input path="firstName" value="${user.firstName}"/>
                </label>
            </div>
            <br>
            <div class="form-group form-group--inline">
                <label>
                    Zmień nazwisko:
                    <form:input path="lastName" value="${user.lastName}"/>
                </label>
            </div>
            <br>
            <div class="form-group form-group--inline">
                <label>
                    Zmień email:
                    <form:input path="username" value="${user.username}"/>
                </label>
            </div>
            <form:hidden path="id"/>
            <form:hidden path="password"/>
            <form:hidden path="role"/>
            <form:hidden path="donations"/>

            <div class="form-group form-group--buttons">
                <button type="submit" class="btn">Zatwierdź</button>
            </div>
        </div>
    </form:form>


    <form method="post" action="<c:url value="/panel/password"/>">
        <div class="steps">
            <h2>Zmień hasło:</h2>
            <div class="form-group form-group--inline">
                <label>
                    Podaj nowe hasło:
                    <input type="password" name="password" id="original" placeholder="Nowe hasło">
                </label>
            </div>
            <div class="form-group form-group--inline">
                <label>
                    Powtórz nowe hasło:
                    <input type="password" id="confirm" placeholder="Nowe hasło">
                </label>
            </div>
            <div class="form-group form-group--buttons">
                <button type="submit" class="btn">Zatwierdź</button>
            </div>
        </div>

    </form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
