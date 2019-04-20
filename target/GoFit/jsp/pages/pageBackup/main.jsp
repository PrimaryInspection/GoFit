<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="main.hello" var="hello"/>
<fmt:message key="main.results" var="results"/>
<fmt:message key="main.kg" var="kg"/>
<fmt:message key="main.togoal" var="togoal"/>
<fmt:message key="main.calor" var="calor"/>
<fmt:message key="main.spend" var="spend"/>
<fmt:message key="main.food" var="food"/>
<fmt:message key="main.activities" var="activities"/>
<fmt:message key="main.stats" var="stats"/>
<fmt:message key="main.goal" var="goal"/>
<fmt:message key="main.calspend" var="calspend"/>
<fmt:message key="main.activspend" var="activityspend"/>
<fmt:message key="main.remain" var ="remain"/>
<fmt:message key="main.datesel" var="datesel"/>
<fmt:message key="main.daysum" var = "daysummary"/>/





<html>
<head>
    <title>Home page</title>
    <jsp:include page="header.jsp"/>
    <link rel="stylesheet" href="/bootstrap/css/main.css">
    <link rel="stylesheet" href="/bootstrap/css/adminpage.css">
    <script type="text/javascript">
        $(document).ready(function () {
            $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
                localStorage.setItem('activeTab', $(e.target).attr('href'));
            });
            var activeTab = localStorage.getItem('activeTab');
            if (activeTab) {
                $('#mainTab a[href="' + activeTab + '"]').tab('show');
            }
        });
    </script>
</head>


<body class="bg">

<div class="container bg-container">
    <%--------- HEADER ---------%>
    <div class="row">
        <nav class="navbar container-fluid navbar-header">
            <form class="form-no-margin-bottom" action="/controller" method="post">
                <button class="navbar-brand fitness-buddy btn btn-link" name="command" value="TO_MAIN_PAGE"><span
                        class="fitness">Go</span>Fit!
                </button>
            </form>



            <form class="form-inline form-no-margin-bottom" method="post" action="/controller">
                <form method="post" action="/controller">
                    <input type="hidden" name="command" value="SET_LOCALE_MAIN">
                    <div class="row">

                        <div class="col py-4 text-center">
                            <button type="submit" class="btn btn-link" name="locale" value="EN">en</button>
                            <button type="submit" class="btn btn-link" name="locale" value="UA">укр</button>
                        </div>
                    </div>
                </form>
                <c:if test="${not empty user}">
                    <c:if test="${user.admin}">
                        <button type="submit" class="btn btn-link" name="command" value="TO_ADMIN_PAGE">Admin</button>
                    </c:if>
                    <button type="submit" class="btn btn-link" name="command" value="LOGOUT">Logout</button>
                </c:if>

            </form>
        </nav>
    </div>
    <br/>

    <%----------- JUMBOTRONs ------------%>
    <div class="container container-fluid table-bordered">
        <div class="row">
            <div class="col-4 jumbotron jumbotron1">
                <div class="hello">${hello} ${user.firstName}!</div>
                <div class="on_track">
                    ${remaining > 0 ? results : ''}</div>
                <div class="still_to_go_text">
                    <c:choose>
                        <c:when test="${kgToGoal > 0}">
                            <span class="still-to-go-number">${kgToGoal} ${kg} </span>${togoal}
                        </c:when>
                        <c:otherwise>
                            <span class="still-to-go-number">Well done! You have reached your weight goal!</span>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="still_to_go_text">
                    <c:choose>
                        <c:when test="${remaining > 0}">
                            <span class="still-to-go-number">${remaining} ${calor} </span> ${spend}
                        </c:when>
                        <c:otherwise>
                            <span class="still-to-go-number-minus">You are exceeding calories consumption by ${remaining} calories. Do more activities!</span>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="col jumbotron jumbotron2 text-center">
                <div class="your-daily-summary">${daysummary}</div>
                <table class="table text-center borderless">
                    <thead>
                    <tr class="your-daily-summary-numbers your-daily-summary-table">
                        <th class="">${user.calories_norm}</th>
                        <th>-</th>
                        <th>${totalDayCalories}</th>
                        <th>+</th>
                        <th>${activitiesListTotals.calories}</th>
                        <th>=</th>
                        <th>${remaining}</th>
                        </th>
                    </tr>
                    </thead>
                    </thead>
                    <tbody>
                    <tr class="main-caption">
                        <td>${goal}</td>
                        <td></td>
                        <td>${calspend}</td>
                        <td></td>
                        <td>${activityspend}</td>
                        <td></td>
                        <td>
                            ${remain}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div>
                    <form name="calendarForm" method="post" action="controller">
                        <input type="hidden" name="command" value="Select_date">
                        <input required type="date" name="chosenDate" value="${chosenDateSession}" min="1900"/>

                        <%--<button type="submit" class="btn" name="command" value="Select_date">Select date</button>--%>
                        <input type="submit" class="button" value=${datesel}>
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="container table-bordered">

        <ul class="nav nav-tabs nav-justified" id="mainTab">
            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#food">${food}</a>
            </li>
            <li class="nav-item"><a class="nav-link " role="tab" data-toggle="tab" href="#activity">${activities}</a>
            </li>
 <%--           <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#water">Water</a>
            </li>--%>
            <li class="nav-item"><a class="nav-link " role="tab" data-toggle="tab" href="#bodyStats"> ${stats} </a>
            </li>
        </ul>


        <div class="tab-content table-bordered">
            <br/><br/>

            <%---------- FOOD -----------%>
            <div class="tab-pane active" id="food" role="tabpanel">
                <jsp:include page="/jsp/pages/includeAddFoodBlock.jsp"/>

                <%-------------  SHOW FOOD DIARY  ------------%>
                <c:if test="${not empty meals}">
                    <jsp:include page="/jsp/pages/includeFoodTable.jsp"/>
                </c:if>
            </div>

            <%------------ ACTIVITY ---------------%>
            <div class="tab-pane " id="activity" role="tabpanel">
                <jsp:include page="/jsp/pages/includeAddActivityBlock.jsp"/>

                <%-------------  SHOW ACTIVITY DIARY  ------------%>
                <c:if test="${not empty activitiesList}">
                    <jsp:include page="/jsp/pages/includeActivityTable.jsp"/>
                </c:if>
            </div>

            <%------------- BODY STATS ------------%>
            <div class="tab-pane " id="bodyStats" role="tabpanel">
                <jsp:include page="/jsp/pages/includeAddBodyStatsBlock.jsp"/>
            </div>
        </div>

    </div>


</body>
</html>

