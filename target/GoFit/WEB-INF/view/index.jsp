<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body >
<table>
    <c:forEach items="${items}" var="item">
        <tr>
            <td>${item.userId}</td>
            <td>${item.firstName}</td>
            <td>${item.secondName}</td>
            <td>${item.login}</td>
            <td>${item.password}</td>
            <td>${item.email}</td>
            <td>${item.birthday}</td>
            <td>${item.weight}</td>
            <td>${item.weightGoal}</td>
            <td>${item.height}</td>
            <td>${item.calories_norm}</td>
            <td>${item.lifestyle}</td>
            <td>${item.status}</td>
            <td>${item.roleId}</td>

        </tr>
    </c:forEach>
</table>
</form>
</body>
</html>

