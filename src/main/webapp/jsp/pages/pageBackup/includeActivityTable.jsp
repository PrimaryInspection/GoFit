<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="main.activitydiary" var="activitydiary"/>
<fmt:message key="main.durationmin" var="durationmin"/>
<fmt:message key="main.burnt" var="burnt"/>
<fmt:message key="main.delete" var="delete"/>
<fmt:message key="main.totalofday" var="totalofday"/>


<div class="">
    <nav class="admin navbar justify-content-center">
        <span class="navbar-brand">${activitydiary}</span>
    </nav>
    <br/>

    <table class="table table-sm">
        <div class="col-12 bg-light food-row-meal-title">${chosenDateSession}</div>
        <thead class="text-right food-row-thead">
        <th scope="col"></th>
        <th scope="col">${durationmin}</th>
        <th scope="col">${burnt}</th>
        <th scope="col"></th>
        </thead>

        <tbody class="text-right">
        <c:forEach var="entry" items="${activitiesList}">
            <form method="post" action="/controller">
                <input type="hidden" name="activityId" value="${entry.id}">
                <tr class="food-row-tr">
                    <td class="food_table_first_col_width text-left">${entry.activity}</td>
                    <td>${entry.timeSpent}</td>
                    <td>${entry.calories}</td>
                    <td>
                        <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit" name="command"
                                value="DELETE_ENTRY_FROM_ACTIVITY_DIARY">${delete}
                        </button>
                    </td>
                </tr>
            </form>
        </c:forEach>

        <tr class="food-row-tr-bottom-final">
            <td>${totalofday}</td>
            <td>${activitiesListTotals.timeSpent}</td>
            <td>${activitiesListTotals.calories}</td>
            <td></td>
        </tr>
        </tbody>
    </table>


</div>



