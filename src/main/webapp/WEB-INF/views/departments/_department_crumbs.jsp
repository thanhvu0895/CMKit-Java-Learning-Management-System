<c:choose>
  <c:when test="${current_user.admin}">
  	<li><t:link_to text="Departments" path="${UrlUtils.DEPARTMENTS_PATH}"></t:link_to></li>
  </c:when>
  <c:otherwise>
  <p> I am not a admin </p>
    <li>Departments</li>
  </c:otherwise>
</c:choose>
<li>${department.title}</li>