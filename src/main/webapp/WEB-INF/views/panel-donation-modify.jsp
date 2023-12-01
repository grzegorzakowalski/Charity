<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 01.12.2023
  Time: 19:13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h2>Zmodyfikuj dotację</h2>
    <form:form method="post" modelAttribute="donation">
        <div class="form-group admin">
            Ilość worków:<br>
            <form:input path="quantity"/>
        </div>
        <div class="form-group admin">
            Lista kategorii:
        </div>
        <c:forEach items="${categories}" var="category">
            <div class="form-group form-group--checkbox">
                <label>
                    <form:checkbox path="categories" value="${category}"/>

                    <span class="checkbox"></span>
                    <span class="description">${category.name}</span>
                </label>
            </div>
        </c:forEach>
        <div class="form-group admin">Aktualna fundacja to: ${donation.institution.name}</div>
        <c:forEach items="${institutions}" var="institution">
            <div class="form-group form-group--checkbox">
                <label>
                    <form:radiobutton path="institution" value="${institution}"/>
                    <span class="checkbox radio"></span>
                    <span class="description">
                            <div class="title">${institution.name}</div>
                            <div class="subtitle">${institution.description}</div>
                        </span>
                </label>
            </div>
        </c:forEach>
        <form:hidden path="id"/>
        <h3 class="admin">Modyfikuj adres oraz termin odbioru rzecz przez kuriera:</h3>

        <div class="form-section form-section--columns">
            <div class="form-section--column">
                <h4>Adres odbioru</h4>
                <div class="form-group form-group--inline">
                    <label>
                        Ulica <form:input path="street"/>
                    </label>
                </div>

                <div class="form-group form-group--inline">
                    <label>
                        Miasto <form:input path="city"/>
                    </label>
                </div>

                <div class="form-group form-group--inline">
                    <label>
                        Kod pocztowy <form:input path="zipCode"/>
                    </label>
                </div>

                <div class="form-group form-group--inline">
                    <label>
                        Numer telefonu <form:input path="phoneNumber"/>
                    </label>
                </div>
            </div>

            <div class="form-section--column">
                <h4>Termin odbioru</h4>
                <div class="form-group form-group--inline">
                    <label>
                        Data <form:input path="pickUpDate" type="date"/>
                    </label>
                </div>

                <div class="form-group form-group--inline">
                    <label>
                        Godzina <form:input path="pickUpTime" type="time"/>
                    </label>
                </div>

                <div class="form-group form-group--inline">
                    <label>
                        Uwagi dla kuriera
                        <form:textarea path="pickUpComment" rows="5"/>
                    </label>
                </div>
            </div>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zmodyfikuj dotację</button>
        </div>
    </form:form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
