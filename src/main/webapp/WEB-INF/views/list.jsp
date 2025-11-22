<html>
  <body>
    <h2>TODO List</h2>

    <a href="/">Add More</a>

    <ul>
      <c:forEach var="item" items="${list}">
        <li>${item.task}</li>
      </c:forEach>
    </ul>
  </body>
</html>
