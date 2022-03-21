<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty cookie['lang']}">
    <fmt:setLocale value="${cookie['lang'].getValue()}"/>
</c:if>
<c:if test="${empty cookie['lang']}">
    <fmt:setLocale value="ru"/>
</c:if>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
            <link rel="stylesheet" href="assets/css/movies.css">
            <link rel="stylesheet" href="assets/css/style.css">
            <title><fmt:message key = "movies.title" /></title>
        </head>
        <body>
            <jsp:include page="header.jsp" />

            <div class="row col-lg-12">
                <div class="col-lg-2">

                </div>
                <div class="film-page-title col-lg-8">
                    <h2 class="headline"><fmt:message key = "movies.headline" /></h2>
                        <div class="form">
                            <c:if test="${empty number}"><a href="/controller?command=getmovies&moviePage=1&view=all"></c:if>
                            <c:if test="${not empty number}"><a href="/controller?command=getmovies&moviePage=${number}&view=all"></c:if>
                                <button><fmt:message key = "movies.all" /></button>
                            </a>
                            <c:if test="${empty number}"><a href="/controller?command=getmovies&moviePage=1&view=futureOnly"></c:if>
                            <c:if test="${not empty number}"><a href="/controller?command=getmovies&moviePage=${number}&view=futureOnly"></c:if>
                                <button><fmt:message key = "movies.onlyWithSeances" /></button>
                            </a>
                        </div>

                </div>
            </div>


            <div class="col-xs-0 col-sm-0 col-md-1 col-lg-2">

            </div>

                <div class="film-items col-xs-12 col-sm-12 col-md-10 col-lg-8">

                    <c:if test="${sessionScope.user.role == 'Admin'}">
                        <a href="/controller?command=newMoviePage">
                            <div class = "film_item col-xs-12 col-sm-6 col-md-4 col-lg-3">
                                <div class="poster">
                                    <img src="/images/dummy.jpg" alt="" width="218" height="322">
                                </div>
                                <div class="film_item_body">
                                    <h5><fmt:message key = "admin.addMovie" /></h5>
                                </div>
                            </div>
                        </a>
                    </c:if>

                    <c:forEach var="movie" items="${movies}">
                        <a href="controller?command=getfullmovie&movieId=${movie.id}">
                        <div class = "film_item col-xs-12 col-sm-6 col-md-4 col-lg-3">
                            <div class="poster">
                                <img src="${movie.imagePath}" alt="" width="218" height="322">
                            </div>
                            <div class="film_item_body">
                                <h5>${movie.title}</h5>
                            </div>
                        </div>
                        </a>
                    </c:forEach>


                    <div class="page-number-block col-xs-12 col-sm-12 col-md-12 col-lg-12">
                        <c:forEach begin="1" end="${moviePagesQuantity}" varStatus="loop">
                            <a href="/controller?command=getmovies&moviePage=${loop.index}&view=${view}"><p class="page-number-item">${loop.index}</p></a>
                        </c:forEach>

                    </div>
                </div>

            <div class="col-xs-0 col-sm-0 col-md-1 col-lg-2">

            </div>

        </body>
    </html>
</fmt:bundle>