<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel="stylesheet" href="assets/css/movie.css">
            <title>${movie.title}</title>
        </head>


        <body>
            <jsp:include page="header.jsp" />
            <div class="col-sm-0 col-md-0 col-lg-1">

            </div>

            <div class="col-sm-12 col-md-12 col-lg-11">
                <div class="film-body">

                    <div class="left-half">
                        <div class="col-sm-12 col-md-6 col-lg-5">
                            <h3 class="movie-title">${movie.title}</h3>
                            <img src="${movie.imagePath}" alt="">
                        </div>
                    </div>

                    <div class="bottom-block">

                        <div class="col-sm-12 col-md-12 col-lg-5">
                            <c:if test="${not empty seancesMap}"><h2 class="seances-headline"><fmt:message key = "movie.seances" /></h2></c:if>
                            <c:if test="${empty seancesMap}"><h2 class="seances-headline"><fmt:message key = "movie.noSeances" /></h2></c:if>
                            <c:forEach var="date" items="${seancesMap}">

                                <div class="day-item">
                                    <h5>${date.key.getDayOfMonth()} <fmt:message key = "${date.key.getMonth()}"/>,
                                        <fmt:message key = "${date.key.getDayOfWeek()}"/></h5>

                                    <c:forEach var="seance" items="${date.value}">
                                        <a href="controller?command=ticketChoicePage&seanceId=${seance.id}"type="button">
                                            <button>
                                                <ctg:start-time seance="${seance}"/>
                                            </button>

                                        </a>
                                    </c:forEach>
                                </div>

                            </c:forEach>

                        </div>
                    </div>

                    <div class="right-half">

                        <div class="col-sm-12 col-md-4 col-lg-12">
                            <h3 class = "about-line"><fmt:message key = "movieItem.about" /></h3>
                            <h4><span><fmt:message key = "movieItem.year" /></span> <br>${movie.productionYear}</h4>
                            <h4><span><fmt:message key = "movieItem.director" /></span> <br>${movie.director}</h4>
                            <h4><span><fmt:message key = "movieItem.genre" /></span> <br>${movie.genre}</h4>
                            <h4><span><fmt:message key = "movieItem.age" /></span> <br>${movie.ageRestriction}+</h4>
                            <h4><span><fmt:message key = "movieItem.duration" /></span> <br>${durationMin} <fmt:message key = "movieItem.minutes" /></h4>

                            <c:if test="${user.role == 'Admin'}">
                                <a onclick="return confirm('<fmt:message key = "button.confirmText"/>')" href="/controller?command=deleteMovie&movieId=${movie.id}">
                                    <button>
                                        <h4><fmt:message key = "admin.deleteMovie" /></h4>
                                    </button>
                                </a>
                            </c:if>
                        </div>

                    </div>

                </div>
            </div>

        </body>
    </html>
</fmt:bundle>