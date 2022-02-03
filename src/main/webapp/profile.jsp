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
                <a href="controller?command=deleteAccount">
                    <button class="command-button" type="button" ><fmt:message key = "profile.deleteAcc" /></button>
                </a>
            </div>

            <div class="ticket-list col-sm-12 col-md-4 col-lg-3">
                <h3><fmt:message key = "profile.tickets" /></h3>
                    <%--            <c:forEach var="ticket" items="tickets">--%>
                    <%--                <div class="ticket-item">--%>
                    <%--                    <h6>${ticket.movie.title}</h6>--%>
                    <%--                    <h6>${ticket.movie.startTime}</h6>--%>
                    <%--                    <h6>Row: ${ticket.row}, place: ${ticket.place}</h6>--%>
                    <%--                    <h6>${ticket.price} hrn</h6>--%>
                    <%--                </div>--%>
                    <%--            </c:forEach>--%>
                <a href="controller?command=deleteAccount">
                    <button class="command-button" type="button"><fmt:message key = "profile.printTicket" /></button>
                </a>
            </div>

            <div class="col-sm-0 col-md-0 col-lg-2">

            </div>
        </div>

    </body>
</html>
</fmt:bundle>