<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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


                <c:if test ="${not empty users}">
                    <c:if test="${users.admin}">
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
                <div class="hello">Hello, ${user.firstName}!</div>
                <div class="on_track">
                    ${remaining > 0 ? 'You are on track today!' : ''}</div>
                <div class="still_to_go_text">
                    <c:choose>
                        <c:when test="${kgToGoal > 0}">
                            <span class="still-to-go-number">${kgToGoal} kg </span>to your goal weight
                        </c:when>
                        <c:otherwise>
                            <span class="still-to-go-number">Well done! You have reached your weight goal!</span>
                        </c:otherwise>
                    </c:choose>
                </div>


                <div class="still_to_go_text">
                    <c:choose>
                        <c:when test="${remaining > 0}">
                            <span class="still-to-go-number">${remaining} calories </span>left to spend today
                        </c:when>
                        <c:otherwise>
                            <span class="still-to-go-number-minus">You are exceeding calories consumption by ${remaining} calories. Do more activities!</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <%--<div class="still_to_go_text"><span class="still-to-go-number">4 glasses </span> of water left to drink</div>--%>
            </div>
            <div class="col jumbotron jumbotron2 text-center">
                <div class="your-daily-summary">Your Daily Summary</div>
                <table class="table text-center borderless">
                    <thead>
                    <tr class="your-daily-summary-numbers your-daily-summary-table">
                        <th class="">${user.calories_norm}</th>
                        <th>-</th>
                        <th>${totalDayCalories}</th>
                        <th>+</th>
                        <th>${activitiesListTotals.calories}</th>
                        <th>=</th>
                        <th${remaining}
                        </th>
                    </tr>
                    </thead>
                    </thead>
                    <tbody>
                    <tr class="main-caption">
                        <td>GOAL</td>
                        <td></td>
                        <td>FOOD</td>
                        <td></td>
                        <td>ACTIVITY</td>
                        <td></td>
                        <td>
                            ${remaining < 0 ? 'EXCEEDING' : 'REMAINING'}
                        </td>
                    </tr>
                    </tbody>
                </table>
                <div>
                    <form name="calendarForm" method="post" action="controller">
                        <input type="hidden" name="command" value="Select_date">
                        <input required type="date" name="chosenDate" value="${chosenDateSession}" min="1900"/>

                        <%--<button type="submit" class="btn" name="command" value="Select_date">Select date</button>--%>
                        <input type="submit" class="button" value="Select Date">
                    </form>
                </div>
            </div>
        </div>
    </div>


    <div class="container table-bordered">

        <ul class="nav nav-tabs nav-justified" id="mainTab">
            <li class="nav-item"><a class="nav-link active" role="tab" data-toggle="tab" href="#food">Food</a>
            </li>
            <li class="nav-item"><a class="nav-link " role="tab" data-toggle="tab" href="#activity">Activity</a>
            </li>
 <%--           <li class="nav-item"><a class="nav-link" role="tab" data-toggle="tab" href="#water">Water</a>
            </li>--%>
            <li class="nav-item"><a class="nav-link " role="tab" data-toggle="tab" href="#bodyStats">Body
                stats</a>
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

