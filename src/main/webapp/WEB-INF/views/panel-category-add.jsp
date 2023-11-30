<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 30.11.2023
  Time: 20:10
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
    <h2>Dodaj kategorię</h2>
    <form:form method="post" modelAttribute="category">
        <div class="form-group">
            <form:input path="name" placeholder="Nazwa"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Dodaj kategorię</button>
        </div>
    </form:form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
