<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css' id="bootstrap-css">
    <link rel="stylesheet" href="assets/css/header.css">
    <script src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>

    <title><fmt:message key = "header.home" /></title>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-brand-centered">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand navbar-brand-centered"><fmt:message key = "header_title" /></div>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-brand-centered">
            <ul class="nav navbar-nav">
                <li><a href="main.jsp"><fmt:message key = "header.home" /></a></li>
                <li><a href="controller?command=getseances"><fmt:message key = "header.seances" /></a></li>
                <li><a href="controller?command=getfilms"><fmt:message key = "header.films" /></a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty sessionScope.user}">
                    <li><a href="login.jsp"><fmt:message key = "main.signIn" /></a></li>
                    <li><a href="registration.jsp"><fmt:message key = "main.signUp" /></a></li>
                </c:if>
                <c:if test="${sessionScope.user.role == 'User' || sessionScope.user.role == 'Admin'}">
                    <li><a href="controller?command=logout"><fmt:message key = "main.logOut" /></a></li>
                </c:if>

                <li><a href="controller?command=changelanguage">${languageButtonLabel}</a></li>
                
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

</body>
</html>
</fmt:bundle>