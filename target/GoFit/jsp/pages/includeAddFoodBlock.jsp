<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%---------- FOOD -----------%>
<div class="container container-fluid table-bordered">
    <form action="/controller" method="post">


        <%---------------   select product  ---------------%>
        <div class="row ">
            <%----------------   SELECT PRODUCT   -----------------%>
            <div class="col">
                <div class="main-caption">SELECT PRODUCT</div>

                <%------------  Choose product ------------%>
                <select class="form-control" name="product_id">
                    <c:forEach var="product" items="${products}">
                        <option value="${product.id}">
                            <c:out value="${product.name}"/>
                        </option>
                    </c:forEach>
                </select>

                <%------------- Add New product ------------%>
                <div class="form-inline font12">
                    <label>Can't find a product?</label>
                    <button type="submit" class="btn btn-link font12" name="command"
                            value="TO_ADD_PRODUCT_PAGE">Add new
                    </button>
                </div>
            </div>

            <%-----------------   ENTER QUANTITY CONSUMED   ------------------%>
            <div class="col">
                <%---------- Enter product amount --------------%>
                <div class="main-caption">ENTER QUANTITY (grams)</div>
                <input class="form-control" type="number" name="weight" step="1" min="1" max="999"
                       value="100">

            </div>

            <div class="col">
                <div class="main-caption">SELECT MEAL</div>

                <%------------  Choose meal type ------------%>
                <select class="form-control" name="meal_type_id">
                    <c:forEach var="meal_type" items="${mealTypes}">
                        <option value="${meal_type.id}">
                            <c:out value="${meal_type.name}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <%------------  BUTTON: add to diary  ---------%>
        <div class="text-center">
            <button type="submit" class="btn btn-success" name="command" value="ADD_MEAL">Add to Food
                Diary
            </button>
        </div>
    </form>
</div>
<br>