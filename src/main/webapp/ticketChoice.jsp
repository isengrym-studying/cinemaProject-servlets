<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
    <head>
        <link rel="stylesheet" href="assets/css/ticketChoice.css">
        <title>${movie.title}</title>
    </head>


    <body>
    <jsp:include page="header.jsp" />

    <div class="col-sm-7 col-md-7 col-lg-6">
        <div class="hall-body">
            <h3 class="movie-title"><fmt:message key = "ticket.screen"/></h3>

            <c:forEach begin="1" end="7" varStatus="row">
            <div class = "row">
                <h5>${row.index}</h5>
                <c:forEach begin="1" end="11" varStatus="place">
                <c:if test="${not empty ticketList}">

                    <c:set var="alreadyExists" scope="request" value="false"></c:set>
                <c:forEach var="ticket" items="${ticketList}">
                        <c:if test="${ticket.rowNumber==row.index && ticket.placeNumber==place.index}">
                            <div class="seat-object seat-object-disabled" row-id="${row.index}" seat-id="${place.index}">
                                <p>${place.index}</p>
                            </div>
                            <c:set var="alreadyExists" scope="request" value="true"></c:set>
                        </c:if>
                </c:forEach>

                    <c:if test="${(ticket.rowNumber!=row.index || ticket.placeNumber!=place.index) && alreadyExists == false}">
                        <a href="controller?command=ticketPage&rowId=${row.index}&placeId=${place.index}&seanceId=${seance.id}">
                            <div class="seat-object seat-object-active" row-id="${row.index}" seat-id="${place.index}">
                                <p>${row.index}</p>
                            </div>
                            <c:set var="alreadyExists" scope="request" value="true"></c:set>
                        </a>
                    </c:if>
                </c:if>

                <c:if test="${empty ticketList}">
                    <a href="controller?command=ticketPage&rowId=${row.index}&placeId=${place.index}&seanceId=${seance.id}">
                        <div class="seat-object seat-object-active" row-id="${row.index}" seat-id="${place.index}">
                            <p>${place.index}</p>
                        </div>
                    </a>
                </c:if>
                </c:forEach>
            </div>
            </c:forEach>
            <h3 class="movie-title"><fmt:message key = "ticket.chooseClick"/></h3>
        </div>
    </div>

    <div class="col-sm-6 col-md-6 col-lg-6">
        <div class="film-body">
            <a href="/controller?command=getfullmovie&movieId=${movie.id}">
                <img src="${movie.imagePath}" alt="">
            </a>
            <h3 class="movie-title">${movie.title}</h3>
            <h4 class="movie-info"> <span><fmt:message key = "movieItem.startDate"/></span><br>
                    ${seance.startDate.getDayOfMonth()} <fmt:message key = "${seance.startDate.getMonth()}"/>,
                <fmt:message key = "${seance.startDate.getDayOfWeek()}"/>
            </h4>
            <h4 class="movie-info"><span><fmt:message key = "movieItem.startTime"/></span><br>
                <ctg:start-time seance="${seance}"/>
            </h4>
            <h4 class="movie-info"><span><fmt:message key = "movieItem.endTime"/></span><br>
                <ctg:end-time seance="${seance}"/>
            </h4>
            <h4 class="movie-info"><span><fmt:message key = "movieItem.price"/></span><br>
                    ${seance.ticketPrice}₴
            </h4>
        </div>
    </div>

    </body>
    </html>
</fmt:bundle>