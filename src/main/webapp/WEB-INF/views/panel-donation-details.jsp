<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 26.11.2023
  Time: 16:08
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
<section class="form--steps">
    <div class="form--steps-container">
        <form method="post">
        <div class="active">
            <h3>Detale Twojej darowizny</h3>
            <div class="summary">
                <div class="form-section">
                    <h4><c:if test="${donation.isPicked}">Oddałeś:</c:if><c:if test="${!donation.isPicked}">Oddajesz:</c:if></h4>
                    <ul>
                        <li>
                            <span class="icon icon-bag"></span>
                            <span class="summary--text">${donation.quantity} <c:if test="${donation.quantity > 1}">worki </c:if><c:if test="${donation.quantity == 1}">worek </c:if>
                                <c:forEach items="${donation.categories}" var="category" varStatus="i">
                                    ${category.name}<c:if test="${!(donation.categories.size() == i.count)}">,&nbsp;</c:if>
                                </c:forEach>.</span>
                        </li>

                        <li>
                            <span class="icon icon-hand"></span>
                            <span class="summary--text">Dla fundacji ${donation.institution.name}.</span>
                        </li>
                    </ul>
                </div>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru:</h4>
                        <ul>
                                                            <li>${donation.street}</li>
                                                            <li>${donation.city}</li>
                                                            <li>${donation.zipCode}</li>
                                                            <li>${donation.phoneNumber}</li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru:</h4>
                        <ul>
                                                            <li>${donation.pickUpDate}</li>
                                                            <li>${donation.pickUpTime}</li>
                                                            <li>${donation.pickUpComment}</li>
                            <li>Czy odebrany? <c:if test="${donation.isPicked}">Tak</c:if><c:if test="${!donation.isPicked}">Nie</c:if> </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
            <c:if test="${!donation.isPicked}">
                <div class="form-group form-group--inline active">
                    <label>
                        Czy kurier odebrał paczkę?
                        <select name="isPicked">
                            <option label="Tak" value="true"></option>
                            <option label="Nie" value="false"></option>
                        </select>
                    </label>
                </div>
                <button class="btn btn--small" type="submit">Potwierdź</button>
            </c:if>
        </form>
    </div>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
