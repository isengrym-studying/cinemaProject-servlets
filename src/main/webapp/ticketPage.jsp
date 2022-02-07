<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
<html>
<head>
    <link rel="stylesheet" href="assets/css/ticketPage.css">
    <script src="assets/js/scripts.js"></script>
    <title>Title</title>
</head>
<body>
<%--<jsp:include page="header.jsp" />--%>

<div class="cardWrap">
    <div class="card cardLeft">
        <h1><fmt:message key = "header.title"/></h1>
        <div class="title">
            <h2>${seance.movie.title}</h2>
            <span><fmt:message key = "ticket.movie"/></span>
        </div>
        <div class="name">
            <h2>${user.name} ${user.surname}</h2>
            <span><fmt:message key = "ticket.name"/> </span>
        </div>
        <div class="time">
            <h2>
                ${seance.startDate.getHour()}:${seance.startDate.getMinute()}<c:if test="${seance.startDate.getMinute() == 0}">0</c:if>
            </h2>
            <span><fmt:message key = "ticket.dateTime"/> </span>
        </div>

    </div>
    <div class="card cardRight">
        <div class="eye"></div>
        <div class="number">
            <h3>${rowId}</h3>
            <span>row</span>
            <h3>${placeId}</h3>
            <span>seat</span>
        </div>
    </div>

    <div class="col-lg-5">

    </div>

        <a href="controller?command=confirmTicket&action=confirm">
            <div class="action-button submit-button col-lg-1">
                <h4>Submit</h4>
            </div>
        </a>
        <a href="controller?command=confirmTicket&action=decline">
            <div class="action-button decline-button col-lg-1">
                <h4>Decline</h4>
            </div>
        </a>

    </div>

    <div class="col-lg-5">
    </div>

</body>
</html>
</fmt:bundle>