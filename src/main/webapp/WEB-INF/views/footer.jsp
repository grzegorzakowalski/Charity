<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 15.11.2023
  Time: 17:51
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer>
    <div class="contact">
        <h2>Skontaktuj się z nami</h2>
        <h3>Formularz kontaktowy</h3>
        <form:form modelAttribute="contactMSG" class="form--contact" method="post" action="/contact">
            <div class="form-group form-group--50"><form:input path="name" placeholder="Imię"/></div>
            <div class="form-group form-group--50"><form:input path="surname" placeholder="Nazwisko"/></div>

            <div class="form-group"><form:textarea path="msg" placeholder="Wiadomość" rows="1"/></div>

            <button class="btn" type="submit">Wyślij</button>
        </form:form>
    </div>
    <div class="bottom-line">
        <span class="bottom-line--copy">Copyright &copy; 2018</span>
        <div class="bottom-line--icons">
            <a href="#" class="btn btn--small"><img src="<c:url value="/resources/images/icon-facebook.svg"/>"/></a> <a href="#"
                                                                                                       class="btn btn--small"><img
                src="<c:url value="/resources/images/icon-instagram.svg"/>"/></a>
        </div>
    </div>
</footer>

<script src="<c:url value="/resources/js/app.js"/>"></script>

