<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${assignment.title} | Grading Settings">

<ol class="breadcrumb">
  <%@ include file="../assigneds/_grading_crumbs.jsp"%>
  <li class="active">Grading Settings</li>
</ol>

<jsp:include page="../assigneds/_tabs.jsp"><jsp:param name="current" value=":grading_settings"/></jsp:include>

<h3>Grading Settings for ${assignment.title}</h3>

<div class="panel-group">
  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#graders"><strong>Assign Graders<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="graders" class="panel-collapse collapse in">
	  <div class="panel-body">
	    Note that administrators and class professors already have permission to grade any assignment.
		<table class="table table-hover">
		  <thead>
		    <tr>
			  <th>User</th>
			  <th>Assign/Unassign</th>
			</tr>
		  </thead>
		  <tbody>
		  <c:forEach var="u" items="${grader_users}" varStatus="loop">
		  <tr>
		  	<td>${u.getFull_preferred_name()}</td>
		   	<c:choose>
		   	  <c:when test="${not empty graders[loop.index].assignedStatus}">
		   	    <td><t:link_to path="${UrlUtils.REMOVE_GRADER_PATH}" classBS="btn btn-danger" method="DELETE" id="${graders[loop.index].assignedStatus}">Unassign Grader</t:link_to></td>
		   	  </c:when>
		   	  <c:otherwise>
		   	 	<td><t:link_to path="${UrlUtils.ADD_GRADER_PATH}?assigned=:id&user=:userid" classBS="btn btn-success" method="POST" id="${assigned.id}" secondId="${u.id}">Assign Grader</t:link_to></td>
		   	  </c:otherwise>
		   	</c:choose>
		  </tr>
		  </c:forEach>
		  </tbody>
		</table>
	  </div>
	</div>
  </div>

  <div class="panel panel-default">
	<div class="panel-heading">
	  <h4 class="panel-title">
		<a data-toggle="collapse" href="#adjustments"><strong>Grade Adjustments<span class="caret"></span></strong></a>
	  </h4>
	</div>
	<div id="adjustments" class="panel-collapse collapse in">
	  <div class="panel-body">
	    <t:form_with url="${UrlUtils.ASSIGNMENT_ADJUSTMENTS_PATH}" id="${assigned.id}" secondId="${assignment.id}">
		  <div class="form-group">
			<abbr title="Change how many points this assignment is out of. Doesn't change how many points students receive; Points gained beyond this maximum are considered extra credit."><label for="assigned_Override Maximum Score:">Override maximum score:</label></abbr>
			<input class="form-control" step="0.01" type="number" name="assigned[max_points_override]" id="assigned_max_points_override" <c:if test="${not empty assigned.max_points_override}">value="${assigned.max_points_override}"</c:if>>
		  </div>
		  
		  <div class="form-group">
			<abbr title="After all other grade calculations and adjustments occur, scale both the maximum number of points and the scores students receive so the assignment is out of this many points. Grades will have the same percent score after this adjustment. Affects assignment weight compared to others in the same grade category."><label for="assigned_Scale assignment point value (after all other adjustments):">Scale assignment point value (after all other adjustments):</label></abbr>
			<input class="form-control" step="0.01" type="number" name="assigned[point_value_scale]" id="assigned_point_value_scale" <c:if test="${not empty assigned.point_value_scale}">value="${assigned.point_value_scale}"</c:if>>
		  </div>

		  <div class="actions">
			<input type="submit" name="commit" value="Update Adjustments" class="btn btn-success" data-disable-with="Update Adjustments">
		  </div>
		</t:form_with>
	  </div>
	</div>
  </div>


<div class="panel panel-default">
  <div class="panel-heading">
    <h4 class="panel-title"><strong>
      Graded Notifications
	</strong></h4>
  </div>
  <div class="panel-body">
    <p>
      Students will only be notified that their work has been graded once, 
	  when their submission first becomes graded or when you unhide grades from students.
	  You can use this button to reset this so they get notified again.
	  Students whose submissions are already graded will be re-notified immediately if grades are not hidden from students.
    </p>
    <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_RESET_NOTIFICATIONS_PATH}" classBS="btn btn-default" method="POST" id="${assignment.id}" secondId="${assigned.id}">Reset Graded Notifications</t:link_to>
  </div>
</div>

</div>

</t:layoutj>

