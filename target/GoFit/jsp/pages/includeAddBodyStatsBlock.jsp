<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%---------- Body stats -----------%>
<div class="container container-fluid table-bordered">
    <form action="/controller" method="post">
        <h5 class="text-center">You can update your body stats here</h5>

                <%--ACTIVITY--%>
                <div class="main-caption">ACTIVITY</div>
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
                <div class="main-caption">HEIGHT (in santimeters)</div>
                <input required class="form-control" type="number" name="height" min="50" max="250" step="1"
                       value="${user.height}"
                       placeholder="Enter height">

                <br/>

                <%--BIRTHDAY--%>
                <div class="main-caption">BIRTHDAY</div>
                <input required class="form-control" type="date" name="birthday" value="${user.birthday}"
                       max="${currentDate}">
            </div>

            <%--COLUMN 3--%>
            <div class="col">

                <%--WEIGHT--%>
                <div class="main-caption">WEIGHT (in kilos)</div>
                <input required class="form-control" type="number" name="weight" min="1" max="250" step="1"
                       value="${user.weight}"
                       placeholder="Enter weight">

            </div>

            <%--COLUMN 4--%>
            <div class="col">

                <%--GOAL WEIGHT--%>
                <div class="main-caption">ENTER GOAL WEIGHT</div>
                <input required class="form-control" type="number" name="weightGoal" min="1" max="250" step="1"
                       value="${user.weightGoal}"
                       placeholder="Enter goal weight">


            </div>
        </div>

        <br/><br/>
        <h6 class="text-center">Your daily calorie requirement is <span class="color-success">${user.calories_norm} calories.</span>
        </h6>
        <%--<h6 class="text-center">To reach your goal, we suggest you to consume a maximum amount of 1700 calories, 112 g--%>
        <%--proteins, 88 g fats, and--%>
        <%--174 g carbs per day.</h6>--%>
        <br/>
        <div class="text-center color-success">
            <c:if test="${not empty updateUserSuccessMessage}">${updateUserSuccessMessage}</c:if>
            <c:if test="${not empty updateUserErrorMessage}">${updateUserErrorMessage}</c:if>
        </div>
        <br/>
        <%------------  BUTTON: add to diary  ---------%>
        <div class="text-center">
            <button type="submit" class="btn btn-success" name="command" value="UPDATE_USER_INFO">Save changes
            </button>
            <button type="submit" formnovalidate class="btn btn-secondary" name="command" value="CANCEL">Cancel
            </button>
        </div>
    </form>
</div>
<br>
