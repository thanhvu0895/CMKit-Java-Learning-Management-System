<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="f" tagdir="/WEB-INF/tags/form_tags"%>

<t:layoutj pageTitle="${assignment.title} | Gradebook">

<ol class="breadcrumb">
  <%@ include file="../assigneds/_grading_crumbs.jsp"%>
  <li class="active">Gradebook</li>
</ol>
<jsp:include page="../assigneds/_tabs.jsp"><jsp:param name="current" value=":gradebook"/></jsp:include>

<br>
<center>
  Download As CSV:
  <div class="btn-group">
    <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_GRADEBOOK_CSV_PATH}" classBS="btn btn-default" id="${assignment.id}" secondId="${assigned.id}">All Submissions</t:link_to>
    <t:link_to path="${UrlUtils.ASSIGNMENT_ASSIGNED_GRADEBOOK_CSV_PATH}?skip_prev=true" classBS="btn btn-default" id="${assignment.id}" secondId="${assigned.id}">Exclude Previous Submissions</t:link_to>
  </div>
</center>
<br>

<center>
  <input type="checkbox" id="showPrevious" onchange="hideRows()">  Show Previous Submissions
  <br>
  <input type="checkbox" id="showColors" onchange="toggleColors()">  Show Colors
</center>

</div>

<table id="gradebook" class="table table-bordered" data-toggle="table" data-sort-name="user" data-sort-order="desc" maintainMetaData="true" maintainSelected="true" style="border-collapse: separate !important">
  <thead>
    <tr>
	  <th data-sortable="true" data-field="user" class="sticky-column">Students</th>
	  
	  <%-- <% max_problem_grades = [] %> --%>
	  
	  <c:forEach var="p" items="${problems}">
	    <%-- <% @assigned.assignment.problems.each do |p| %> --%>
	    <%-- <% max_problem_grades[p.id] = p.points %> --%>
	    <th data-sortable="true" data-sort-name=<%-- <%= "_grade"+p.id.to_s+"_data.raw-grade"+p.id.to_s %> --%> data-field=<%-- <%= "grade"+p.id.to_s %> --%>><%-- <%= link_to (p.title + " ( /"+p.points.to_s+")"), assignment_show_grade_problem_path(@assigned.assignment, @assigned, p), style: "color: black" %> --%></th>
	  </c:forEach>
	  
	  <th data-sortable="true" data-field="adj" data-sort-name="_adj_data.raw-adj">Overall Adjustment</th>
	  <th data-sortable="true" data-field="perc_adj" data-sort-name="_oerc_adj_data.raw-perc">Percent Modifier</th>
	  
	  <%-- <% max_grade = @assigned.get_adjusted_max_grade %> --%>
	  
	  <th data-sortable="true" data-field="grade" data-sort-name="_grade_data.raw-grade">Grade ( /<%-- <%= max_grade %> --%>)</th>
	  
	</tr>
  </thead>
  <tbody>
  
  <c:forEach var="s" items="${submissions}">
  <%-- <% @assigned.submissions.each do |s| %> --%>
  <%-- <tr data-prev=<%= s.contributors.empty? %>> --%>
  
  <%-- <%# Contributor names %> --%>
  
  <td class="sticky-column">
		  <%-- <%= link_to submission_grade_path(s), style: "color: black" do %>
		    <%= s.contributors.map{|c| c.user.get_full_preferred_name}.join(", ") %>
		    <% if s.past_contributors.any? %>
		      (<s><%= s.past_contributors.map{|c| c.user.get_full_preferred_name}.join(", ")%></s>)
		    <% end %>
		  <% end %> --%>
		</td>
		
		<%-- <% @assigned.assignment.problems.each do |p| %>
		  <% gp = s.graded_problems.select{|gp| gp.problem_id == p.id}.first %>
		  <% if gp %>
			<% if gp.graded? %>
			  <% grade = gp.get_grade_points %>
			  <td class="recolorable" data-grade-color=<%= get_color_for_grade(grade / max_problem_grades[p.id]) %> data-raw-grade<%= p.id.to_s+"="+grade.to_s %>><%= link_to grade, edit_submission_graded_problem_path(s, gp), style: "color: black" %></td>
			<% else %>
			  <td data-raw-grade<%= p.id.to_s %>="-Infinity"><%= link_to "?", edit_submission_graded_problem_path(s, gp), style: "color: black" %>
			<% end %>
		  <% else %>
		    <td data-raw-grade<%= p.id.to_s %>="-Infinity"><%= link_to "?", submission_graded_problems_path(s, problem: p.id), method: :post, style: "color: black" %></td>
		  <% end %>
		<% end %> --%>
		
<%-- 		<% if s.point_adjustment %>
		  <td data-raw-adj=<%= s.point_adjustment %> class="left-bordered-cell"><%= s.point_adjustment %></td>
		<% else %>
		  <td data-raw-adj="-Infinity" class="left-bordered-cell"></td>
		<% end %>
		
		<td data-raw-perc=<%= s.percent_modifier %>>* <%= s.percent_modifier %>%</td>
		
		<% if s.graded? %>
		  <% grade = s.get_adjusted_grade_points %>
		  <td class="left-bordered-cell recolorable" data-grade-color=<%= get_color_for_grade(grade / max_grade) %> data-raw-grade=<%= grade %>><%= grade.round(2) %></td>
		<% else %>
		  <td class="left-bordered-cell" data-raw-grade="-Infinity">?</td>
		<% end %> --%>
	</tr>
  </c:forEach>
    </tbody>
</table>

<script>

  // Toggle the coloration of table cells based on grade
  function toggleColors()
  {
    cells = document.getElementsByClassName("recolorable");
	
	if(document.getElementById("showColors").checked)
	{
	  for(c of cells)
	  {
	    c.style.backgroundColor = c.getAttribute("data-grade-color");
	  }
	}
	else
	{
	  for(c of cells)
	  {
	    c.style.backgroundColor = "white";
	  }
	}
  
  }

  toggleColors();
</script>

<script>
	
	function hideRows()
	{
	  showPrev = document.getElementById("showPrevious").checked==true
	  
	  for(r of document.getElementById('gradebook').rows)
	  {
	    if(!showPrev && r.getAttribute("data-prev")=="true")
		{
	      r.hidden = "true"
		}
		else
		{
		  r.hidden=null
		}
	  }
	}
	
	$(window).resize(function(){
	  $('[data-toggle="table"]').bootstrapTable('resetView',{height: document.documentElement.clientHeight});
	});
	
</script>

<div>
  
	  
</t:layoutj>