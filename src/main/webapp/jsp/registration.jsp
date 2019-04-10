<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UK">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='/bootstrap/css/bootstrap.min.css' type='text/css'>
    <link rel='stylesheet' href='/bootstrap/css/styles.css' type='text/css' media='all'>
    <title>Registration page</title>
</head>

<body class="page page_white-bg">
<header class="header header-black">
    <div class="logo">
            <span class="logo__first-word">
          Fitness
        </span>
        <span class="logo__second-word">
          Buddy
        </span>
    </div>
</header>
<main>
    <h2 class="h2-page h2-page_grey">
        Sign Up
    </h2>
    <form class="register-form" action="/controller" method="post">
        <div class="wrapper">
            <input type="text"
                   required
                   maxlength="30"
                   name="first_name"
                   value="${registrationUser.firstName}"
                   placeholder="First name"
                   class="register-form__input">
            <input type="text"
                   required
                   maxlength="30"
                   name="last_name"
                   value="${registrationUser.lastName}"
                   placeholder="Last name"
                   class="register-form__input">

            <input type="text"
                   required
                   maxlength="30"
                   name="login"
                   value="${registrationUser.login}"
                   placeholder="Login"
                   class="register-form__input  <c:if test="${not empty errorLoginExistMessage}">border-danger</c:if>">
            <input type="email"
                   required
                   maxlength="30"
                   value="${registrationUser.email}"
                   name="email"
                   placeholder="e-mail"
                   class="register-form__input">
            <input type="password"
                   required
                   maxlength="30"
                   name="password"
                   placeholder="Password"
                   class="register-form__input">
            <input type="password"
                   required
                   maxlength="30"
                   name="password_confirmation"
                   placeholder="Confirm password"
                   class="register-form__input">
        </div>

        <div class="wrapper">
            <label><span>Birthday:</span>
                <input type="date"
                       required
                       max="${maxBirth}"
                       min="${minBirth}"
                       name="birthday"
                       value="${registrationUser.birthday}"
                       class="register-form__input"></label>

            <label><span>Height:</span>
                <input type="number"
                       required
                       name="height" min="50" max="250" step="1"
                       value="${registrationUser.height}"
                       class="register-form__input"> santimeters</label>

            <label><span>Weight:</span>
                <input type="number"
                       required
                       name="weight" min="1" max="300" step="0.1"
                       value="${registrationUser.weight}"
                       class="register-form__input"> kilos</label>

            <label><span>Goal weight:</span>
                <input type="number"
                       required
                       name="goalWeight" min="1" max="300" step="0.1"
                       value="${registrationUser.goalWeight}"
                       class="register-form__input"> kilos</label>


            <label><span>Lifestyle:</span>
                <select name="lifestyle" class="register-form__input">
                    <c:forEach var="item" items="${lifestyles}">
                        <option value="${item.id}"
                                <c:if test="${registrationUser.lifestyleId == item.id}">selected</c:if>>
                            <c:out value="${item.name}"></c:out>
                        </option>
                    </c:forEach>
                </select>
            </label>

        </div>

        <div class="text-center">
            <c:if test="${not empty errorLoginExistMessage}">
                <h4 class="error">${errorLoginExistMessage}</h4>
            </c:if>
            <c:if test="${not empty errorPassConfirmMessage}">
                <h4 class="error">${errorPassConfirmMessage}</h4>
            </c:if>
        </div><br>

        <div class="wrapper">

            <button type="submit" name="command" value="REGISTER_NEW_USER"
                    class="login-form__input login-form__input_submit">Register
            </button>

            <button type="submit" formnovalidate name="command" value="TO_LOGIN_PAGE"
                    class="login-form__input login-form__input_submit btn-cancel">Cancel
            </button>
        </div>
    </form>
</main>
</body>
</html>