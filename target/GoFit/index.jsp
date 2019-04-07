<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
  <title>Title</title>
</head>
<body >
<form method="get" action="MainServlet">
  <h1 align="center">GoFit</h1>
    ${meal}
  <table border="1" cellpadding="30%" align="center" bordercolor="red">
      <tr>
      <th>№</th>
      <th>Name</th>
      <th>Fats</th>
      <th>Calories</th>
      <th>Proteins</th>
      <th>Carbs</th>
    </tr>

    <c:forEach var="mealItems"  items="${meal}">
      <tr>
          <td>${mealItems}</td>
          <td>${meal}</td>
          <td>${mealItems.id}</td>
          <td>${mealItems.name}</td>
          <td>${mealItems.fats}</td>
          <td>${mealItems.calories}</td>
          <td>${mealItems.proteins}</td>
          <td>${mealItems.carbs}</td>
      </tr>
    </c:forEach>

  </table>
</form>
</body>
</html>



