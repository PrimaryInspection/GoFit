<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%---------- Activity -----------%>
    <div class="container container-fluid table-bordered">
        <form action="/controller" method="post">


            <%---------------   activity  ---------------%>
            <div class="row ">
                <div class="col">
                    <div class="main-caption">SELECT TYPE OF ACTIVITY</div>

                    <%------------  Choose activity ------------%>
                    <select class="form-control" name="activityId">
                        <c:forEach var="activity" items="${activities}">
                            <option value="${activity.activityItemId}">
                                <c:out value="${activity.name}"/>
                            </option>
                        </c:forEach>
                    </select>

                    <%------------- Add New activity ------------%>
                    <div class="form-inline font12">
                        <label>Can't find an activity?</label>
                        <button type="submit" class="btn btn-link font12" name="command"
                                value="TO_ADD_ACTIVITY_PAGE">Add new
                        </button>
                    </div>
                </div>

                <%-----------------   ENTER DURATION   ------------------%>
                <div class="col">
                    <%---------- Enter activity time --------------%>
                    <div class="main-caption">ENTER DURATION (minutes)</div>
                    <input class="form-control" type="number" name="timeSpent" step="1" min="1" max="999"
                           value="30">
                </div>
            </div>

            <%------------  BUTTON: add to diary  ---------%>
            <div class="text-center">
                <button type="submit" class="btn btn-success" name="command" value="ADD_ACTIVITY">Add to Activity
                    Diary
                </button>
            </div>
        </form>
    </div>
    <br>
