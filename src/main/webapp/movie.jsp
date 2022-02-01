<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel="stylesheet" href="assets/css/movie.css">
            <title>${movie.title}</title>
        </head>


        <body>
            <jsp:include page="header.jsp" />
            <div class="film-body">

                <div class="left-half">
                    <div class="col-sm-12 col-md-6 col-lg-5">
                        <img src="${movie.imagePath}" alt="">
                    </div>

                </div>
                <div class="right-half">
                    <div class="col-sm-12 col-md-4 col-lg-3">
                        <h3 class="movie-title">${movie.title}</h3>
                        <h4><span><fmt:message key = "movieItem.year" /></span> <br>${movie.productionYear}</h4>
                        <h4><span><fmt:message key = "movieItem.director" /></span> <br>${movie.director}</h4>
                        <h4><span><fmt:message key = "movieItem.genre" /></span> <br>${movie.genre}</h4>
                        <h4><span><fmt:message key = "movieItem.age" /></span> <br>${movie.ageRestriction}+</h4>
                        <h4><span><fmt:message key = "movieItem.duration" /></span> <br>${durationMin} <fmt:message key = "movieItem.minutes" /></h4>
                    </div>

                </div>
                <div class="bottom-block">
                    <div class="col-sm-12 col-md-12 col-lg-4">
                        <c:if test="${not empty seancesMap}"><h2><fmt:message key = "movie.seances" /></h2></c:if>


                        <c:forEach var="date" items="${seancesMap}">
                            <div class="day-item">
                                <h5>${date.key.getDayOfMonth()} <fmt:message key = "${date.key.getMonth()}"/>,
                                    <fmt:message key = "${date.key.getDayOfWeek()}"/></h5>
                                <c:forEach var="seance" items="${date.value}">
                                    <button type="button">${seance.startDate.getHour()}:${seance.startDate.getMinute()}<c:if test="${seance.startDate.getMinute() == 0}">0</c:if></button>
                                </c:forEach>
                            </div>
                        </c:forEach>


                    </div>

                </div>

            </div>
        </body>
    </html>
</fmt:bundle>