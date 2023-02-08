<%@ page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="${course.title}: ${klass.semester}&nbsp;${klass.section})">

<ol class="breadcrumb">
  <li><t:link_to path="${UrlUtils.KLASSES_PATH}">Classes</t:link_to></li>
  <li>${course.course_code}&nbsp${course.title}: ${klass.semester}&nbsp${klass.section}</li>
  <li class="active">Gradebook</li>
</ol>

<center>
<h1>${course.title }</h1>
<h4>${klass.semester} section ${klass.section}</h4>
</center>

<c:if test="${klass.isKlassAdmin(current_user)}">
    <center>
	  This is the grader/student class view. 
	  If you are neither of those and a link brought you here, please report it as a bug.
	  <br>
	  <t:link_to path="${UrlUtils.KLASS_ASSIGNMENTS_PATH}" secondId="${klass.id}" classBS="btn btn-default">Go To Instructor View</t:link_to>
	</center>
	<hr>
</c:if>

<c:if test="${not empty grader_assigneds}">

  <h2> Assignments to grade </h2>
  <table class="table table-hover">
    <thead>
	  <tr>
	    <th>Title</th>
		<th>Due</th>
	    <th>Status</th>
		<th>Grade</th>
	  </tr>
	</thead>
	<tbody>
	<c:forEach var="ad" items="${grader_assigneds}" varStatus="loop">
	  <tr>
    	<td>${grader_assignments[loop.index].title}</td>
		<td>${ad.formatDueDate()}</td>
		<td width="40%"><%-- <%= render 'assignment_status', assigned: ad, assignment: ad.assignment %> --%> ASSIGNMENT STATUS</td>
		<td><t:link_to path="${UrlUtils.SUBMISSIONS_PATH}?assigned=:id" classBS="btn btn-default" id="${ad.id}">Grade</t:link_to></td>
		<%-- <td><%= link_to 'Grade', submissions_path(assigned: ad.id), class:  (ad.count_ungraded==0 ? "btn btn-default" : "btn btn-success") %></td> --%>
	  </tr>
	</c:forEach>
	</tbody>
  </table>	
  
  <h3> Files </h3>
  <li class="list-group-item hoverable-thing">
		
		
		<script>
		  directorybrowser0Toggled = true
		  function fvToggleIconFordirectorybrowser0()
		  {
		    if(directorybrowser0Toggled)
			{
			  directorybrowser0Toggled = false
			  document.getElementById("fvIconFordirectorybrowser0").className = "glyphicon glyphicon-folder-close"
			}
			else
			{
			  directorybrowser0Toggled = true
			  document.getElementById("fvIconFordirectorybrowser0").className = "glyphicon glyphicon-folder-open"
			}
		  }
		</script>
		
		<a data-toggle="collapse" href="#directorybrowser0" onclick="fvToggleIconFordirectorybrowser0()" aria-expanded="false" class="collapsed">
		  <strong>
			<span class="glyphicon glyphicon-folder-close" aria-hidden="true" id="fvIconFordirectorybrowser0"></span>
			Class Student Files
			<span class="caret"></span>
		  </strong>
		</a>
	  </li>
	  
	  
	<h2> Assignments </h2>
	<table class="table table-hover">
	    <thead>
			<tr>
				<th>Title</th>
				<th>Category</th>
				<th>Due</th>
				<th>Status</th>
				<th>Submit</th>
				<th style="width:20%">Grade</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
	<%-- 	  <% @klass.assigned.order(due_date: :desc).each do |ad| %>
		  <% s = ad.get_user_submission(current_user) %>
			  
		<% if s %>
		  <% feedback_seen = (s.contributors & current_user.contributors).first.feedback_seen? || !s.graded? || s.assigned.hide_grades? %>
		  <% unless feedback_seen %>
			  <tr class="info">
		  <% else %>
			  <tr>
		  <% end %>
		<% else %>
			  <tr>
		<% end %> --%>
		
			    <td><%-- <%= ad.assignment.title %> --%></td>
				<td><%-- <%= ad.assignment.grade_category ? ad.assignment.grade_category.title : "None" %> --%></td>
			
				<td><%-- <%= ad.get_user_due_date(current_user).strftime("%A, %b %d at %I:%M%p") %> --%></td>
				
				
				<%-- <% if s %>
				  <% if s.graded? && !ad.hide_grades? %>
				    <%# Graded Assignment %> --%>
					<td>
		<%-- 			  <% if s.has_active_regrade_request? %> --%>
					    <p style="color:blue"><b>Regrade Requested</b></p>
					  <%-- <% else %>
					    <p style="color:blue">Graded</p>
					  <% end %> --%>
					</td>
					<td><%-- <%= link_to "Feedback", submission_path(s), class: (feedback_seen ? "btn btn-primary" : "btn btn-info") %> --%></td>
					<td>
					  <%-- <%= render "submissions/submission_grade_bar", submission: s, assigned: ad %> --%>
					</td>
				  <%-- <% else %>
				    <%# Assignment is submitted or in progress, but not graded. %>
				    
					<% case ad.assignment.assignment_type %>
				    <% when "student_file" %> --%>
				      <td><p style="color:green">Submitted</p></td>
					  <%-- <td><%= link_to "View", submission_path(s), class: "btn btn-default" %> --%>
					  
				    <%-- <% when "student_repo" %>
				      <% if s.turned_in? %> --%>
					    <td><p style="color:green">Submitted</p></td>
						<td><%-- <%= link_to "View", submission_path(s), class: "btn btn-default" %>
					  <% elsif !ad.overdue_for?(current_user) %> --%>
					    <td><p style="color:orange">In Progress</p></td>
					    <td><%-- <%= link_to "View & Submit", submission_path(s), class: "btn btn-warning" %>
					  <% else %> --%>
					    <td><p style="color:red">In Progress, <b>Overdue</b></p></td>
					    <td><%-- <%= link_to "View & Submit", submission_path(s), class: "btn btn-danger" %> --%>
					  <%-- <% end %> --%>
				   <%--  <% when "professor_file", "grade_only" %> --%>
				      <td>Not Graded</td>
					  <td></td>
				    <%-- <% end %> --%>
					
					<td>
					  <div class="progress" style="position: relative; text-align: center">
					    <span style="position:absolute; left: 0; right: 0">
						  -/<%-- <%= ad.get_adjusted_max_grade %> --%>
					    </span>
					  </div>
				    </td>
				  
