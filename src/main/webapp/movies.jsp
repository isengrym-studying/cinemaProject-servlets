<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel="stylesheet" href="assets/css/movies.css">
            <title><fmt:message key = "movies.title" /></title>
        </head>
        <body>
            <jsp:include page="header.jsp" />



            <div class="film-items">
                <div class="film-page-title">
                    <h2><fmt:message key = "movies.headline" /></h2>
                </div>


                <c:forEach var="seance" items="${seances}">
                    <a href="controller?command=getfullmovie&movieId=${seance.movie.id}">
                    <div class = "film_item col-md-4 col-lg-2">
                        <div class="poster">
                            <img src="${seance.movie.imagePath}" alt="" width="218" height="322">
                        </div>
                        <div class="film_item_body">
                            <h5>${seance.movie.title}</h5>
                        </div>
                    </div>
                    </a>
                </c:forEach>


                <div class="page-number-block col-lg-12">
                    <% for(int i = 1; i < (Integer)request.getAttribute("moviePagesQuantity")+1; i+=1) { %>
                    <c:set var="number" scope="request" value="<%= i %>"></c:set>
                    <a href="/controller?command=getmovies&moviePage=${number}"><p class="page-number-item">${number}</p></a>
                    <%}%>
                </div>

            </div>

        </body>
    </html>
</fmt:bundle>