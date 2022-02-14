
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="assets/css/movies.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <title><fmt:message key = "admin.addSeance" /></title>
</head>
<body>

<jsp:include page="header.jsp" />
<div class="row col-lg-12">

    <div class="col-lg-2">

    </div>
    <div class="col-lg-8">

        <h2 class="headline"><fmt:message key = "admin.addSeance" /></h2>

        <form action="controller" method="post">

            <input type="hidden" name="command" value="addNewSeance">

            <div class="form-group">

                <label><fmt:message key = "movieItem.name" /></label>
                <select name="movies" class="form-control">
                    <c:if test="${not empty movies}">
                        <c:forEach var="movie" items="${movies}">
                            <option name = "movieOption" value="${movie.id}">${movie.title}</option>
                        </c:forEach>
                    </c:if>
                </select>

            </div>
            <div class="form-group">
                <label><fmt:message key = "movieItem.startDate" /></label>
                <input name="dateTime" type="datetime-local" required class="form-control">
            </div>

            <div class="form-group">
                <label><fmt:message key = "movieItem.price" /></label>
                <input name="price" type="number" required class="form-control">
            </div>

            <button type="submit" class="btn btn-black"><fmt:message key = "admin.addSeance" /></button>

            <h5>${wrongAction}</h5>
            <h5>${nullPage}</h5>
        </form>


    </div>

    <div class="col-lg-2">

    </div>
</div>




</body>
</html>
</fmt:bundle>