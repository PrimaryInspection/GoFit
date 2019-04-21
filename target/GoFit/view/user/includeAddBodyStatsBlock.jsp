<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="main.save" var="save"/>
<fmt:message key="main.cancel" var="canel"/>
<fmt:message key="main.bottomcal" var="bottomcal"/>
<fmt:message key="main.dailynormbottom" var="dailynormbottom"/>
<fmt:message key="main.update" var="update"/>
<fmt:message key="main.ulifestyle" var="ulifestyle"/>
<fmt:message key="main.uheight" var="uheight"/>
<fmt:message key="main.birth" var="births"/>
<fmt:message key="main.statweight" var="statweight"/>
<fmt:message key="main.statgoalweight" var="statgoalweight"/>


<%---------- Body stats -----------%>
<div class="container container-fluid table-bordered">
    <form action="/controller" method="post">
        <h5 class="text-center">${update}</h5>

        <%--ACTIVITY--%>
        <div class="main-caption">${ulifestyle}</div>
        <select class="form-control" name="lifestyleId">
            <c:forEach var="item" items="${lifestyles}">
                <option value="${item.id}" <c:if test="${user.lifestyle_id == item.id}">selected</c:if>>
                    <c:out value="${item.name}"/>
                </option>
            </c:forEach>
        </select>


</div>

<%--COLUMN 2--%>
<div class="col">

    <%--HEIGHT--%>
    <div class="main-caption">${uheight}</div>
    <input required class="form-control" type="number" name="height" min="50" max="250" step="1"
           value="${user.height}"
           placeholder="Enter height">

    <br/>

    <%--BIRTHDAY--%>
    <div class="main-caption">${births}</div>
    <input required class="form-control" type="date" name="birthday" value="${user.birthday}"
           max="${currentDate}">
</div>

<%--COLUMN 3--%>
<div class="col">

    <%--WEIGHT--%>
    <div class="main-caption">${statweight}</div>
    <input required class="form-control" type="number" name="weight" min="1" max="250" step="1"
           value="${user.weight}"
           placeholder="Enter weight">

</div>

<%--COLUMN 4--%>
<div class="col">

    <%--GOAL WEIGHT--%>
    <div class="main-caption">${statgoalweight}</div>
    <input required class="form-control" type="number" name="weightGoal" min="1" max="250" step="1"
           value="${user.weightGoal}"
           placeholder="Enter goal weight">


</div>
</div>

<br/><br/>
<h6 class="text-center">${dailynormbottom}<span class="color-success">${user.calories_norm} ${bottomcal}</span>
</h6>
<br/>
<div class="text-center color-success">
    <c:if test="${not empty updateUserSuccessMessage}">${updateUserSuccessMessage}</c:if>
    <c:if test="${not empty updateUserErrorMessage}">${updateUserErrorMessage}</c:if>
</div>
<br/>
<%------------  BUTTON: add to diary  ---------%>
<div class="text-center">
    <button type="submit" class="btn btn-success" name="command" value="UPDATE_USER_INFO">${save}
    </button>
    <button type="submit" formnovalidate class="btn btn-secondary" name="command" value="CANCEL">${canel}
    </button>
</div>
</form>
</div>
<br>
