<%@ include file="../departments/_department_crumbs.jsp" %>
<li><t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${department.id}">Courses</t:link_to></li>
<li>${course.course_code}&nbsp${course.title}</li>
<%-- <h1>DONE</h1> --%>