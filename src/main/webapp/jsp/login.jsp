<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="lang"/>

<fmt:message key="login.title" var="loginTitle"/>
<fmt:message key="login.login" var="loginPlaceholder"/>
<fmt:message key="login.password" var="passwordPlaceholder"/>
<fmt:message key="login.loginbutton" var="loginBtn"/>
<fmt:message key="login.registrationbutton" var="registerBtn"/>
<fmt:message key="login.notamember" var="notAMember"/>
<fmt:message key="login.incorrectlog" var="incorrectLog"/>
<fmt:message key="login.userblocked" var="userBlocked"/>
<fmt:message key="login.registrsuccess" var="registrSuccess"/>
<fmt:message key="login.usernull" var="userNull"/>
<fmt:message key="login.gaslo" var="gaslo"/>

<html lang="UK">
<head>

    <title>${loginTitle}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='/bootstrap/css/bootstrap.min.css' type='text/css'>
    <link rel='stylesheet' href='/bootstrap/css/login.css' type='text/css' media='all'>
</head>
<body>
<header>
    <form method="post" action="/controller">
        <input type="hidden" name="command" value="SET_LOCALE">
        <div class="row">
            <div class="col logo">
            <span class="logo__first-word">
          Go
        </span>
                <span class="logo__second-word">
          Fit!
        </span>
            </div>
            <div class="col text-center">
                <c:if test="${not empty registrationSuccessMessage}">
                    <h4 class="success">${registrSuccess}</h4>
                </c:if>
                <c:if test="${not empty errorLoginPassMessage}">
                    <h4 class="error">${incorrectLog}</h4>
                </c:if>
                <c:if test="${not empty userBlockMessage}">
                    <h4 class="error">${userBlocked}</h4>
                </c:if>
                <c:if test="${not empty userNullMessage}">
                    <h4 class="error">${userNull}</h4>
                </c:if>
            </div>
            <div class="col py-4 text-center">
                <button type="submit" class="btn btn-link" name="locale" value="EN">en</button>
                <button type="submit" class="btn btn-link" name="locale" value="UA">укр</button>
            </div>
        </div>
    </form>

</header>
<main>


    <h2 class="h2-index h2-index_grey">
        ${gaslo}
    </h2>
    <form class="login-form" method="post" action="/controller">
        <div class="wrapper">
            <input
                    type="text"
                    name="login"
                    placeholder="${loginPlaceholder}"
                    class="login-form__input <c:if test="${not empty errorLoginPassMessage}">border-danger</c:if>"
                    value="${login}">
            <input
                    type="password"
                    name="password"
                    placeholder="${passwordPlaceholder}"
                    class="login-form__input <c:if test="${not empty errorLoginPassMessage}">border-danger</c:if>"
                    value="${password}">
        </div>

        <button type="submit" value="Login" class="login-form__input login-form__btn_submit" name="command"
                value="to_registration_page">${loginBtn}
        </button>

        <div class="wrapper">
            <span class="login-form__questions">${notAMember}&nbsp;</span>
            <button type="submit" class="btn btn-link" name="command"
                    value="to_registration_page">${registerBtn}
            </button>
        </div>
    </form>
</main>
</body>
</html>