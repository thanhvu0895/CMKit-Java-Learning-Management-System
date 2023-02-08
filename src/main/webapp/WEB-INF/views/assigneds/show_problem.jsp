<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${assignment.title} | Grade">
<ol class="breadcrumb">
  <%@ include file="../assigneds/_grading_crumbs.jsp"%>
  <li><t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_PROBLEMS_PATH}" secondId="${assignment.id}" id="${assigned.id}">Problems</t:link_to></li>
  <li class="active">${problem.title}</li>
</ol>

<center><h2>${problem.title}</h2></center>

<center>
  <input type="checkbox" id="showPrevious" onchange="hideRows()">  Show Previous Submissions
</center>

<c:if test="${not empty problem.grader_notes}">
<u>Grader Notes:</u>
<span style="white-space:pre"><c:out value="${problem.grader_notes}" escapeXml="false"/></span>
</c:if>

<t:form_with url="${ASSIGNMENT_ASSIGNED_PROBLEMS_PATH}" id="${assigned.id}" secondId="${assignment.id}" thirdId="${problem.id}" method="PATCH">

<table class="table table-hover" data-toggle="table" data-search="true" maintainMetaData="true" maintainSelected="true" id="subsTable" data-height="300">
  <thead>
    <tr>
      <th data-sortable="true" data-field="user" class="sticky-column">User(s)</th>
	  <th>View</th>
	  <c:choose><c:when test="${assignment.getAssignmentType().equals('student_repo')}">
	  	<th data-sortable="true" data-field="repo">Repository</th>
	  </c:when><c:when test="${assignment.getAssignmentType().equals('student_file') || assignment.getAssignmentType().equals('professor_file')}">
	    <th>File</th>
	  </c:when></c:choose>
	  
	  <c:forEach var="i" items="${rubric_items}">
	    <%-- <%# <th><p class="rotated" style="border-bottom: 1px; transform: rotate(90deg)">TITLE</p></th> %> --%>
	    <th>
		  <span >${i.title}</span>
		  <br>
		  <button class="btn btn-default btn-xs" title="Toggle All" type="button" onclick="toggleAll(${i.id})">
		    <span class="glyphicon glyphicon glyphicon-ok-circle"></span>
		  </button>
		</th>
	  </c:forEach>
	  	  <th>Point Adjustment</th>
	  <th>Comments</th>
	  
	  <th data-sort-name="_graded_data.raw-grade" data-sortable="true" data-field="grade">Grade</th>
    </tr>
  </thead>

  <tbody>
<%--   <%= form.fields_for "Submissions" do |subItems| %>
    <% @submissions.order(id: :asc).each do |submission| %>
 	<% updateFunc = "updateTotal("+submission.id.to_s+")" %>
	<%= subItems.fields_for submission.id.to_s do |items| %>
      <tr data-prev=<%= submission.contributors.empty? %>> --%>
      <%-- <%# Contributor names %> --%>
      
      <td class="sticky-column"> JOIN CONTRIBUTORS FULL PREFERRED NAME HERE<%-- <%= submission.contributors.map{|c| c.user.get_full_preferred_name}.join(", ") %> --%>
      <c:if test="${not empty past_contributors}">
      <%-- <% if submission.past_contributors.any? %> --%>
        (<s><%-- <%= submission.past_contributors.map{|c| c.user.get_full_preferred_name}.join(", ")%> --%> JOIN PAST CONTRIBUTORS NAME HERE</s>)
      </c:if>
      </td>
      
      <td>
        <div class="btn-group-vertical">
          <t:link_to path="${UrlUtils.SUBMISSION_GRADE_PATH}" classBS="btn btn-default btn-xs" secondId="${submission.id}">Submission</t:link_to>

<%--      <% graded_problem = submission.graded_problems.find_by(problem: @problem) %> --%>
		  <c:choose><c:when test="${not empty graded_problem}">
		    <t:link_to path="${UrlUtils.EDIT_SUBMISSION_GRADED_PROBLEM_PATH}" classBS="btn btn-default btn-xs" id="${graded_problem.id}" secondId="${submission.id}">Problem</t:link_to>
		  </c:when><c:otherwise>
		    <t:link_to path="${UrlUtils.SUBMISSION_GRADED_PROBLEMS_PATH}" classBS="btn btn-default btn-xs" secondId="${submission.id}" method="POST">Problem</t:link_to>
		  </c:otherwise></c:choose>
		</div>
	</td>
	
	<c:choose><c:when test="${assignment.getAssignmentType().equals('student_repo')}">
	<%-- <% if @assigned.assignment.student_repo? %> --%>
	<td>
	  <c:if test="${not empty repo}">
	   <%-- <% if submission.repo %> --%>
	   <kbd><%-- <%= get_repo_url submission.repo %> --%></kbd>
	  </c:if>
	</td>
	</c:when><c:when test="${assignment.getAssignmentType().equals('student_file') || assignment.getAssignmentType().equals('professor_file')}">
	<%-- <% unless submission.blank? %> --%>
	<c:if test="${!submission.blank }">
	  <%-- <% files = submission.get_files %> --%>
	  <c:if test="${files.length == 1}">
		<td>
		  <div class="btn-group">
			<%-- <%= link_to submission_download_path(submission, filename: files[0][0].split(File::SEPARATOR)[-1]), class: "btn btn-default btn-sm" do %> --%>
			  <span class="glyphicon glyphicon-save"></span>
<%-- 				    <% end %> --%>
			<%-- <%= link_to submission_download_inline_path(submission, filename: files[0][0].split(File::SEPARATOR)[-1]), class: "btn btn-default btn-sm", target: "kit-file" do %> --%>
			  <span class="glyphicon glyphicon-eye-open"></span>
<%-- 				    <% end %> --%>
				  </div>
			    </td>
	  </c:if>
	</c:if>

<%-- <%# Need to clearly show multiple files without taking up too much room %> --%>
	
	
	</c:when>
	</c:choose>
	
</t:form_with>
</t:layoutj>