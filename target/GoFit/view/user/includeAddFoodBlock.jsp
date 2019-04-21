<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="main.selectprod" var="selectprod"/>
<fmt:message key="main.selectquantity" var="selectquantity"/>
<fmt:message key="main.mealtime" var="mealtime"/>
<fmt:message key="main.cantfindmeal" var="cantfindmeal"/>
<fmt:message key="main.addfood" var="addfood"/>
<fmt:message key="main.addtofooddiary" var="addtofooddiary"/>


<%---------- FOOD -----------%>
<div class="container container-fluid table-bordered">
    <form action="/controller" method="post">


        <%---------------   select product  ---------------%>
        <div class="row ">
            <%----------------   SELECT PRODUCT   -----------------%>
            <div class="col">
                <div class="main-caption">${selectprod}</div>

                <%------------  Choose product ------------%>
                <select class="form-control" name="meal_item_id">
                    <c:forEach var="product" items="${mealItems}">
                        <option value="${product.mealItemId}">
                            <c:out value="${product.name}"/>
                        </option>
                    </c:forEach>
                </select>

                <%------------- Add New product ------------%>
                <div class="form-inline font12">
                    <label>${cantfindmeal}</label>
                    <button type="submit" class="btn btn-link font12" name="command"
                            value="TO_ADD_PRODUCT_PAGE">${addfood}
                    </button>
                </div>
            </div>

            <%-----------------   ENTER QUANTITY CONSUMED   ------------------%>
            <div class="col">
                <%---------- Enter product amount --------------%>
                <div class="main-caption">${selectquantity}</div>
                <input class="form-control" type="number" name="weight" step="1" min="1" max="999"
                       value="100">

            </div>

            <div class="col">
                <div class="main-caption">${mealtime}</div>

                <%------------  Choose meal type ------------%>
                <select class="form-control" name="meal_type_id">
                    <c:forEach var="meal_type" items="${mealTypes}">
                        <option value="${meal_type.mealTypeId}">
                            <c:out value="${meal_type.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <%------------  BUTTON: add to diary  ---------%>
        <div class="text-center">
            <button type="submit" class="btn btn-success" name="command" value="ADD_MEAL">${addtofooddiary}</button>
        </div>
    </form>
</div>
<br>