<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
    <head>
        <link rel="stylesheet" href="assets/css/seances.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title> <fmt:message key = "seances.title"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp" />


    <div class="col-lg-2">

    </div>

    <div class="col-lg-8">

        <div class="seance-page-title">
            <h2 class="headline"><fmt:message key = "seances.headline" /></h2>
        </div>

        <c:if test="${sessionScope.user.role == 'Admin'}">
            <a href="/controller?command=newSeancePage"type="button">
                <div class="seance-item row">
                    <div class="left-side col-sm-2 col-md-3 col-lg-3">
                        <img src="images/dummy.jpg" alt="" width="150" height="227">
                    </div>
                    <div class="right-side add-seance col-sm-3 col-md-6 col-lg-8">
                        <h3 class=""><fmt:message key = "admin.addSeance" /></h3>

                    </div>

                </div>
            </a>
        </c:if>

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
                            <button class="action-button">
                                <fmt:message key = "ticket.buy"/>
                            </button>
                        </a>
                        <c:if test="${user.role == 'Admin'}">
                            <a href="/controller?command=deleteSeance&seanceId=${seance.id}">
                                <button class="action-button">
                                    <fmt:message key = "admin.deleteSeance"/>
                                </button>
                            </a>
                        </c:if>
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