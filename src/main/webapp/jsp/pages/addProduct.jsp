<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="UK">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='/bootstrap/css/bootstrap.min.css' type='text/css'>
    <link rel='stylesheet' href='/bootstrap/css/styles.css' type='text/css' media='all'>
    <title>Add product</title>
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
        Add product
    </h2>
    <form class="register-form" action="/controller" method="post">
        <div class="wrapper">
            <label><span>Name:</span>
                <input type="text"
                       required
                       maxlength="30"
                       name="name"
                       value="${newProduct.name}"
                       placeholder="Name"
                       class="register-form__input <c:if test="${not empty errorProductExistMessage}">border-danger</c:if>"></label>

            <label><span>Calories:</span>
                <input type="number"
                       required
                       name="calories" min="0" max="999" step="1"
                       value="${newProduct.calories}"
                       class="register-form__input"></label>

            <label><span>Protein:</span>
                <input type="number"
                       required
                       name="protein" min="0" max="99" step="0.1"
                       value="${newProduct.protein}"
                       class="register-form__input"></label>

            <label><span>Fat:</span>
                <input type="number"
                       required
                       name="fat" min="0" max="99" step="0.1"
                       value="${newProduct.fat}"
                       class="register-form__input"></label>

            <label><span>Carbs:</span>
                <input type="number"
                       required
                       name="carbs" min="0" max="99" step="0.1"
                       value="${newProduct.carbs}"
                       class="register-form__input"></label>

            <%--note (in 100 gramms...)--%>
            <div>
                <label>* numbers are indicated in 100 grams of the product</label>
            </div>

        </div>

        <div class="text-center">
            <c:if test="${not empty errorProductExistMessage}">
                <h4 class="error">${errorProductExistMessage}</h4>
            </c:if>
        </div>

        <div class="wrapper">
            <button type="submit" name="command" value="ADD_NEW_PRODUCT"
                    class="login-form__input login-form__input_submit">Add product
            </button>

            <button type="submit" formnovalidate name="command" value="Cancel"
                    class="login-form__input login-form__input_submit btn-cancel">Cancel
            </button>
        </div>
    </form>
</main>
</body>
</html>