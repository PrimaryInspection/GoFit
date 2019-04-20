<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="main.total" var = "total"/>
<fmt:message key="main.totalday" var="totalday"/>
<fmt:message key="main.uweight" var="uweight"/>
<fmt:message key="main.ucalories" var="ucalories"/>
<fmt:message key="main.uproteins" var="uproteins"/>
<fmt:message key="main.ufats" var="ufats"/>
<fmt:message key="main.ucarbs" var="ucarbs"/>
<fmt:message key="main.foodtracking" var="foodtracking"/>






<div class="">
    <nav class="admin navbar justify-content-center">
        <span class="navbar-brand">${foodtracking}</span>
    </nav>
    <br/>
    <c:forEach var="entry" items="${meals}">
        <c:if test="${not empty entry.value}">

            <table class="table table-sm">
                <div class="col-12 bg-light food-row-meal-title">${entry.key}</div>
                <thead class="text-right food-row-thead">
                <th scope="col"></th>
                <th scope="col">${uweight}</th>
                <th scope="col">${ucalories}</th>
                <th scope="col">${uproteins}</th>
                <th scope="col">${ufats}</th>
                <th scope="col">${ucarbs}</th>
                <th scope="col"></th>
                </thead>

                <tbody class="text-right">
                    <%--Meals--%>
                <c:forEach var="mealEntry" items="${entry.value}">
                    <form action="/controller" method="post">
                        <input type="hidden" name="meal_id" value="${mealEntry.id}">
                        <tr class="food-row-tr">
                            <td class="food_table_first_col_width text-left">${mealEntry.mealItem}</td>
                            <td>${mealEntry.weight}</td>
                            <td>${mealEntry.calories}</td>
                            <td>${mealEntry.proteins}</td>
                            <td>${mealEntry.fats}</td>
                            <td>${mealEntry.carbs}</td>
                        <%--    <td>
                                <button class="btn btn-link btn-block food-row-tr-btn btn-sm" type="submit"
                                        name="command"
                                        value="DELETE_ENTRY_FROM_FOOD_DIARY">Delete
                                </button>
                            </td>--%>
                        </tr>
                    </form>
                </c:forEach>

                    <%--Meal totals--%>
                <tr class="food-row-tr-bottom">
                    <td>${total}</td>
                    <td>${totalsByMealType[entry.key].weight}</td>
                    <td>${totalsByMealType[entry.key].calories}</td>
                    <td>${totalsByMealType[entry.key].proteins}</td>
                    <td>${totalsByMealType[entry.key].fats}</td>
                    <td>${totalsByMealType[entry.key].carbs}</td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </c:if>

    </c:forEach>

    <%--Day totals--%>
    <table class="table table-sm">
        <div class="col-12 bg-light food-row-meal-title"></div>
        <thead class="text-right food-row-thead">
        <td class="food_table_first_col_width text-left"></td>
        <th class="invisible" scope="col">WEIGHT</th>
        <th class="invisible" scope="col">CALORIES</th>
        <th class="invisible" scope="col">PROTEINS</th>
        <th class="invisible" scope="col">FATS</th>
        <th class="invisible" scope="col">CARBS</th>
        </thead>

        <tbody class="text-right">

        <tr class="food-row-tr-bottom-final">
            <th>${totalday}</th>
            <th>${totalDayFoodWeight}</th>
            <th>${totalDayCalories}</th>
            <th>${totalDayProteins}</th>
            <th>${totalDayFat}</th>
            <th>${totalDayCarbs}</th>
<%--            <td>
                <button class="btn btn-link btn-block invisible food-row-tr-btn btn-sm" type="button">Delete
                </button>
            </td>--%>
        </tr>
        </tbody>
    </table>

</div>


