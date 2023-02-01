<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester} ${klass.section}) | Graders">

<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Settings</li>
</ol>

<jsp:include page="../klasses/_tabs.jsp"><jsp:param name="current" value=":graders"/></jsp:include>

<p>
  <table class="table table-hover">
	<thead>
	  <tr>
		<th>Name</th>
		<th>Email</th>
		<th>Assigned Assignments</th>
		<th>Submissions To Grade</th>
		<th></th>
	  </tr>
	</thead>
	<tbody>
	  <c:forEach var="s" items="${graders}" varStatus="loop">
	  		<tr>
	  	  <td>${grader_users[loop.index].getFull_preferred_name()}</td>
		  <td><a href="mailto:${grader_users[loop.index].email}">${grader_users[loop.index].email}</a></td>
		  
		  <%-- <% asds = s.user.assigned_graders.map{|ag| ag.assigned} & @klass.assigned %>
		  
		    <%#= asds.select{|ad| ad.submissions.map{|s| s.contributors.any? && s.turned_in? && (!s.graded? || s.has_active_regrade_request?)}.include?(true)}.count %>
		  
		  <td>
			<%= asds.count %>
		  </td>
		  <td>
		    <%= asds.map{|ad| ad.submissions.select{|s| s.contributors.any? && s.turned_in? && (!s.graded? || s.has_active_regrade_request?)}.count}.sum %>
		  </td>
		  
		  <td><%= link_to 'Remove', s, method: :delete, data: { confirm: 'Are you sure?' }, class: "btn btn-danger" %></td> --%>
		</tr>
	  </c:forEach>
	</tbody>
  </table>
</p>

<form class="form-inline" action="/graders" accept-charset="UTF-8" data-remote="true" method="post">
  <div class="form-group">
	<label for="Emails (separate multiple with commas):">Emails (separate multiple with commas):</label>
	<input class="form-control" type="text" name="emails" id="emails">
  </div>
  <div class="form-group">
	<input value="2" type="hidden" name="klass_id" id="klass_id">
  </div>

  <div class="form-group">
	<input type="submit" name="commit" value="Add Grader" class="btn btn-success" data-disable-with="Add Grader">
  </div>
</form>
</t:layoutj>