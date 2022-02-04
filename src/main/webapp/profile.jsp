<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
    <head>
        <link rel="stylesheet" href="assets/css/profile.css">
        <title><fmt:message key = "profile.title" /></title>
    </head>
    <body>
        <jsp:include page="header.jsp" />

        <div class="page-body">
            <div class="col-sm-0 col-md-0 col-lg-2">

            </div>
            <div class = "personal-data col-sm-12 col-md-7 col-lg-6">
                <h3><fmt:message key = "profile.personalData" /></h3>
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
                <a href="controller?command=deleteUser">
                    <button class="command-button" type="button" ><fmt:message key = "profile.deleteAcc" /></button>
                </a>
            </div>

            <div class="ticket-list col-sm-12 col-md-12 col-lg-3">
                <h3><fmt:message key = "profile.tickets" /></h3>
                                <c:forEach var="ticket" items="${ticketList}">
                                    <div class="ticket-item col-lg-12">
                                        <h6 class="ticket-data"><span> Seance id </span> <br> ${ticket.seanceId}</h6>
                                        <h6 class="ticket-data"><span> Row number </span> <br>${ticket.rowNumber}</h6>
                                        <h6 class="ticket-data"><span> Seat number </span> <br>${ticket.placeNumber}</h6>
                                    </div>
                                </c:forEach>

                <div class="page-number-block">
                    <% for(int i = 1; i < (Integer)request.getAttribute("ticketPagesQuantity")+1; i+=1) { %>
                        <c:set var="number" scope="request" value="<%= i %>"></c:set>
                        <a href="/controller?command=profile&ticketPage=${number}"><p class="page-number-item">${number}</p></a>
                    <%}%>
                </div>
            </div>

            <div class="col-sm-0 col-md-0 col-lg-2">

            </div>
        </div>

    </body>
</html>
</fmt:bundle>