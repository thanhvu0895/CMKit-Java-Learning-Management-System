<li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>

<c:choose>
<c:when test="${klass.isKlassAdmin(current_user)}">
<li><t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${klass.id}">${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</t:link_to></li>
<li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
</c:when>
<c:otherwise>
<li><t:link_to path="${UrlUtils.KLASSES_PATH}/:id" id="${klass.id}">${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</t:link_to></li>
<li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
</c:otherwise>
</c:choose>

