<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 01.12.2023
  Time: 20:52
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
    <h2>Wiadomość od ${contactMSG.name} ${contactMSG.surname}</h2>
    <form:form method="post" modelAttribute="contactMSG">
        <div class="form-group admin">
                    ${contactMSG.msg}<br><br>
            Zarchiwizować?   <form:checkbox path="archived"/>
        </div>
        <form:hidden path="id"/>
        <form:hidden path="name"/>
        <form:hidden path="surname"/>
        <form:hidden path="msg"/>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zapisz zmiany</button>
        </div>
    </form:form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
