<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<center>
  <h1>${department.title} department</h1>
</center>
<ul class="nav nav-tabs">
  <li <c:if test="${param.current == ':courses' }">class="active"</c:if>>
	<t:link_to path="${UrlUtils.DEPARTMENT_COURSES_PATH}" id="${department.id}"><span class="glyphicon glyphicon-book" aria-hidden="true"></span>  Courses</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':files' }">class="active"</c:if>>
	<t:link_to path="${UrlUtils.DEPARTMENT_FILES_PATH}" id="${department.id}"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>  Files</t:link_to>
  </li>
  
  <% // TODO: WRITE isDepartmentAdmin function in department model. %>
  <%-- <% if @department.is_department_admin?(current_user) %>  --%>
  <li <c:if test="${param.current == ':klasses' }">class="active"</c:if>>
	<t:link_to path="${UrlUtils.DEPARTMENT_CLASSES_PATH}" id="${department.id}"><span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span>  Classes</t:link_to>
  </li>
 
  <li <c:if test="${param.current == ':settings' }">class="active"</c:if>>
	<t:link_to path="${UrlUtils.EDIT_DEPARTMENT_PATH}" id="${department.id}"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>  Settings</t:link_to>
  </li>
</ul>