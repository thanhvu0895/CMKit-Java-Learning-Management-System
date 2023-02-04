<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${assignment.title}">

<ol class="breadcrumb">
  <%@ include file="_assignment_crumbs.jsp" %>
  <li class="active">${assignment.title}</li>
</ol>

<center>

<p>
  <strong><h1>${assignment.title}</h1></strong>
</p>

<span style="white-space:pre"><c:out value="${assignment.description}" escapeXml="false"/></span>

<p>
  <strong>Grade category:</strong>
  ${ not empty grade_category ? grade_category.title : "none"}
  <br>Worth ${assignment.total_points == 0 ? 0 : assignment.total_points}
</p>

<p>
  <t:link_to path="${UrlUtils.EDIT_ASSIGNMENT_PATH}" classBS="btn btn-default"  id="${assignment.id}">Edit Title & Category</t:link_to>
  <t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEMS_PATH}" classBS="btn btn-primary"  secondId="${assignment.id}">Edit Grading Rubric</t:link_to>
</p>

<p>
  <strong>Type:</strong>
  ${assignment.getAssignmentType()}
</p>

</center>

<c:if test="${not empty student_repo}">
<div class="panel panel-default">
      <div class="panel-heading"><h3>Template Repository</h3></div>
		<div class="panel-body">
		<p>
		  <strong>Repository URL:</strong>
		  <%-- <kbd><%= get_repo_url @assignment.template_repo %></kbd> --%>
		  <br>Students will start the assignment with a clone of this repository.
		</p>
	  </div>
	</div>
</c:if>


<a data-toggle="collapse" href="#delete_hidden" class="btn btn-danger">Delete Assignment</a>
<div id="delete_hidden" class="collapse collapse-in">
  <a data-toggle="collapse" href="#delete_hidden2" class="btn btn-danger">Really Delete Assignment</a>
  <div id="delete_hidden2" class="collapse collapse-in">
  <t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" classBS="btn btn-warning" id="${course.id}" method="DELETE" confirm="Are you really, really, really sure you want to delete this assignment?">Really, Really Delete Assignment</t:link_to>
  </div>
</div>
</t:layoutj>
<%// WRITE PROBLEM MODEL, REPOSITORY AND MAPPER%>