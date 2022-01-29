<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="en"/>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
<head>
    <title><fmt:message key = "main.title" /></title>
</head>
<body>
<jsp:include page="header.jsp" />

</body>
</html>
</fmt:bundle>