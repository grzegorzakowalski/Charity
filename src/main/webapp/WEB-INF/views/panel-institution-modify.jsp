<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 25.11.2023
  Time: 19:11
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
    <h2>Zmodyfikuj instytucję</h2>
    <form:form method="post" modelAttribute="institution">
      <div class="form-group">
        Nazwa:<br>
        <form:input path="name"/>
      </div>
      <div class="form-group">
        Opis:<br>
        <form:textarea path="description" />
      </div>
      <form:hidden path="id"/>
      <div class="form-group form-group--buttons">
        <button class="btn" type="submit">Zmodyfikuj instytucję</button>
      </div>
    </form:form>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
