<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

            <% for(int i = 1; i < 8; i+=1) { %>
            <div class = "row">
                <h5><%= i %></h5>
                <% for(int j = 1; j < 12; j+=1) { %>
                <c:if test="${not empty ticketList}">

                    <c:set var="row" scope="request" value="<%= i %>"></c:set>
                    <c:set var="place" scope="request" value="<%= j %>"></c:set>
                    <c:set var="alreadyExists" scope="request" value="false"></c:set>

                <c:forEach var="ticket" items="${ticketList}">
                        <c:if test="${ticket.rowNumber==row && ticket.placeNumber==place}">
                            <div class="seat-object seat-object-disabled" row-id="${row}" seat-id="${place}">
                                <p><%=j%></p>
                            </div>
                            <c:set var="alreadyExists" scope="request" value="true"></c:set>
                        </c:if>
                </c:forEach>

                    <c:if test="${(ticket.rowNumber!=row || ticket.placeNumber!=place) && alreadyExists == false}">
                        <a href="controller?command=ticketPage&rowId=${row}&placeId=${place}&seanceId=${seance.id}">
                            <div class="seat-object seat-object-active" row-id="${row}" seat-id="${place}">
                                <p><%=j%></p>
                            </div>
                            <c:set var="alreadyExists" scope="request" value="true"></c:set>
                        </a>
                    </c:if>
                </c:if>

                <c:if test="${empty ticketList}">
                    <a href="controller?command=ticketPage&rowId=<%= i %>&placeId=<%= j %>&seanceId=${seance.id}">
                        <div class="seat-object seat-object-active" row-id="<%= i %>" seat-id="<%= j %>">
                            <p><%=j%></p>
                        </div>
                    </a>
                </c:if>
                <% } %>
            </div>
            <% } %>
            <h3 class="movie-title"><fmt:message key = "ticket.chooseClick"/></h3>
        </div>
    </div>

    <div class="col-sm-6 col-md-6 col-lg-6">
        <div class="film-body">
            <img src="${movie.imagePath}" alt="">
            <h3 class="movie-title">${movie.title}</h3>
            <h4 class="movie-info"> <span><fmt:message key = "movieItem.startDate"/></span><br>
                    ${seance.startDate.getDayOfMonth()} <fmt:message key = "${seance.startDate.getMonth()}"/>,
                <fmt:message key = "${seance.startDate.getDayOfWeek()}"/>
            </h4>
            <h4 class="movie-info"><span><fmt:message key = "movieItem.startTime"/></span><br>
                    ${seance.startDate.getHour()}:${seance.startDate.getMinute()}<c:if test="${seance.startDate.getMinute() == 0}">0</c:if>
            </h4>
            <h4 class="movie-info"><span><fmt:message key = "movieItem.endTime"/></span><br>
                    ${seance.endDate.getHour()}:${seance.endDate.getMinute()}<c:if test="${seance.endDate.getMinute() == 0}">0</c:if>
            </h4>
        </div>
    </div>

    </body>
    </html>
</fmt:bundle>