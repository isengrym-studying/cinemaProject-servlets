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

    <title><fmt:message key = "admin.addMovie" /></title>
</head>
<body>
    <jsp:include page="header.jsp" />
    <div class="row col-lg-12">

        <div class="col-lg-2">

        </div>
        <div class="col-lg-8">

            <h2 class="headline"><fmt:message key = "admin.addMovie" /></h2>

            <form action="controller" method="post">

                <input type="hidden" name="command" value="addNewMovie">

                <div class="form-group">
                    <label><fmt:message key = "movieItem.name" /></label>
                    <input name="title" type="text" required pattern="^[a-zA-Zа-яА-Яєїё0-9:- ']+$" class="form-control">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "movieItem.director" /></label>
                    <input name="director" type="text" required pattern="^[a-zA-Zа-яА-Яєїё0-9 ']+$" class="form-control">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "movieItem.year" /></label>
                    <input name="year" type="number" min="1900" max="2050" value="2022" step="1" class="form-control">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "movieItem.genre" /></label>
                    <select name="genres" class="form-control">
                        <c:if test="${not empty genres}">
                            <c:forEach var="genre" items="${genres}">
                                <option name = "genreOption" value="${genre.id}">${genre.name}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                </div>
                <div class="form-group">
                    <label><fmt:message key = "movieItem.age" /></label>
                    <input name="age" type="number" min="0" max="21" step="1" required class="form-control">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "movieItem.duration" />, <fmt:message key = "movieItem.minutes" /></label>
                    <input name="duration" type="number" min="1" max="400"  step="1" required class="form-control">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "movieItem.image" /></label>
                    <input name="image" type="file" required class="form-control">
                </div>

                <button type="submit" class="btn btn-black"><fmt:message key = "admin.addMovie" /></button>

                <c:if test="${not empty registrationError}" >
                    <h5><fmt:message key = "${registrationError}" /></h5>
                </c:if>

                <c:if test="${not empty userAlreadyExists}" >
                    <h5><fmt:message key = "${userAlreadyExists}" /></h5>
                </c:if>

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