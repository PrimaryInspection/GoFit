<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="main.selectactivity" var="selectactivity"/>
<fmt:message key="main.cantfindactivity" var="cantfindactivity"/>
<fmt:message key="main.addactivity" var="addactivity"/>
<fmt:message key="main.duration" var="duration"/>
<fmt:message key="main.addtoactdiaty" var="addtoactdiaty"/>


<%---------- Activity -----------%>
    <div class="container container-fluid table-bordered">
        <form action="/controller" method="post">


            <%---------------   activity  ---------------%>
            <div class="row ">
                <div class="col">
                    <div class="main-caption">${selectactivity}</div>

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
                        <label>${cantfindactivity}</label>
                        <button type="submit" class="btn btn-link font12" name="command"
                                value="TO_ADD_ACTIVITY_PAGE">${addactivity}
                        </button>
                    </div>
                </div>

                <%-----------------   ENTER DURATION   ------------------%>
                <div class="col">
                    <%---------- Enter activity time --------------%>
                    <div class="main-caption">${duration}</div>
                    <input class="form-control" type="number" name="timeSpent" step="1" min="1" max="999"
                           value="30">
                </div>
            </div>

            <%------------  BUTTON: add to diary  ---------%>
            <div class="text-center">
                <button type="submit" class="btn btn-success" name="command" value="ADD_ACTIVITY">${addtoactdiaty}
                </button>
            </div>
        </form>
    </div>
    <br>
