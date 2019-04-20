<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' href='/bootstrap/css/bootstrap.min.css' type='text/css'>
    <link rel='stylesheet' href='/bootstrap/css/login.css' type='text/css' media='all'>
</head>
<body>
<header>
        <div class="row">
            <div class="col logo">
            <span class="logo__first-word">
          Go
        </span>
                <span class="logo__second-word">
          Fit
        </span>
            </div>
        </div>
</header>
<main>


    <h1 class="h2-index h2-index_grey">
        Oops! Something went wrong...
    </h1>
    <form class="login-form" method="post" action="/controller">
        Request from ' ${pageContext.errorData.requestURI} ' is failed
        <br/>
        Servlet name or type: ${pageContext.errorData.servletName}
        <br/>
        Status code: ${pageContext.errorData.statusCode}
        <br/>
        Exception: ${pageContext.errorData.throwable}
        <br/>
        Error: ${nullPage}
        <button type="submit" value="Login" class="login-form__input login-form__btn_submit" name="command"
                value="TO_MAIN_PAGE">HOME
        </button>
    </form>
</main>
</body>
</html>

