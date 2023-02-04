<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester} ${klass.section} | Assignments">
<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Assignments</li>
</ol>

<jsp:include page="../klasses/_tabs.jsp"><jsp:param name="current" value=":assignments"/></jsp:include>

<br>

<span class="pull-left"><h4> Class Assignments: </h4></span>
<span class="pull-right">
 <t:link_to path="${UrlUtils.NEW_ASSIGNMENT_PATH}?class=:id" id="${klass.id}" classBS="btn btn-success">
   <span class="glyphicon glyphicon-plus" aria-hidden="true"></span><span class="glyphicon glyphicon-file" aria-hidden="true"></span> New Assignment (for this class)
   </t:link_to>
 <t:link_to path="${UrlUtils.SHOW_COPY_ASSIGNMENT_PATH}?klass=:id" id="${klass.id}" classBS="btn btn-primary">
   <span class="glyphicon glyphicon-duplicate" aria-hidden="true"></span> Copy Assignment
 </t:link_to>
</span>

<table class="table table-hover">
<thead>
  <tr>
	<td>Assignment</td>
	<td>Category</td>
	<td>Points</td>
	<td>Type</td>
	<td>Due</td>
	<td>Graders</td>
	<td>Status</td>
	<td>Grading</td>
  </tr>
</thead>
<tbody>
  <c:set var="grade_categories" value="${klass_grade_categories}" />
  <c:set var="assigneds" value="${klass_assigneds}" />
  <c:forEach var="a" items="${klass_assignments}" varStatus="loop">
   <%@ include file="../klasses/_assignment_row.jsp" %>
  </c:forEach>
	

</tbody>
</table>

<br>
	<p>
	  <h4><t:link_to path="${UrlUtils.COURSES_PATH}/:id" id="${course.id}">Assignments Inherited From Course:</t:link_to></h4>
	  <table class="table table-hover">
		<thead>
		  <tr>
			<td>Assignment</td>
			<td>Category</td>
			<td>Points</td>
			<td>Type</td>
			<td>Due</td>
			<td>Graders</td>
			<td>Status</td>
			<td>Grading</td>
		  </tr>
		</thead>
		<tbody>
		<c:set var="grade_categories" value="${course_grade_categories}"/>
		<c:forEach var="a" items="${course_assignments}" varStatus="loop">
		<c:set var="assigneds" value="${course_assigneds}" />
			<%@ include file="../klasses/_assignment_row.jsp" %>
		</c:forEach>
		</tbody>
	  </table>
	</p>

</t:layoutj>