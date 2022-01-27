<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<!DOCTYPE html>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="assets/css/style.css">
    <title>Sign in</title>
</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2><fmt:message key = "signIn_label" /></h2>
        <p><fmt:message key = "signIn_description" /></p>
    </div>
</div>

<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="login" method="post">
                <div class="form-group">
                    <label>Email</label>
                    <input name="email" type="email" class="form-control" placeholder="johnmarston@gmail.com">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input name="password" type="password" class="form-control" placeholder="********">
                </div>
                <button type="submit" class="btn btn-black">Login</button>
                <h2>${loginStatus}</h2>
            </form>
        </div>
    </div>
</div>

<br/>
</body>
</html>
</fmt:bundle>