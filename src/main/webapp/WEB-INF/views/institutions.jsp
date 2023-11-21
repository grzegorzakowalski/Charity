<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kowal
  Date: 21.11.2023
  Time: 18:40
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
<header>
<c:import url="nav.jsp"/>
</header>
<section class="help">
    <h2>Komu pomagamy?</h2>

    <!-- SLIDE 1 -->
    <div class="help--slides active" data-id="1">
        <p>W naszej bazie znajdziesz listę zweryfikowanych Fundacji, z którymi współpracujemy.
            Możesz sprawdzić czym się zajmują.</p>

        <ul class="help--slides-items">
            <c:forEach items="${pairList}" var="pair">
                <li>
                    <div class="col">
                        <div class="title">${pair.institutionPair.get(0).name}</div>
                        <div class="subtitle">${pair.institutionPair.get(0).description}</div>
                    </div>

                    <div class="col">
                        <div class="title">${pair.institutionPair.get(1).name}</div>
                        <div class="subtitle">${pair.institutionPair.get(1).description}</div>
                    </div>
                </li>
            </c:forEach>
<%--            <li>--%>
<%--                <div class="col">--%>
<%--                    <div class="title">${institutionList.get(2).name}</div>--%>
<%--                    <div class="subtitle">${institutionList.get(2).description}</div>--%>
<%--                </div>--%>
<%--                <div class="col">--%>
<%--                    <div class="title">${institutionList.get(3).name}</div>--%>
<%--                    <div class="subtitle">${institutionList.get(3).description}</div>--%>
<%--                </div>--%>

<%--            </li>--%>

        </ul>
    </div>
</section>
<c:import url="footer.jsp"/>
</body>
</html>
