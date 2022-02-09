<%@ attribute name="seance" required="true" type="com.example.cinema.model.entity.Seance" description="Seance-object" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>${seance.startDate.getHour()}:${seance.startDate.getMinute()}<c:if test="${seance.startDate.getMinute() == 0}">0</c:if></p>