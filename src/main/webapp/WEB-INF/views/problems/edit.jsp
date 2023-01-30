<%@page trimDirectiveWhitespaces="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page import="codingmentor.javabackend.k3.Utils.UrlUtils" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<t:layoutj pageTitle="Edit ${problem.title}">
<%@ include file="../assignments/_assignment_crumbs.jsp" %>
<li><t:link_to path="${UrlUtils.ASSIGNMENT_PATH}/:id" id="${assignment.id}">${assignment.title}</t:link_to></li>
<li><t:link_to path="${UrlUtils.ASSIGNMENT_PROBLEMS_PATH}" secondId="${assignment.id}">Rubric</t:link_to></li>
<li class="active">${problem.id}</li>

<h2>Editing Problem "${problem.title}"</h2>

<%@ include file="_form.jsp" %>

<h3>Rubric Items</h3>
These become check boxes on the grading page for this problem.
<table class= "table table-hover">
	<thead>
		<tr>
			<td>Option</td>
			<td>Value</td>
			<td>Edit</td>
			<td>Delete</td>
			<td>Move</td>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach items="${rubric_items}" var="r">
		<tr>
			<td style="word-wrap: break-word;min-width: 300px;max-width: 300px;">${r.title}</td>
			<td>${r.points}</td>
			<td><button data-toggle="collapse" data-target="#item${r.id}" class="btn btn-default">Edit</button></td>
			<%-- <td><%= link_to 'Delete', assignment_problem_rubric_item_path(@assignment, @problem, r), method: :delete, class: "btn btn-danger" %></td> --%>
			<td>
			  <%-- <%= link_to '<span class="glyphicon glyphicon-arrow-down"></span>'.html_safe, assignment_problem_rubric_item_move_down_path(r.problem.assignment, r.problem,r), method: :post, class: "btn btn-default" %>
			  <%= link_to '<span class="glyphicon glyphicon-arrow-up"></span>'.html_safe, assignment_problem_rubric_item_move_up_path(r.problem.assignment, r.problem,r), method: :post, class: "btn btn-default" %> --%>
			</td>
		</tr>
		<tr id="item${r.id}" class="collapse">
		  <td colspan=5>
			<div>
			<t:form_with url="ASSIGNMENT_PROBLEM_RUBRIC_ITEM_PATH"></t:form_with>
			</div>
		  </td>
		</tr>			
	 	</c:forEach>
	</tbody>
</table>

<button data-toggle="collapse" data-target="#new_option" class="btn btn-primary">Add Option</button>

<div id="new_option" class="collapse">
	<div class="panel panel-default">
	<div class="panel-body">
	<%-- 	<%= form_with( url: assignment_problem_rubric_items_path(@assignment,@problem), method: :create) do |form| %>
	
		<div class="form-group">
			<%= form.label "Text" %>
			<%= form.text_field :title, class: "form-control"  %>
		</div>

		<div class="form-group">
			<%= form.label "Points" %>
			<%= form.number_field :points, step: :any, class: "form-control" %>
		</div>
		
		<div class="actions">
			<%= form.submit "Add", class: "btn btn-success" %>
		</div>
	<% end %> --%>
	</div>
	</div>
</div>

<br><br><br>
<h3> Reusable Comments </h3>
<table class="table table-striped" id="reusableCommentsTable">
  <thead>
    <tr>
	  <th>Comment</th>
	  <th>Delete</th>
	</tr>
  </thead>
  <tbody>
  <c:forEach items="${reusable_comments}" var="rc">
  <%-- 
  <tr>
	    <td><%= simple_format(rc.comment) %></td>
		<td>
		  <%= link_to assignment_problem_reusable_comment_path(@assignment, @problem, rc), method: :delete, remote: true, class: "btn btn-danger rc-deleter" do %>
		    <span class="glyphicon glyphicon-trash" aria-alt="Delete"></span>
		  <% end %>
		</td>
	  </tr> --%>
   </c:forEach>
  </tbody>
</table>

<h4> Add a reusable comment: </h4>
<%-- <%= form_with( url: assignment_problem_reusable_comments_path(@assignment,@problem), model: ReusableComment.new, method: :create, id: "addReusableComment") do |form| %>
	
	<div class="form-group">
		<%= form.label "Comment" %>
		<%= form.text_area :comment, class: "form-control"  %>
	</div>
	
	<div class="actions">
		<%= form.submit "Add", class: "btn btn-success" %>
	</div>
<% end %>

<%# Handle this form with javascript %> --%>
<script>

// HTML for a delete button
// replace _RCID_ with the ID to delete
<%-- deleteButtonHTML = "<%= ( link_to "<span class=\"glyphicon glyphicon-trash\" aria-alt=\"Delete\"></span>".html_safe, assignment_problem_reusable_comment_path(@assignment, @problem, "_RCID_"), method: :delete, remote: true, class: "btn btn-danger rc-deleter" ).gsub("\"","\\\"").html_safe %>" --%>

// ----- Scripts for adding new reusable comments -----

var d = null

addReusableComment.addEventListener('ajax:success', function(event) {
  var detail = event.detail;
  var data = detail[0], status = detail[1], xhr = detail[2];
  d = detail
  
  
  r = reusableCommentsTable.insertRow()
  
  c = r.insertCell()
  c.innerHTML = reusable_comment_comment.value
  
  c = r.insertCell()
  c.innerHTML = deleteButtonHTML.replace("_RCID_",data.id)
  
  addDeleteButtonListeners(c.children[0])
  
  reusable_comment_comment.value = ""
  
  alert("Reusable Comment Added!")
})

addReusableComment.addEventListener('ajax:error', function(event) {
  var detail = event.detail;
  var data = detail[0], status = detail[1], xhr = detail[2];
  
  // Come up with an error string
  str = "Failed to save reusable comment: "
  for(e in data)
  {
    str+=e
	str+= " " + data[e]
  }
  
  alert(str)
})


// ------ Scripts for deleting reusable comments -----

function addDeleteButtonListeners(db)
{
  db.addEventListener('ajax:success', function(event) {
    var detail = event.detail;
    var data = detail[0], status = detail[1], xhr = detail[2];

	//Remove this row
	this.parentElement.parentElement.remove()
	
    alert("Reusable comment deleted!")
  })
  
  db.addEventListener('ajax:error', function(event) {
    var detail = event.detail;
    var data = detail[0], status = detail[1], xhr = detail[2];
     
    alert("Failed to delete reusable comment.")
  })
}

deleteButtons = document.getElementsByClassName("rc-deleter")
for(db of deleteButtons)
{
  addDeleteButtonListeners(db)
}

</script>
</t:layoutj>