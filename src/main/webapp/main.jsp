<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty cookie['lang']}">
    <fmt:setLocale value="${cookie['lang'].getValue()}"/>
</c:if>
<c:if test="${empty cookie['lang']}">
    <fmt:setLocale value="ru"/>
</c:if>
<fmt:bundle basename="language">

<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css' id="bootstrap-css">
    <link rel="stylesheet" href="assets/css/mainpage.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <script src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <title><fmt:message key = "main.title" /></title>
</head>
<body>
<jsp:include page="header.jsp" />


<div class="col-lg-2">

</div>

<div class="col-lg-8">
    <div class="welcome-block col-lg-12">

        <div class="col-lg-12">
            <h3 class="headline"><fmt:message key = "main.welcome" /></h3>
            <div class="info">
                <h4><fmt:message key = "main.info.hall" /></h4>
                <h4><fmt:message key = "main.info.seances" /></h4>
                <h4><fmt:message key = "main.info.location" /></h4>
            </div>
        </div>
    </div>

    <div class="col-lg-12">
        <div class="map">
            <h3 class="headline"><fmt:message key = "main.location" /></h3>
            <iframe class="col-lg-12" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d924.6607713392696!2d30.533993712897498!3d50.443296761019965!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4cfab7596cae9%3A0xd3c25d8b062ea0!2z0YPQuy4g0JvQuNC_0YHQutCw0Y8sIDE1LzE3LCDQmtC40LXQsiwgMDIwMDA!5e0!3m2!1sru!2sua!4v1644432633374!5m2!1sru!2sua"
                     height="450px" style="border:0;" allowfullscreen="" ></iframe>
        </div>
    </div>
</div>

<div class="col-lg-2">

</div>



</body>
</html>
</fmt:bundle>