<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 19.11.2023
  Time: 15:36
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="container container--70">
    <sec:authorize access="isAnonymous()">
        <ul class="nav--actions">
            <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border <c:if test="${active.equals('login')}">active</c:if>">Zaloguj</a></li>
            <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted <c:if test="${active.equals('register')}">active</c:if>">Załóż konto</a></li>
        </ul>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <ul class="nav--actions">
            <li class="logged-user">
                Witaj ${user.firstName}
                <ul class="dropdown">
                    <li><a href="<c:url value="/panel/user"/>">Profil</a></li>
                    <li><a href="#">Moje zbiórki</a></li>
                    <li><a href="<c:url value="/logout"/>">Wyloguj</a></li>
                </ul>
            </li>
        </ul>
    </sec:authorize>

    <ul>
        <li><a href="<c:url value="/"/>" class="btn btn--without-border <c:if test="${active.equals('home')}">active</c:if>">Start</a></li>
        <li><a href="#" class="btn btn--without-border <c:if test="${active.equals('about')}">active</c:if>">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border <c:if test="${active.equals('aboutUs')}">active</c:if>">O nas</a></li>
        <li><a href="<c:url value="/institutions"/>" class="btn btn--without-border <c:if test="${active.equals('institutions')}">active</c:if>">Fundacje i organizacje</a></li>
        <sec:authorize access="isAuthenticated()"><li><a href="<c:url value="/form"/>" class="btn btn--without-border <c:if test="${active.equals('form')}">active</c:if>">Przekaż dary</a></li></sec:authorize>
        <li><a href="#" class="btn btn--without-border <c:if test="${active.equals('contact')}">active</c:if>">Kontakt</a></li>
    </ul>
</nav>
