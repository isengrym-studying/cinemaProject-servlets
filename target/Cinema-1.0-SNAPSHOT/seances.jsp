<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename="language">

<html>
    <head>
        <script src="assets/js/scripts.js" type="text/javascript"></script>
        <link rel="stylesheet" href="assets/css/seances.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <title> <fmt:message key = "seances.title"/></title>
    </head>
    <body>

    <jsp:include page="header.jsp" />


    <div class="col-lg-2">

    </div>

    <div class="col-lg-8">

        <div class="seance-page-title col-xs-12 col-sm-12 col-md-12 col-lg-4">
            <h2 class="headline"><fmt:message key = "seances.headline" /></h2>
        </div>

        <div class="sorting-block col-xs-12 col-sm-12 col-md-12 col-lg-8">
            <select id="select1" onchange="firstSelectFunction()">
                <option value="-1"><fmt:message key = "movies.all" /></option>
                <c:forEach var="movie" items="${movies}">
                    <option <c:if test="${movieId == movie.id}">selected="selected"</c:if> value="${movie.id}">${movie.title}</option>
                </c:forEach>
            </select>
            <select id="select2" onchange="secondSelectFunction()">
                <option <c:if test="${sorting == 'date'}">selected="selected"</c:if> value="date"><fmt:message key = "sorting.sortByDate" /></option>
                <option <c:if test="${sorting == 'freeSeats'}">selected="selected"</c:if> value="freeSeats"><fmt:message key = "sorting.sortBySeat" /></option>
            </select>
            <select id="select3" onchange="thirdSelectFunction()">
                <option <c:if test="${order == 'ASC'}">selected="selected"</c:if> value="ASC"><fmt:message key = "sorting.sortASC" /></option>
                <option <c:if test="${order == 'DESC'}">selected="selected"</c:if> value="DESC"><fmt:message key = "sorting.sortDESC" /></option>
            </select>

            <a id="link" href="/controller?command=getseances&seancePage=1">
                <button onclick="getOption()"><fmt:message key = "button.confirm" /></button>
            </a>

            <script type="text/javascript">
                function getOption() {
                    selectElement = document.querySelector('#select1');
                    movieOutput = selectElement.value;

                    selectElement = document.querySelector('#select2');
                    sortingOutput = selectElement.value;

                    selectElement = document.querySelector('#select3');
                    orderOutput = selectElement.value;

                    var a = document.querySelector('#link');
                    a.setAttribute('href', "/controller?command=getseances&seancePage=1" + "&movieId="+movieOutput+"&sorting="+sortingOutput+"&order="+orderOutput);
                }
            </script>
        </div>


        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <c:if test="${sessionScope.user.role == 'Admin'}">
                <a href="/controller?command=newSeancePage"type="button">
                    <div class="seance-item row">
                        <div class="left-side col-sm-2 col-md-3 col-lg-3">
                            <img src="images/dummy.jpg" alt="" width="150" height="227">
                        </div>
                        <div class="right-side add-seance col-sm-3 col-md-6 col-lg-8">
                            <h3 class=""><fmt:message key = "admin.addSeance" /></h3>

                        </div>

                    </div>
                </a>
            </c:if>
        </div>

        <c:forEach var="seance" items="${seances}">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <a href="/controller?command=getfullmovie&movieId=${seance.movie.id}"type="button">
                    <div class="seance-item row">
                        <div class="left-side col-lg-3">
                            <img src="${seance.movie.imagePath}" alt="" width="170" height="257">
                        </div>
                        <div class="right-side col-lg-8">
                            <h3>${seance.movie.title}</h3>
                            <h5><span><fmt:message key = "movieItem.startDate"/></span><br>
                                    ${seance.startDate.getDayOfMonth()} <fmt:message key = "${seance.startDate.getMonth()}"/>,
                                <fmt:message key = "${seance.startDate.getDayOfWeek()}"/>, <ctg:start-time seance="${seance}"/>
                            </h5>
                            <h5><span><fmt:message key = "movieItem.endTime"/></span><br>
                                <ctg:end-time seance="${seance}"/>
                            </h5>
                            <h5><span><fmt:message key = "movieItem.freePlaces"/></span><br>
                                    ${seance.freePlaces}
                            </h5>
                            <a href="/controller?command=ticketChoicePage&seanceId=${seance.id}">
                                <button class="action-button">
                                    <fmt:message key = "ticket.buy"/>
                                </button>
                            </a>
                            <c:if test="${user.role == 'Admin'}">
                                <a onclick="return confirm('<fmt:message key = "button.confirmText"/>')" href="/controller?command=deleteSeance&seanceId=${seance.id}">
                                    <button class="action-button">
                                        <fmt:message key = "admin.deleteSeance"/>
                                    </button>
                                </a>
                            </c:if>
                        </div>

                    </div>
                </a>
            </div>
        </c:forEach>

    </div>

    <div class="col-lg-2">

    </div>


    <div class="page-number-block col-lg-12">
        <c:forEach begin="1" end="${seancePagesQuantity}" varStatus="loop">
            <a class="page-number-link" href="/controller?command=getseances&seancePage=${loop.index}&movieId=${movieId}&sorting=${sorting}&order=${order}"><p class="page-number-item">${loop.index}</p></a>
        </c:forEach>
    </div>

    </body>
</html>
</fmt:bundle>

