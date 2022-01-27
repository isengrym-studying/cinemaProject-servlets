<%@ page import="com.example.cinema.domain.entity.Administrator" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>
    <%Administrator administrator = new Administrator();%>
    <%= administrator.getAllUsers() %>
</h1>
<br/>
<a href="hello-servlet">Get users</a>
<a href="registration.jsp">Registration</a>
</body>
</html>