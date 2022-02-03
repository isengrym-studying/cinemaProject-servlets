<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css' id="bootstrap-css">
    <link rel="stylesheet" href="assets/css/mainpage.css">
    <script src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <title><fmt:message key = "main.title" /></title>
</head>
<body>
<jsp:include page="header.jsp" />


<%--        <c:forEach var="movie" items="${moviesList}">--%>
<%--            <li>--%>
<%--                <div class="image_title">--%>
<%--                    <a href="controller?command=getfullmovie&movieId=${movie.id}">${movie.title}</a>--%>
<%--                </div>--%>
<%--                <a href="controller?command=getfullmovie&movieId=${movie.id}">--%>
<%--                    <img src="${movie.imagePath}"/>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </c:forEach>--%>



</body>
</html>
</fmt:bundle>