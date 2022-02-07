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
<jsp:include page="header.jsp" />

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
                    <input name="name" type="text" required pattern="^[a-zA-Zа-яА-Яєїё']+$" class="form-control" placeholder="<fmt:message key = "field.name" />">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.surname" /></label>
                    <input name="surname" type="text" required pattern="^[a-zA-Zа-яА-Яєїё']+$" class="form-control" placeholder="<fmt:message key = "field.surname" />">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.email" /></label>
                    <input name="email" type="email" required pattern="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$" class="form-control" placeholder="<fmt:message key = "field.email" />">
                </div>
                <div class="form-group">
                    <label><fmt:message key = "signUp.password" /></label>
                    <input name="password" type="password" required pattern="^(?=.*?[0-9]).{8,}$" class="form-control" placeholder="<fmt:message key = "field.password" />">
                </div>
                <button type="submit" class="btn btn-black"><fmt:message key = "signUp.action" /></button>

                <c:if test="${not empty registrationError}" >
                    <h5><fmt:message key = "${registrationError}" /></h5>
                </c:if>

                <c:if test="${not empty userAlreadyExists}" >
                    <h5><fmt:message key = "${userAlreadyExists}" /></h5>
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