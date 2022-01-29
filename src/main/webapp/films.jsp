<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
<html>
<head>
    <link rel="stylesheet" href="assets/css/style.css">
    <title><fmt:message key = "films.title" /></title>
</head>
<body>
<jsp:include page="header.jsp" />



<div class="film-items">
    <div class="film-page-title">
        <h2><fmt:message key = "films.headline" /></h2>
    </div>

    <c:forEach var="film" items="${films}">
        <a href="#">
        <div class = "film_item col-md-4 col-lg-2">
            <div class="poster">
                <img src="${film.imagePath}" alt="" width="218" height="322">
            </div>
            <div class="film_item_body">
                <h5>${film.title}</h5>
            </div>
        </div>
        </a>
    </c:forEach>
</div>


</body>
</html>
</fmt:bundle>