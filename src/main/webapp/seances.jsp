<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
    <head>
        <link rel="stylesheet" href="assets/css/seances.css">
        <title> <fmt:message key = "seances.title"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp" />


    <div class="col-lg-2">

    </div>

    <div class="col-lg-8">

        <div class="seance-page-title">
            <h2><fmt:message key = "seances.headline" /></h2>
        </div>

        <c:forEach var="seance" items="${seances}">
            <a href="/controller?command=getfullmovie&movieId=${seance.movie.id}"type="button">
                <div class="seance-item row">

                    <div class="left-side col-lg-3">
                        <img src="${seance.movie.imagePath}" alt="" width="150" height="227">
                    </div>
                    <div class="right-side col-lg-8">
                        <h3>${seance.movie.title}</h3>
                        <h5><span><fmt:message key = "movieItem.startDate"/></span><br>
                                ${seance.startDate.getDayOfMonth()} <fmt:message key = "${seance.startDate.getMonth()}"/>,
                            <fmt:message key = "${seance.startDate.getDayOfWeek()}"/>, ${seance.startDate.getHour()}:${seance.startDate.getMinute()}<c:if test="${seance.startDate.getMinute() == 0}">0</c:if>
                        </h5>
                        <h5><span><fmt:message key = "movieItem.endTime"/></span><br>
                                ${seance.endDate.getHour()}:${seance.endDate.getMinute()}<c:if test="${seance.endDate.getMinute() == 0}">0</c:if>
                        </h5>
                        <a href="/controller?command=ticketChoicePage&seanceId=${seance.id}">
                            <button class="buy-ticket">
                                <fmt:message key = "ticket.buy"/>
                            </button>
                        </a>
                    </div>

                </div>
            </a>

        </c:forEach>

    </div>

    <div class="col-lg-2">

    </div>


    <div class="page-number-block col-lg-12">
        <% for(int i = 1; i < (Integer)request.getAttribute("seancePagesQuantity")+1; i+=1) { %>
            <c:set var="number" scope="request" value="<%= i %>"></c:set>
            <a href="/controller?command=getseances&seancePage=${number}"><p class="page-number-item">${number}</p></a>
        <%}%>
    </div>




    </body>
</html>
</fmt:bundle>