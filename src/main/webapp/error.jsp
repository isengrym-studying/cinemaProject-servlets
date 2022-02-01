<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
    <link rel="stylesheet" href="assets/css/error.css">
    <title>Error Page</title>
</head>
<body>
<%--Request from ${pageContext.errorData.requestURI} is failed--%>
<%--<br/>--%>
<%--Servlet name or type: ${pageContext.errorData.servletName};--%>
<%--<br/>--%>
<%--Status code: ${pageContext.errorData.statusCode}--%>
<%--<br/>--%>
<%--Exception: ${pageContext.errorData.throwable}--%>

<div class="page-wrap d-flex flex-row align-items-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
<%--                <span class="display-1 d-block">404</span>--%>
                <div class="mb-4 lead">Oops! Something went wrong.</div>
                <a href="index.jsp" class="btn btn-link">Back to Home</a>
            </div>
        </div>
    </div>
</div>

</body>
</html>
