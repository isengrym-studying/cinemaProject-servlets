<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="assets/css/header.css">
    <link rel="stylesheet" href="assets/js/scripts.js">

    <title><fmt:message key = "header.home" /></title>
</head>
<body>

<nav class="navbar navbar-default" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
<%--            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-brand-centered">--%>
<%--                <span class="sr-only">Toggle navigation</span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--                <span class="icon-bar"></span>--%>
<%--            </button>--%>
            <div class="navbar-brand navbar-brand-centered"><fmt:message key = "header.title" /></div>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
<%--        class="collapse navbar-collapse--%>
        <div  id="navbar-brand-centered">
            <ul class="nav navbar-nav">
                <li><a href="controller?command=fillmainpage"><fmt:message key = "header.home" /></a></li>
                <li><a href="controller?command=getmovies"><fmt:message key = "header.movies" /></a></li>
                <li><a href="controller?command=getseances"><fmt:message key = "header.seances" /></a></li>

            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${empty sessionScope.user}">
                    <li><a href="login.jsp"><fmt:message key = "header.signIn" /></a></li>
                    <li><a href="registration.jsp"><fmt:message key = "header.signUp" /></a></li>
                </c:if>
                <c:if test="${sessionScope.user.role == 'User' || sessionScope.user.role == 'Admin'}">
                    <li><a href="controller?command=logout"><fmt:message key = "header.logOut" /></a></li>
                    <li><a href="controller?command=profile"><fmt:message key = "header.profile" /></a></li>
                </c:if>

<%--                <li><a href="controller?command=changelanguage">${languageButtonLabel}</a></li>--%>

                <c:choose>
                    <c:when test="${cookie['lang'].getValue() == 'ru'}">
                        <li><a href="javascript:changeLanguage('en')">EN</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:changeLanguage('ru')">RU</a></li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

</body>
</html>
</fmt:bundle>

<script>
    function changeLanguage(language) {
        document.cookie = "lang=" + language + "; page=/; max-age=" + (10*365*24*60*60);
        location.reload();
    }
</script>