<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key = "signUp.title" /></title>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="assets/css/signUpSignIn.css">
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key = "signUp.label" /></h2>
        <p><fmt:message key = "signUp.description" /></p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="controller" method="post">

                <input type="hidden" name="command" value="signup">
                <input type="hidden" name="role" value="User">

                <div class="form-group">
                    <label><fmt:message key = "signUp.name" /></label>
                    <input name="name" type="text"  class="form-control" placeholder="John">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.surname" /></label>
                    <input name="surname" type="text" class="form-control" placeholder="Marston">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.email" /></label>
                    <input name="email" type="email" class="form-control" placeholder="johnmarston@gmail.com">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.password" /></label>
                    <input name="password" type="password" class="form-control" placeholder="********">
                </div>
                <button type="submit" class="btn btn-black"><fmt:message key = "signUp.action" /></button>

                <c:if test="${not empty nameIssue}" >
                    <h5><fmt:message key = "${nameIssue}" /></h5>
                </c:if>

                <c:if test="${not empty emailIssue}" >
                    <h5><fmt:message key = "${emailIssue}" /></h5>
                </c:if>

                <c:if test="${not empty passwordIssue}" >
                    <h5><fmt:message key = "${passwordIssue}" /></h5>
                </c:if>

                <c:if test="${not empty userExistsIssue}" >
                    <h5><fmt:message key = "${userExistsIssue}" /></h5>
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