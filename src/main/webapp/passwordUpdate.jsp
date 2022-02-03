<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <!DOCTYPE html>
    <html>
    <head>
        <link rel="stylesheet" href="assets/css/profile.css">
        <title><fmt:message key = "profile.title" /></title>
        <title>Title</title>
    </head>
    <body>
    <jsp:include page="header.jsp" />

    <div class="col-lg-4">

    </div>
    <div class="col-lg-4">
        <form action="controller" method="post">
            <input type="hidden" name="command" value="updateUserPassword">
            <div class="form-group">
                <label><fmt:message key = "signUp.password" /></label>
                <input name="password" type="password" required pattern="^(?=.*?[0-9]).{8,}$" placeholder="********" class="form-control"/>
            </div>

            <div class="form-group">
                <label><fmt:message key = "edit.passwordConfirmation" /></label>
                <input name="password" type="password" required pattern="^(?=.*?[0-9]).{8,}$" class="form-control" placeholder="********">
            </div>

            <button type="submit" class="btn btn-black"><fmt:message key = "edit.save" /></button>

            <c:if test="${not empty errorPasswordMessage}">
                <h5><fmt:message key = "${errorPasswordMessage}" /></h5>
            </c:if>
            <c:if test="${not empty errorOldPasswordMessage}">
                <h5><fmt:message key = "${errorOldPasswordMessage}" /></h5>
            </c:if>
            <h5>${wrongAction}</h5>
            <h5>${nullPage}</h5>
        </form>
    </div>
    <div class="col-lg-4">

    </div>

    </body>
    </html>
</fmt:bundle>