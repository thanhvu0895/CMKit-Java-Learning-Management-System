<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<body>

<h1>Students</h1>
<ul>
<c:forEach var="i" items="${students}">
  <li>${i.name} - ${i.age}</li>
</c:forEach>


</ul>

</body>
</html>