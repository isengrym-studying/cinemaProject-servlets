<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel="stylesheet" href="assets/css/movies.css">
            <title><fmt:message key = "${pageTitleProperty}" /></title>
        </head>
        <body>
            <jsp:include page="header.jsp" />



            <div class="film-items">
                <div class="film-page-title">
                    <h2><fmt:message key = "${pageHeadlineProperty}" /></h2>
                </div>


                <c:forEach var="movie" items="${movies}">
                    <a href="controller?command=getfullmovie&movieId=${movie.id}">
                    <div class = "film_item col-md-4 col-lg-2">
                        <div class="poster">
                            <img src="${movie.imagePath}" alt="" width="218" height="322">
                        </div>
                        <div class="film_item_body">
                            <h5>${movie.title}</h5>
                        </div>
                    </div>
                    </a>
                </c:forEach>
            </div>


        </body>
    </html>
</fmt:bundle>