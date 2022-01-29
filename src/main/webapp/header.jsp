<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css' id="bootstrap-css">
    <link rel="stylesheet" href="assets/css/style.css">
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
                <li><a href="seances.jsp"><fmt:message key = "header.seances" /></a></li>
                <li><a href="controller?command=getfilms"><fmt:message key = "header.films" /></a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${sessionScope.userIsAuthorized != true}">
                    <li><a href="login.jsp"><fmt:message key = "main.signIn" /></a></li>
                    <li><a href="registration.jsp"><fmt:message key = "main.signUp" /></a></li>
                </c:if>
                <c:if test="${sessionScope.userIsAuthorized == true}">
                    <li><a href="controller?command=logout"><fmt:message key = "main.logOut" /></a></li>
                </c:if>


<%--                <li class="dropdown">--%>
<%--                            <form action="changeLanguage" method="POST">--%>
<%--                                <select name="languageList">--%>
<%--                                    <option value="ru">Русский</option>--%>
<%--                                    <option value="en">English</option>--%>
<%--                                </select>--%>
<%--                                <button type="submit" value="button">ОК</button>--%>
<%--                            </form>--%>
<%--                </li>--%>
                <li><a href="controller?command=changelanguage">${languageButtonLabel}</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

</body>
</html>
</fmt:bundle>