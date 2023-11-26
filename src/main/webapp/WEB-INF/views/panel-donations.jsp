<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 26.11.2023
  Time: 15:55
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
<c:if test="${sortedDonations.size() > 0}">
  <section class="login-page">
    <div class="steps">
      <h2>Lista twoich dotacji:</h2>
      <c:forEach items="${sortedDonations}" var="donation" varStatus="i">
        <div class="form-group form-group--inline">
          <label>
              <span class="admin">${i.count}. Ilość worków: ${donation.quantity}, Kategorie: <c:forEach items="${donation.categories}" var="category">${category.name},&nbsp;</c:forEach>
            Czy odebrane?<c:if test="${donation.isPicked}">tak</c:if><c:if test="${!donation.isPicked}">nie</c:if>, Kiedy <c:if test="${!donation.isPicked}">ma być</c:if> odebrane:
                  ${donation.pickUpDate} ${donation.pickUpTime}.</span>
            <a href="<c:url value="/panel/donation/details?id=${donation.id}"/>"><button type="button" class="btn btn--small">Szczegóły</button> </a>
          </label>
        </div>
      </c:forEach>
    </div>
  </section>
</c:if>
<c:if test="${sortedDonations.size() == 0}">
<section class="login-page">
  <div class="steps">
    <h2>Niestety nie masz jeszcze żadnych dotacji, ale zawsze możesz to zmienić!</h2>
    <a href="<c:url value="/form"/>" class="btn btn--large">Przekaż dary</a>
  </div>
</section>
</c:if>

<c:import url="footer.jsp"/>
</body>
</html>
