<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<!DOCTYPE html>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="assets/css/signUpSignIn.css">
    <title><fmt:message key = "signIn.title" /></title>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key = "signIn.label" /></h2>
        <p><fmt:message key = "signIn.description" /></p>
    </div>
</div>

<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">

            <form action="controller" method="post">

                <input type="hidden" name="command" value="Login">

                <div class="form-group">
                    <label><fmt:message key = "signUp.email" /></label>
                    <input name="email" type="email" required pattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" class="form-control" placeholder="johnmarston@gmail.com">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.password" /></label>
                    <input name="password" type="password" required pattern="^(?=.*?[0-9]).{8,}$" class="form-control" placeholder="********">
                </div>
                <button type="submit" class="btn btn-black"><fmt:message key = "signIn.action" /></button>

                <c:if test="${not empty errorLoginPassMessage}">
                    <h5><fmt:message key = "${errorLoginPassMessage}" /></h5>
                </c:if>

                <h5>${wrongAction}</h5>
                <h5>${nullPage}</h5>
            </form>

        </div>
    </div>
</div>

<br/>
</body>
</html>
</fmt:bundle>