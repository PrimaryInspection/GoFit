<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UK">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='/bootstrap/css/bootstrap.min.css' type='text/css'>
    <link rel='stylesheet' href='/bootstrap/css/styles.css' type='text/css' media='all'>
    <title>Add activity</title>
</head>

<body class="page page_white-bg">
<header class="header header-black">
    <div class="logo">
            <span class="logo__first-word">
          Go
        </span>
        <span class="logo__second-word">
          Fit!
        </span>
    </div>
</header>
<main>
    <h2 class="h2-page h2-page_grey">
        Add activity
    </h2>
    <form class="register-form" action="/controller" method="post">
        <div class="wrapper">
            <label><span>Name:</span>
                <input type="text"
                       required
                       maxlength="30"
                       name="name"
                       value="${newActivity.name}"
                       placeholder="Activity"
                       class="register-form__input <c:if test="${not empty errorActivityExistMessage}">border-danger</c:if>"></label>

            <label><span>Calories:</span>
                <input type="number"
                       required
                       name="calories" min="1" max="999" step="1"
                       value="${newActivity.calories}"
                       class="register-form__input"></label>

            <%--note (for 60 minutes)--%>
            <div>
                <label>* calories are indicated for 60 minutes of activity</label>
            </div>

        </div>

        <div class="text-center">
            <c:if test="${not empty errorActivityExistMessage}">
                <h4 class="error">${errorActivityExistMessage}</h4>
            </c:if>
        </div>

        <div class="wrapper">
            <button type="submit" name="command" value="ADD_NEW_ACTIVITY"
                    class="login-form__input login-form__input_submit">Add activity
            </button>

            <button type="submit" formnovalidate name="command" value="Cancel"
                    class="login-form__input login-form__input_submit btn-cancel">Cancel
            </button>
        </div>
    </form>
</main>
</body>
</html>