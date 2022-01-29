<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${request.getParameter('language')}"/>
<fmt:bundle basename="language">


<!DOCTYPE html>
<html>
<head>

</head>
<body>
<jsp:forward page="controller?command=setlanguage"/>


</body>
</html>
</fmt:bundle>