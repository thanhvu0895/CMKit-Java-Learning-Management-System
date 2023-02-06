<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<center>
  <h1>${assignment.title}</h1>
  <c:if test="${assignment.getAssignmentType() == 'student_repo'}">
  	Template Repo: <kbd><%-- <%= get_repo_url(assigned.assignment.template_repo) %> --%></kbd>
  </c:if>
  
 <ul class="nav nav-tabs">
  <li <c:if test="${param.current == ':submissions' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.SUBMISSIONS_PATH}?assigned=:id" id="${assigned.id}"><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>  Submissions</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':problems' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_PROBLEMS_PATH}" secondId="${assignment.id}" id="${assigned.id}"><span class="glyphicon glyphicon-list" aria-hidden="true"></span>  Problems</t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':gradebook' }">class="active"</c:if>>
    <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_GRADEBOOK_PATH}" secondId="${assignment.id}" id="${assigned.id}"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>  Gradebook</t:link_to>
  </li>
  
 <c:if test="${current_user.admin || klass.isKlassProfessor(current_user)}"> 
 
  <li <c:if test="${param.current == ':submission_settings' }">class="active"</c:if>>
  <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_PATH}" secondId="${assignment.id}" id="${assigned.id}">
    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span><span class="glyphicon glyphicon-inbox" aria-hidden="true"></span>  Submission Settings
  </t:link_to>
  </li>
  
  <li <c:if test="${param.current == ':grading_settings' }">class="active"</c:if>>
  <t:link_to path="${UrlUtils.ASSIGNMENT_GRADE_SETTINGS_PATH}" secondId="${assignment.id}" id="${assigned.id}">
    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span><span class="glyphicon glyphicon-ok" aria-hidden="true"></span>  Grading Settings
  </t:link_to>
  </li>
  
   <span class="pull-right">
   <t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" classBS="btn btn-default" id="${assignment.id}">
     <span class="glyphicon glyphicon-file" aria-hidden="true"></span>  Edit Assignment & Rubric 
   </t:link_to>
   </span>
   
</c:if> 
 </ul>
</center>