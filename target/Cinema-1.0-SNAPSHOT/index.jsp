<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">


<!DOCTYPE html>
<html>
<head>
    <title>${role} page</title>

</head>
<body>
<%--<jsp:include page="header.jsp" />--%>
<jsp:forward page="main.jsp"/>

</body>
</html>
</fmt:bundle>