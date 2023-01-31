<c:choose>
<c:when test="${not empty klass}">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${klass.id}">${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</t:link_to></li>
</c:when>
<c:otherwise>
<%@ include file="../courses/_course_crumbs.jsp" %>
 <li><t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}">Assignments</t:link_to></li>
</c:otherwise>
</c:choose>