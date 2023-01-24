<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<center>
  <strong><h1> ${course.title}  </h1></strong>
  <h4> ${course.course_code} </h4>
</center>

<ul class="nav nav-tabs">
  
  <li <c:if test="${param.current == ':assignments' }">class="active"</c:if>>
  	<t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}"><span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span>  Assignments</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':files' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.COURSE_FILES_PATH}" id="${course.id}"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>  Files</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':grade_categories' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.COURSE_GRADE_CATEGORIES_PATH}" id="${course.id}"><span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>  Grade Categories</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':settings' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.EDIT_COURSE_PATH}" id="${course.id}"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>  Settings</t:link_to>
  </li>
  
  <span class="pull-right"><t:link_to path="${UrlUtils.NEW_KLASS_PATH}?course=:id" classBS="btn btn-success"  id="${course.id}">Create Class</t:link_to></span>
  
</ul>
