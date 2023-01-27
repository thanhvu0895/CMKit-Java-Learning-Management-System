<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<center>
  <h1>${course.title}</h1>
  <h4>${klass.semester} section ${klass.section}</h4>
  
 <ul class="nav nav-tabs">
  <li <c:if test="${param.current == ':assignments' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${klass.id}"><span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span>  Assignments</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':students' }">class="active"</c:if>>
  <t:link_to path="${UrlUtils.KLASS_STUDENTS_PATH}" secondId="${klass.id}"><span class="glyphicon glyphicon-education" aria-hidden="true"></span>  Students</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':gradebook' }">class="active"</c:if>>
  <t:link_to path="${UrlUtils.KLASS_GRADEBOOK_PATH}" secondId="${klass.id}"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>  Gradebook</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':files' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.KLASS_FILES_PATH}" secondId="${klass.id}"><span class="glyphicon glyphicon-folder-open" aria-hidden="true"></span>  Files</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':graders' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.KLASS_GRADERS_PATH}" secondId="${klass.id}"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Graders</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':settings' }">class="active"</c:if>>
  <t:link_to path="${UrlUtils.EDIT_KLASS_PATH}" id="${klass.id}"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span>  Settings</t:link_to>
  </li>
  
   <span class="pull-right"><t:link_to path="${UrlUtils.COURSES_PATH}/:id" classBS="btn btn-default" id="${course.id}"><span class="glyphicon glyphicon-book" aria-hidden="true"></span> Edit Course </t:link_to></span> 
 </ul>
</center>