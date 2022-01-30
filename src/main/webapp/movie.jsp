<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel="stylesheet" href="assets/css/style.css">
            <title><fmt:message key = "${movie.title}" /></title>
        </head>


        <body>
            <jsp:include page="header.jsp" />
            <div class="film-body">
                <div class="left-half">
                    <div class="col-lg-2">

                    </div>
                    <div class="col-lg-4">
                        <img src="${movie.imagePath}" alt="">

                    </div>

                </div>
                <div class="right-half">
                    <div class="col-lg-6">
                        <h3>${movie.title}</h3>
                        <h3><span><fmt:message key = "movieItem.year" />:</span> ${movie.productionYear}</h3>
                        <h3><span><fmt:message key = "movieItem.director" />:</span> ${movie.director}</h3>
                        <h3><span><fmt:message key = "movieItem.genre" />:</span> ${movie.genre}</h3>
                        <h3><span><fmt:message key = "movieItem.age" />:</span> ${movie.ageRestriction}+</h3>
                        <h3><span><fmt:message key = "movieItem.duration" />:</span> ${durationMin} <fmt:message key = "movieItem.minutes" /></h3>
                    </div>
                </div>
            </div>
        </body>
    </html>
</fmt:bundle>