<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 19.11.2023
  Time: 15:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="container container--70">

    <ul class="nav--actions">
        <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>

    <ul>
        <li><a href="#" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border">O nas</a></li>
        <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
