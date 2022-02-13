<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">
    <html>
        <head>
            <link rel="stylesheet" href="assets/css/movie.css">
            <link rel="stylesheet" href="assets/css/style.css">
            <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
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
                                <a onclick="return confirm('<fmt:message key = "button.confirmText"/>')" href="controller?command=deleteMovie&movieId=${movie.id}">
                                    <button>
                                        <fmt:message key = "admin.deleteMovie" />
                                    </button>
                                </a>
                            </c:if>
                        </div>
                    </div>

                    <div class="reviews-block row col-xs-10 col-sm-10 col-md-10 col-lg-10">
                        <h2 class="headline review-headline"><fmt:message key = "review.headline" /></h2>

                        <c:if test="${not empty user}">
                            <c:if test="${reviewExists == true}">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <form class="review-form" action="controller" method="post">
                                        <input type="hidden" name="command" value="updateReview">
                                        <input type="hidden" name="movieId" value="${movie.id}">
                                        <div class="col-lg-12">
                                            <textarea type="text" name="reviewText" required minlength="20" maxlength="250" placeholder="<fmt:message key = "review.placeholder" />">${userReviewText}</textarea>
                                        </div>
                                        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                            <button type="submit"><fmt:message key = "button.edit" /></button>
                                        </div>
                                    </form>
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                        <a onclick="return confirm('<fmt:message key = "button.confirmText"/>')" href="controller?command=deleteReview&movieId=${movie.id}">
                                            <button class="delete-button">
                                                <fmt:message key = "review.delete" />
                                            </button>
                                        </a>
                                    </div>
                                </div>

                            </c:if>

                            <c:if test="${reviewExists == false}">
                            <div class="col-lg-12">
                                <form class="review-form" action="controller" method="post">
                                    <input type="hidden" name="command" value="addReview">
                                    <input type="hidden" name="movieId" value="${movie.id}">
                                    <div class="col-lg-12">
                                        <textarea type="text" name="reviewText" required minlength="20" maxlength="250" placeholder="<fmt:message key = "review.placeholder" />"></textarea>
                                    </div>
                                    <div class="col-lg-12">
                                        <button type="submit"><fmt:message key = "review.publish" /></button>
                                    </div>
                                </form>
                            </div>
                            </c:if>
                        </c:if>

                        <c:forEach var="review" items="${reviews}">
                            <div class="review-item col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                <div class="col-lg-11">
                                <h4>${review.user.name} ${review.user.surname}<br> <span><ctg:numeric-date-time date="${review.date}"/></span></h4>
                                <h5>${review.text}</h5>
                                </div>
                                <c:if test="${user.role == 'Admin'}">
                                    <div class="col-lg-1">
                                        <a onclick="return confirm('<fmt:message key = "button.confirmText"/>')"
                                           href="controller?command=deleteUserReview&userId=${review.user.id}&movieId=${movie.id}">
                                            <button class="admin-delete-button">
                                                <fmt:message key = "button.delete" />
                                            </button>
                                        </a>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="page-number-block row col-xs-10 col-sm-10 col-md-10 col-lg-10">
                        <c:forEach begin="1" end="${reviewPagesQuantity}" varStatus="loop">
                            <a href="controller?command=getfullmovie&movieId=${movie.id}&commentPage=${loop.index}"><p class="page-number-item">${loop.index}</p></a>
                        </c:forEach>

                    </div>
                </div>
            </div>
        </body>
    </html>
</fmt:bundle>