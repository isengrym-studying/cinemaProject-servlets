<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
    <head>
        <link rel="stylesheet" href="assets/css/profile.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title><fmt:message key = "profile.title" /></title>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="page-body">
            <div class="col-sm-0 col-md-0 col-lg-2">

            </div>

            <div class="ticket-list col-sm-12 col-md-6 col-lg-4">
                <h3 class="headline"><fmt:message key = "profile.tickets" /></h3>
                                <c:forEach var="ticket" items="${ticketList}">
                                        <div class="ticket-item">
                                            <div class="left-side">
                                                <a href="/controller?command=ticketChoicePage&seanceId=${ticket.seance.id}">
                                                    <img class="ticket-image" src="${ticket.seance.movie.imagePath}" alt="">
                                                </a>
                                            </div>
                                            <div class="right-side">
                                                <h6 class="ticket-info ticket-title">${ticket.seance.movie.title}</h6>
                                                <h6 class="ticket-info"><span> <fmt:message key = "ticket.when"/>: </span>
                                                        ${ticket.seance.startDate.getDayOfMonth()} <fmt:message key = "${ticket.seance.startDate.getMonth()}"/>,
                                                    <fmt:message key = "${ticket.seance.startDate.getDayOfWeek()}"/>
                                                    <ctg:start-time seance="${ticket.seance}"/> </h6>
                                                <h6 class="ticket-info"><span> <fmt:message key = "ticket.row"/>: </span> ${ticket.rowNumber}</h6>
                                                <h6 class="ticket-info"><span> <fmt:message key = "ticket.seat"/>: </span> ${ticket.placeNumber}</h6>
                                                <h6 class="ticket-info"><span> <fmt:message key = "ticket.price"/>: </span> ${ticket.seance.ticketPrice}₴</h6>
                                            </div>
                                        </div>

                                </c:forEach>

                <div class="page-number-block">
                    <c:forEach begin="1" end="${ticketPagesQuantity}" varStatus="loop">
                        <a href="/controller?command=profile&ticketPage=${loop.index}&view=${view}"><p class="page-number-item">${loop.index}</p></a>
                    </c:forEach>

                </div>
            </div>


            <div class="col-sm-0 col-md-0 col-lg-2">

            </div>

            <div class = "personal-data col-sm-12 col-md-6 col-lg-4">
                <h3 class="headline"><fmt:message key = "profile.personalData" /></h3>
                <h4>
                    <fmt:message key = "signUp.name" /> : ${userName}
                    <a class="change" href="nameUpdate.jsp"> <fmt:message key = "profile.change" /></a>
                </h4>
                <h4>
                    <fmt:message key = "signUp.surname" /> : ${userSurname}
                    <a class="change" href="surnameUpdate.jsp"><fmt:message key = "profile.change" /></a>
                </h4>
                <h4>
                    <fmt:message key = "signUp.email" /> : ${userEmail}
                    <a class="change" href="emailUpdate.jsp"><fmt:message key = "profile.change" /></a>
                </h4>
                <h4>
                    <fmt:message key = "signUp.password" />
                    <a class="change" href="passwordUpdate.jsp"><fmt:message key = "profile.change" />
                    </a>
                </h4>
                <a onclick="return confirm('<fmt:message key = "button.confirmText"/>')" href="controller?command=deleteUser">
                    <button class="command-button" type="button" ><fmt:message key = "profile.deleteAcc" /></button>
                </a>
            </div>

            <div class="col-sm-0 col-md-0 col-lg-2">

            </div>
        </div>

    </body>
</html>
</fmt:bundle>