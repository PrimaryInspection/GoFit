<div class="row">
    <nav class="navbar container-fluid navbar-header">
        <form class="form-no-margin-bottom" action="/controller" method="post">
            <button class="navbar-brand fitness-buddy btn btn-link" name="command" value="HOME"><span class="fitness">Fitness</span>Buddy
            </button>
        </form>
        <form class="form-inline form-no-margin-bottom" method="post" action="/controller">


            <c:if test="${not empty user}">
                <c:if test="${user.admin}">
                    <button type="submit" class="btn btn-link" name="command" value="TO_ADMIN_PAGE">Admin</button>
                </c:if>
                <%--<input type="submit" name="<command>" value="TO_LOGIN_PAGE">--%>
                <button type="submit" class="btn btn-link" name="command" value="LOGOUT">Logout</button>
            </c:if>
        </form>
    </nav>
</div>