<%-- 				  <% end %>
				<% else %>
				  <%# No submission yet %>
				
				  <% case ad.assignment.assignment_type %>
				  <% when "student_file" %>
				    <% if ad.overdue_for?(current_user) %> --%>
					  <td><p style="color:red"><b>Overdue</b></p></td>
					  
<%-- 					  <% if ad.student_can_submit?(current_user) %> --%>
					    <td>	</td>
					  <%-- <% else %>
					    <td></td>
					  <% end %> --%>
					  
					<%-- <% else %>
					  <%# Not overdue %>
					  <td>Not Submitted</td>
					  <td><%= link_to "View & Submit", new_submission_path(assigned: ad.id), class: "btn btn-warning" %></td>
					<% end %> --%>
					
				  <%-- <% when "student_repo" %> --%>
				    <%-- <% if ad.overdue_for?(current_user) %> --%>
					  <td><p style="color:red"><b>Overdue</b></p></td>
					  
					<%--   <% if ad.student_can_submit?(current_user) %>
					    <td><%= link_to "Start", new_submission_path(assigned: ad.id), class: "btn btn-danger" %></td>
					  <% else %> --%>
					    <td></td>
					  <%-- <% end %> --%>
					  
					<%-- <% else %>
					  <%# Not overdue %>
					  <td>Not Started</td>
					  <td><%= link_to "Start", new_submission_path(assigned: ad.id), class: "btn btn-warning" %></td>
					<% end %>
				  <% when "professor_file", "grade_only" %>
				    <td>Not Graded</td>
					<td></td>
				  <% end %> --%>
				  
				  <td>
					<div class="progress" style="position: relative; text-align: center">
					  <span style="position:absolute; left: 0; right: 0">
						-/<%-- <%= ad.get_adjusted_max_grade %> --%>
					  </span>
					</div>
				  </td>
				<%-- <% end %> --%>
				
				<td>
				  <%-- <% past_subs = current_user.past_contributors.select{|pc| pc.submission.assigned==ad} %>
				  <% if past_subs.any? %> --%>
				    <div class="dropdown">
				      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Past Submissions<span class="caret"></span></a>
				      <ul class="dropdown-menu">
					    <%-- <% past_subs.each do |ps| %>
					      <li><%= link_to ps.submission.created_at.to_s, submission_path(ps.submission) %></li>
					    <% end %> --%>
					  </ul>
				    </div>
				  <%-- <% end %> --%>
				</td>
			  </tr>
			<%-- <% end %> --%>
		</tbody>
	</table>
	
	<p>
	<%-- <center><h3> Current Grade: <%= @klass.get_student_grade_percent(current_user).round(2) %>% </h3> --%>
	
	<h4> Grade Summary: </h4>
	<table class="table table-hover">
	  <thead> 
	    <tr>
		  <th>Category</th>
		  <th>Category Weight</th>
		  <th>Points Earned</th>
		  <th>Percent</th>
		</tr>
	  </thead>
	  <tbody>
	  	<c:forEach var="cat" items="${grade_categories}">
	    <%-- <% @klass.get_grade_categories.each do |cat| %> --%>
		  <tr>
		    <td> ${cat.title} </td>
			<td> ${cat.weight}% </td>
		    <td> <%-- <%= cat.get_category_grade_points(@klass, current_user) %>/<%= cat.get_category_max_points(@klass, current_user) %> --%> </td>
			<td> <%-- <%= (cat.get_category_grade_percent(@klass, current_user)*100).round(2) %> --%>% </td>
		  </tr>
		</c:forEach>
	  </tbody>
	</table>
	</p>

</c:if>


</t:layoutj>